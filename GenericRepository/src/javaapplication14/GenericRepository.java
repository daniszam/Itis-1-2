package javaapplication14;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenericRepository<T> {
    private String csv;
    private File file = new File("repo.csv");
    private String[] fieldS;
    
    
    
    public void readCsv(){
        try(BufferedReader bf = new BufferedReader (new FileReader(file))){
            String line = "";
            StringBuilder sbr = new StringBuilder();
            while((line=bf.readLine())!=null){
                sbr.append(line);
            }
            csv = sbr.toString();
            String[] fieldS = csv.split(",");
        }   catch (FileNotFoundException ex) {
            Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }   
    
    
    public void saveCsv(T o){
        try(BufferedWriter bos = new BufferedWriter(new FileWriter(file))){
           String str = this.getAsCsvString(o);
           bos.write(str);
           bos.flush();
           bos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidEntityException ex) {
            Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
        
    
        
    
    
    public T read(T o){
            try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            Class myclass =Class.forName(o.getClass().getName());
            String str;
            str = reader.readLine();
            String[] atributs = str.split(",");
            int count = 0;
            int max =0;
            Constructor[] constructors = myclass.getDeclaredConstructors();
            for (int i =0 ; i<constructors.length; i++){
                 max = constructors[i].getParameterCount() > constructors[max].getParameterCount() ? i : max; 
            }
            Constructor constructor = constructors[max];
            constructors = null;
            Class[] param = constructor.getParameterTypes();
            Object[] obj = new Object[param.length];
            System.out.println(param[0].getName());
            Pattern numbers = Pattern.compile("^(0-9)[0-9]+");
            Matcher matcher ;
            for(int i = 0; i<param.length; i++){
                matcher = numbers.matcher(atributs[i]);
                if((param[i].getName().equals(Integer.class.getName()))){
                    if(matcher.find()){
                        obj[i] = Integer.parseInt(atributs[i]);
                    }else{
                        obj[i] = 0;
                    }
                     continue;
                }
                if((param[i].getName().equals(Double.class.getName()))){
                    if(matcher.find()){
                        obj[i] = Double.parseDouble(atributs[i]);
                    }else{
                        obj[i] = 0.0;
                    }
                    continue;
                }
                if((param[i].getName().equals(Long.class.getName()))){
                    if(matcher.find()){
                        obj[i] = Long.parseLong(atributs[i]);
                    }else{
                        obj[i] = 0;
                    }
                    continue;
                }
                if((param[i].getName().equals(Short.class.getName()))){
                    if(matcher.find()){
                        obj[i] = Short.parseShort(atributs[i]);
                    }else{
                        obj[i] = 0; 
                    }
                    continue;
                }
                if((param[i].getName().equals(Byte.class.getName()))){
                    if(matcher.find()){
                        obj[i] = Byte.parseByte(atributs[i]);
                    }else{
                        obj[i] = 0; 
                    }
                    continue;
                }
                if(param[i].getName().equals(Boolean.class.getName())){
                    obj[i] = Boolean.parseBoolean(atributs[i]);
                    continue;
                }
                if(param[i].getName().equals(Character.class.getName())){
                    obj[i] = (char) atributs[i].charAt(0);
                    continue;
                }
                 if(param[i].getName().equals(String.class.getName())){
                    obj[i] = atributs[i];
                    continue;
                }
            }
            T o1 = (T) constructor.newInstance(obj);
            return o1;
    
            } catch (InstantiationException ex) {
            Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
           
    }
    
    public String getAsCsvString(T o)
            throws InvalidEntityException
//                    IllegalArgumentException, 
//                    IllegalAccessException, 
//                    NoSuchMethodException, 
//                    InvocationTargetException
    {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] values = new String[fields.length];
        int i = 0;
        for (Field field : fields) {
            String methodName = "";
            if(field.getName().length()>1){
                 methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            }else {
                 methodName = "get" + field.getName().substring(0, 1).toUpperCase(); 
            }
            try{
                Method m = o.getClass().getDeclaredMethod(methodName, new Class[]{});
                try{
                    values[i] = m.invoke(o).toString();
                }
                catch(IllegalAccessException ex){
                    throw new InvalidEntityException("The currently executing method does not have access to the definition of the specified class: " + methodName, ex);
                }
                catch(IllegalArgumentException ex){
                    throw new InvalidEntityException("Method has been passed an illegal or inappropriate argument " + methodName, ex);
                }
                catch(InvocationTargetException ex){}
                i++;
            }
            catch(NoSuchMethodException ex){
                try{
                    values[i] = field.get(o).toString();
                }
                catch(IllegalAccessException|IllegalArgumentException ex1){
                    throw new InvalidEntityException("Entity class doesn't have get method: " + methodName, ex1);
                }
                
            }
            catch(SecurityException ex){
                throw new InvalidEntityException("A security violation " + methodName, ex);
            }
        }
        return Stream.of(values).collect(Collectors.joining(","));
    }
}

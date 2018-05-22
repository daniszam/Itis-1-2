/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.propertiesreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author danis_zam
 */
public class PropertiesReader {
    
    
   public PropertiesReader(){
   }
   
   public void readProp(String path){
       Pattern properties = Pattern.compile(".+(properties)$");
       Matcher m = properties.matcher(path);
       if(!m.find()){
            return;
       }
       try(FileReader reader = new FileReader(new File(path))){
           Scanner sc = new Scanner(reader);
           while(sc.hasNextLine()){
               System.out.println(sc.nextLine());
           }
       } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertiesReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesReader.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void getProp(String path){
       Pattern properties = Pattern.compile(".+(properties)$");
       Matcher m = properties.matcher(path);
       if(!m.find()){
            return;
       }
       try(FileInputStream fis = new FileInputStream(new File(path))){
          Properties property = new Properties(); 
          property.load(fis);
          String name = property.getProperty("db.name");
          String pas = property.getProperty("db.pass");
          String mod = property.getProperty("lastmodifited");
          
           System.out.println("name "+ name + "\npass "+ pas +"\nmod "+mod);
           long date1  = System.currentTimeMillis();
           Date date = new Date(date1);
           property.setProperty("lastmodifited", date.toString());
           FileOutputStream fos = new FileOutputStream(new File(path));
           property.store(fos, "config");
       } catch (FileNotFoundException ex) {
           Logger.getLogger(PropertiesReader.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(PropertiesReader.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
      
    public static void main(String[] args) {
        PropertiesReader p = new PropertiesReader();
       // p.readProp("/Users/danis_zam/Desktop/NetBeansProjects/Timsort/build/built-jar.properties");
        p.getProp("/Users/danis_zam/Desktop/NetBeansProjects/PropertiesReader/src/config.properties");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.StudentList;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danis_zam
 */
public class ListOfStudent {

    private List<Student> arr;
    private File file;

    public ListOfStudent() {
        arr = new ArrayList<>();
        file = new File(Paths.get("ListOfStudent").toUri().normalize());
        if (!file.exists()) {
            System.out.printf("File %s doesn't exists. Creating new one.\n", file);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.err.println("Couldn't create a file.");
                System.exit(1);
            }
        }
    }
    
    

    public boolean addStudent(Student student) {
        if (arr.contains(student)) {
            return false;
        } else {
            arr.add(student);
            return true;
        }
    }

    public void saveListOfStudent() {
        try ( DataOutputStream dos = new DataOutputStream(new BufferedOutputStream( new FileOutputStream(file, true)))) {
            for(int i=0; i<arr.size();i++){
                dos.writeUTF(arr.get(i).getName()+";"+arr.get(i).getGender());
                dos.writeInt(arr.get(i).getBirthday());
                dos.writeDouble(arr.get(i).getAverageBall());
            }
            arr.clear();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListOfStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListOfStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        }
    
    public ArrayList<Student> readListOfStudent(){
        try( DataInputStream dis = new DataInputStream(new BufferedInputStream( new FileInputStream(file)))){
            //BufferedInputStream bis = new BufferedInputStream(is);
       //     DataInputStream dis = new DataInputStream(bis);
            
            while(dis.available()>0){
             String str = dis.readUTF();
              String[] array =str.split(";");      
              
              arr.add(new Student(array[0], array[1], dis.readInt(), dis.readDouble()));
         }
            
            return (ArrayList<Student>) arr;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListOfStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListOfStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    }

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kpfu.itis.darZam1_2;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import ru.itis.kpfu.darZam1_2.Comparators.*;
import ru.itis.kpfu.darZam1_2.UI.JTableExample;


/**
 *
 * @author danis_zam
 */
public class FileManager {

    private Path directory;
   private ArrayList<File> files;
   private File[] files1;
    
    public FileManager(){
        directory = Paths.get(System.getProperty("file.separator"));  
        files1 = new File(directory.toUri()).listFiles();
        files = new ArrayList<File>();
        for (File item: files1){
            files.add(item);
        }
    }       
    
    public ArrayList<File> setFiles(){
        files1 = new File(directory.toUri()).listFiles();
        files = new ArrayList<File>();
        for (File item: files1){
            files.add(item);
        }
        return files;
    }

    public ArrayList<File> getFiles(){
        return files;
    }
    
    
    public void setDirectory(Path directory) {
        this.directory = directory;
    }

    public Path getDirectory() {
        return directory;
    }
    
    public void sortByName (){
        Collections.sort(files, new NameComarator());
    }
   
    public void sortByDirectory(){
        Collections.sort(files, new DirectoryComparator());
    }
    
    public void sortByExtension(){
         Collections.sort(files, new ExtensionComparator());
    }
    
   public void sortBySize(){
        Collections.sort(files, new SizeComparator());
   }
    
    public void sortByDate(){
         Collections.sort(files, new DateComparator());
    }
    
    
    
    public static void main(String[] args) {
        JTableExample j =new JTableExample();
    }
}

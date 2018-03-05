/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZamaliev1_2;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danis_zam
 */
public class ListOfTrack {
    private String path ;
    private TreeSet<Track> trackSet;
    private static final String DIRECTORY = "/Users/danis_zam/Desktop/NetBeansProjects/MyMusicPlayer";
    
    public ListOfTrack() {
       
    }

    public boolean add(String path) { 
        System.out.println(path);
        try {
            Track mp3file = new Track(path);
            
            //  this.save(mp3file);
             
        } catch (IOException ex) {
            System.out.println("file could not be created");
            return false;
        } catch (UnsupportedTagException ex) {
            Logger.getLogger(ListOfTrack.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (InvalidDataException ex) {
            System.out.println("недопустимый формат");
            return false;
        }
        return true;
    }
    
   // public boolean deleate (Track track){
        
  //  }

    public  ArrayList<Track> readAll() {
        try (FileInputStream fin = new FileInputStream(DIRECTORY + "/ListOfTrack.ser")){
            ObjectInputStream ois = new ObjectInputStream(fin);
            return ( ArrayList<Track>) ois.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println("error1");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("error2");
        }
        return null;
    }
    
    
    
     public boolean save (Track track){
         if(this.readAll()!=null){
          for (int i = 0 ; i<this.readAll().size(); i++){
                    if(this.readAll().toArray()[i].equals(track)){
                        return false;
                    }
             }
         }
        try {
            ArrayList<Track> traaaa;
            if (this.readAll()==null){
                 traaaa = new  ArrayList<>();
            } else{
            traaaa = this.readAll();
             }
             traaaa.add(track);
            final FileOutputStream fos = new FileOutputStream(DIRECTORY + "/ListOfTrack.ser" ) ;
            final ObjectOutputStream oos = new ObjectOutputStream(fos);
           oos.writeObject(traaaa);
           return true;
        } catch (FileNotFoundException ex){
            System.out.println("Файл не найден");
        } catch (IOException ex){
            System.out.println("не работает");
        }
        return false;
    }
     
  
   private void saveSortArray(ArrayList<Track> track){
         try {
            final FileOutputStream fos = new FileOutputStream(DIRECTORY + "/ListOfTrack.ser" ) ;
            final ObjectOutputStream oos = new ObjectOutputStream(fos);
           oos.writeObject(track);
        } catch (FileNotFoundException ex){
            System.out.println("Файл не найден");
        } catch (IOException ex){
            System.out.println("не работает");
        }
   }
     
     
   public void sortByGenre(){
       List<Track> track = this.readAll();
       Collections.sort(track, new GenreComparator());
       this.saveSortArray((ArrayList<Track>) track);
   }
     
    public void sortByName(){  // не работает
       List<Track> track = this.readAll();
       Collections.sort(track, new NameComporator());
       this.saveSortArray((ArrayList<Track>) track);
    }
    
    public void sortByMusician(){
        List<Track> track = this.readAll();
       Collections.sort(track, new MusicianComparator());
       this.saveSortArray((ArrayList<Track>) track);
    }
    
    public void sortByYear(){
         List<Track> track = this.readAll();
       Collections.sort(track, new YearComparator());
       this.saveSortArray((ArrayList<Track>) track);
    }
    
    public void sortByDuration(){}
}

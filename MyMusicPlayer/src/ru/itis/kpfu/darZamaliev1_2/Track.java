/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZamaliev1_2;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author danis_zam
 */
public class Track extends Mp3File implements Serializable  {
    
    private int year = -1;
    private double duration;
    private String name = "default";
    private String musician = "default";
    private String genre = "default";
   //private Genre genre;
    
    public Track(String name, String musician, double duration, int year){
        this.name = name;
        this.musician = musician;
        this.duration = duration; 
        this.year = year;
    }

    Track(String path) throws IOException, UnsupportedTagException, InvalidDataException {
       super(path);
    }
       //   enum Genre { blues, country, electronic, hiphop, jazz, pop, rock }
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public double getDuration() {
        return duration;
    }

    public String getMusician() {
        return musician;
    }

    public String getGenre() {
        return genre;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setMusician(String musician) {
        this.musician = musician.toLowerCase();
    }

    public void setGenre(String genre) {
        this.genre = genre.toLowerCase();
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Track other = (Track) obj;
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.name.toLowerCase(), other.name.toLowerCase())) {
            return false;
        }
        if (!Objects.equals(this.musician.toLowerCase(), other.musician.toLowerCase())) {
            return false;
        }
        return true;
    }
    
    public String toString(Track track){
        return this.name + " " + this.musician + " " + this.duration + " " + this.year;
    }
    
    public boolean deleate(){  // возможно не пригодится
        
        return true;
    }
    
    public String getAllGenre(){
        return genre;
    }
    
   
  
}
    


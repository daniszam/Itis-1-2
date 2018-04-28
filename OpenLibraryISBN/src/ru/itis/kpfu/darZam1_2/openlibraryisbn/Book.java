/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.openlibraryisbn;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danis
 */
public class Book {
    
    private String title;
    private String publishers;
    private String author;
    private int date;
    private String lastMod;
    private URL url;
    private String language;
    private URL img;
    private URL authorURL;
    private int page;

    
    
    private static final Logger log = Logger.getLogger(Book.class.getName());

    
    public Book(){ }
    
    
    public Book(String name, String publishers, String author, int date, String lastMod, URL url) {
        
        
        this.title = name;
        this.publishers = publishers;
        this.author = author;
        this.date = date;
        this.lastMod = lastMod;
        this.url = url;
        
       
       
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    
    
    

    public URL getAuthorURL() {
        return authorURL;
    }

    public void setAuthorURL(String authorURL) {
        try {
            this.authorURL =new URL( authorURL);
        } catch (MalformedURLException ex) {
            log.log(Level.FINE, "no http");
        }
    }
    
    
    
    public URL getImg() {
        return img;
    }

    public void setImg(String img) {
        try {
            this.img = new URL (img);
        } catch (MalformedURLException ex) {
            log.log(Level.FINE, "no http");
        }
//        System.out.println(this.img);
//        try(InputStream is  = this.img.openConnection().getInputStream()){ 
//            File image = new File(this.getTitle().concat(".png"));  
//            BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(image));
//            while(is.available()!=0){
//               bs.write(is.read());
//            }
//            bs.flush();
//            bs.close();
//        }
//       catch (IOException ex) {
//            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", publishers=" + publishers + ", author=" + author + ", date=" + date + ", lastMod=" + lastMod + ", url=" + url + ", language=" + language + ", img=" + img + ", authorURL=" + authorURL + '}';
    }

    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.title);
        hash = 23 * hash + Objects.hashCode(this.publishers);
        hash = 23 * hash + Objects.hashCode(this.author);
        hash = 23 * hash + this.date;
        hash = 23 * hash + Objects.hashCode(this.lastMod);
        hash = 23 * hash + Objects.hashCode(this.url);
        hash = 23 * hash + Objects.hashCode(this.language);
        hash = 23 * hash + Objects.hashCode(this.img);
        return hash;
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
        final Book other = (Book) obj;
        if (this.date != other.date) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.publishers, other.publishers)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.lastMod, other.lastMod)) {
            return false;
        }
        if (!Objects.equals(this.language, other.language)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.img, other.img)) {
            return false;
        }
        return true;
    }
    
    
    
    

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishers(String publishers) {
        this.publishers = publishers;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setLastMod(String lastMod) {
        this.lastMod = lastMod;
    }

    public void setUrl(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException ex) {
            log.log(Level.FINE, "no http");
        }
    }
    
    

    public String getTitle() {
        return title;
    }

    public String getPublishers() {
        return publishers;
    }

    public String getAuthor() {
        return author;
    }

    public int getDate() {
        return date;
    }

    public String getLastMod() {
        return lastMod;
    }

    public URL getUrl() {
        return url;
    }
    
    
}

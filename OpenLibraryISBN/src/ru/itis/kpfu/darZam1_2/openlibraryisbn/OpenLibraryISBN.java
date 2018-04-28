/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.openlibraryisbn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author danis
 */
public class OpenLibraryISBN {

    private final String str = "http://openlibrary.org/api/volumes/brief/isbn/";
    private URI uri;
    private String info;
    
    private static Logger log = Logger.getLogger(OpenLibraryISBN.class.getName());
    
    
    public OpenLibraryISBN(){
       
    }
    
    public void searchBook(String ISBN){
       try {
            this.uri = new URI(str.concat(ISBN).concat(".json")).normalize();
        } catch (URISyntaxException ex) {
            log.log(Level.SEVERE, "Your ISBN is incorrect");
        } 
        this.getINFO();
    } 
    
    
    
    public void getINFO(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(uri.toURL().openConnection().getInputStream()))){
            StringBuilder str = new StringBuilder();
            String c;
            while( (c = br.readLine())!=null){
                str.append(c);
            }
            info = str.toString();
        } catch (MalformedURLException ex) {
            log.info("Any Problem");
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Input Output Exception");
        }
    }
    
    public Book getBook(){
        Pattern url = Pattern.compile("(?<=\"recordURL\": \")[a-zA-Z0-9:-_/.]+");
        Pattern date = Pattern.compile("(?<=\"publishDates\": .\")[0-9]+");
        Pattern publishers = Pattern.compile("(?<=\"publishers\": .\")[ a-zA-Z,/.0-9]+");
        Pattern language = Pattern.compile("(?<=\"/languages/)[ a-z]+");
        Pattern title = Pattern.compile("(?<=\"title\": \")[ A-Z0-9a-z]+");
        Pattern time = Pattern.compile("(?<=\"value\": \")[ 0-9-:T]+");
        Pattern author = Pattern.compile("(?<=\"authors\": .{1,2}\"name\": \")[ 0-9A-Za-z]+");
        Pattern authorURL = Pattern.compile("(?<=\"authors\": .{1,2}\"url\": \")[0-9A-Za-z:_/./]+");
        Pattern image = Pattern.compile("(?<=\"medium\": \")[-0-9A-Za-z:_/./]+");
        Pattern page = Pattern.compile("(?<=\"number_of_pages\": )[0-9]+");
        Matcher m = url.matcher(info);
        Book book = new Book();
        if(m.find()){
          //  System.out.println(m.group());
            book.setUrl(m.group());
        }
        m = date.matcher(info);
        if(m.find()){
          //  System.out.println(m.group());
            book.setDate(Integer.parseInt(m.group()));
        }
        m = publishers.matcher(info);
        if(m.find()){
            book.setPublishers(m.group());
           // System.out.println(m.group());
        }
        m = language.matcher(info);
        if(m.find()){
            book.setLanguage(m.group());
           // System.out.println(m.group());
        }
        m = title.matcher(info);
        if(m.find()){
            book.setTitle(m.group());
            //System.out.println(m.group());
        }
        m = time.matcher(info);
        if(m.find()){
            book.setLastMod(m.group());
            //System.out.println(m.group());
        }
        m = author.matcher(info);
        if(m.find()){
            book.setAuthor(m.group());
            //System.out.println(m.group());
        }
        m = authorURL.matcher(info);
        if(m.find()){
            book.setAuthorURL(m.group());
            //System.out.println(m.group());
        }
        m = image.matcher(info);
        if(m.find()){
            book.setImg(m.group());
            //System.out.println(m.group());
        }
        m = page.matcher(info);
        if(m.find()){
            book.setPage(Integer.valueOf(m.group()));
        }
        return book;
       // System.out.println(book);
    }
        
    
    
    public static void main(String[] args) {
       OpenLibraryISBN isbn = new OpenLibraryISBN();
       isbn.searchBook("9781576755129");
       isbn.getBook();
     //   isbn.infoNormalize();
    }
}

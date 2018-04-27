/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.openlibraryisbn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danis
 */
public class OpenLibraryISBN {

    private final String str = "http://openlibrary.org/api/volumes/brief/isbn/";
    private URI uri;
    
    private static Logger log = Logger.getLogger(OpenLibraryISBN.class.getName());
    
    
    public OpenLibraryISBN(String ISBN){
        try {
            this.uri = new URI(str.concat(ISBN).concat(".json")).normalize();
        } catch (URISyntaxException ex) {
            log.log(Level.SEVERE, "Your ISBN is incorrect");
        }
        System.out.println(uri);
    }
    
    public String getINFO(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(uri.toURL().openConnection().getInputStream()))){
            StringBuilder str = new StringBuilder();
            String c;
            while( (c = br.readLine())!=null){
                str.append(c);
                str.append("\n");
            }
            return str.toString();
        } catch (MalformedURLException ex) {
            log.info("Any Problem");
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Input Output Exception");
        }
        return null;
    }
    
    
    public static void main(String[] args) {
       OpenLibraryISBN isbn = new OpenLibraryISBN("0892816635");
        System.out.println(isbn.getINFO());
    }
}

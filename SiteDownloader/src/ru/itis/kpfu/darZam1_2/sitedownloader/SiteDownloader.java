/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.sitedownloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author danis
 */
public class SiteDownloader {

    private URI uri;
    private int depth;
    private File root;
    private int numberOfPage = 0;
    
    
    private static final Logger log = Logger.getLogger(SiteDownloader.class.getName());
    
    
    public SiteDownloader(String uri, int depth, int numberOfPage){
        try {
            this.numberOfPage = numberOfPage;
            System.out.println(uri);
            this.uri = new URI(uri).normalize();
            this.depth = depth;
            Pattern p = Pattern.compile("css");
            Matcher m = p.matcher(uri);
            Pattern fonts = Pattern.compile(".+fonts.+");
            Matcher f = fonts.matcher(uri);
            if(m.find()){
                 root = new File ("html/css/".concat(String.valueOf(this.depth).concat(String.valueOf(this.numberOfPage).concat(".css"))));
                 if(!f.find()){
                    this.uri = new URI("http://tatphone.ru/".concat(this.uri.toString()));
                 }else{
                    this.uri = new URI("http://".concat(this.uri.toString().substring(2)));  
                     System.out.println(this.uri);
                 }
            }else{
                String str = uri.substring(0, 1);
               if(str.equals("/") ){
                  root = new File("html/".concat(String.valueOf(this.depth).concat(String.valueOf(this.numberOfPage).concat((".html")))));
                  this.uri = new URI("http://tatphone.ru".concat(this.uri.toString()));
               } else{
                  root = new File ("html/".concat(String.valueOf(this.depth).concat(String.valueOf(this.numberOfPage).concat((".html"))))); 
               }
            }
            root.createNewFile();
        } catch (URISyntaxException ex) {
            Logger.getLogger(SiteDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SiteDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }
    
    
    
    
    public void download(){
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(uri.toURL().openConnection().getInputStream()))){
           
            String str;
            StringBuilder string = new StringBuilder();
            System.out.println(root.getName());
            try (FileWriter bo = new FileWriter(root)) {
                while((str=bf.readLine())!=null){
                    bo.write(str);
                    string.append(str);
                }
                bo.flush();
                bo.close();

            }
            Document doc = Jsoup.connect(this.uri.toString()).get();
            Elements links = doc.select("a[href]");
           // System.out.println(links);
            
           
            Pattern site = Pattern.compile("(?<=href=\")[a-zA-Z0-9-:/./=*_?;/+,&]+");
           // Matcher m = site.matcher(links.toString());
            Matcher m = site.matcher(string);
            m.find();
            Pattern p = Pattern.compile("(.+/.+)");
            Matcher m1 ;
            Pattern img = Pattern.compile("(&amp)");
            Matcher m2;
            Pattern image = Pattern.compile("(image)");
            Matcher m3 ;
           
            while(m.find()&&depth!=0){
                m3 = image.matcher(m.group());
                if(!m.group().equals(this.uri.toString()) && !m3.find()){
                    m1 = p.matcher(m.group());
                    m2 = img.matcher(m.group());
                    String link = "";
                    link=m.group();
                    if( m2.find()){
                         link = m.group();
                         link = link.replaceAll("(amp;)","");
                    }
                    if(m1.find()){
                        SiteDownloader sd = new SiteDownloader(link, depth-1, this.numberOfPage);
                        this.setNumberOfPage(numberOfPage+1);
                         sd.download();   
                  }
                }
            }
        } catch (IOException ex) {
            log.info("IOException");
        }
    }
    
    public static void main(String[] args) {
        SiteDownloader st = new SiteDownloader("http://tatphone.ru", 1,0);
        st.download();
    }
    
}

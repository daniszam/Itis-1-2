/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kpfu.itis.darZamaliev1_2;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author danis_zam
 */
public class CheckHTML {

    private Document doc;
    private List<Integer> priceList;
    private List<String> nameList;
    private Map<String, Integer> mapOfProduct;
    private int size;

    public CheckHTML(String url) {
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException ex) {
            Logger.getLogger(CheckHTML.class.getName()).log(Level.SEVERE, null, ex);
        }
        priceList = new ArrayList<>();
        nameList =new ArrayList<>();
    }

    public void getPrice() {
        Elements el = doc.select(".price");
        Pattern p = Pattern.compile("(?<=price-new\">| )[0-9]+");
        Matcher m = p.matcher(el.toString());
        while (m.find() ) {
            priceList.add(Integer.parseInt(m.group()));
        }
        size = this.priceList.size();
    }
    
    public void getName(){
        Elements el = doc.getElementsByAttribute("alt");
        Pattern p = Pattern.compile("(?<=title=\")[a-zA-Zа-яА-Я 0-9]+");
        Matcher m = p.matcher(el.toString());
        m.find();
        while(m.find()){
            nameList.add(m.group());
        }
    }

    public String getTitle() {
        String title = doc.title();
        return title;
    }

    public void getMap(){
        this.getPrice();
        this.getName();
        mapOfProduct = new TreeMap<>();
        for (int i=0; i<size; i++){
            mapOfProduct.put(nameList.get(i), priceList.get(i));
        }
        priceList.clear();
        nameList.clear();
    }
    
    public void seeAllProduct(){
        int i=1;
        for(Map.Entry<String, Integer> item : mapOfProduct.entrySet()){
            System.out.println(i+". "+ item.getKey() +" " + item.getValue()+" руб.");
            i++;
        }
    //    i=1;
    }
    public static void main(String[] args) {
        CheckHTML ht = new CheckHTML("http://tatphone.ru");
        System.out.println("Title of this shop:" + " " + ht.getTitle());
        ht.getMap();
        ht.seeAllProduct();

    }
}

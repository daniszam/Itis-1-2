/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author danis_zam
 */
public class CSVReader {

    private String[] array;
    private File file;
    private String delimiter;

    public CSVReader(String csv) {
        file = new File(csv);
        delimiter = ";";
    }

    public CSVReader(String csv, String delimiter) {
        file = new File(csv);
        this.delimiter = delimiter;
    }

    public void readCSV() {
        try (FileReader dis = new FileReader(file)) {
            Scanner sc = new Scanner(file);
            int size =0;
            while (sc.hasNext()) {
                System.out.println(sc.next());
                size++;
            }
            sc.close();
            array = new String[size];
            Scanner sc1 = new Scanner (file);
           for(int i = 0; i<size; i++){
               array[i] = sc1.next();
           }
            Pattern p = Pattern.compile(",.+");
            Matcher m;
            Pattern p1 = Pattern.compile("^\"");
            Pattern p2 = Pattern.compile(".+\"\".+");
            for(int i =0; i<size; i++){
                m = p.matcher(array[i]);
                while(m.find()){
                  array[i] = array[i].substring(1);
                }
                m = p1.matcher(array[i]);
                 while(m.find()){
                  array[i] = array[i].substring(1, array[i].length()-1);
                }
                 m = p2.matcher(array[i]);
                  while(m.find()){
                  array[i] = array[i].replaceAll("\"\"", "\"");
                }
            }
            System.out.println(Arrays.toString(array));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

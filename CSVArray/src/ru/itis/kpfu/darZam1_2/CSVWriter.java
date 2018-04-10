/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author danis_zam
 */
public class CSVWriter {

    private File output;
    private String delimiter;

    public CSVWriter() {
        delimiter = ",";
    }

    public CSVWriter(String delimiter) {
        this.delimiter = delimiter;
    }

    public <T> void write2dArray(T[][] array) {
        output = new File("output1.csv");
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(output)))) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length; j++) {
                    if (j == array[0].length - 1) {
                        dos.writeUTF("" + array[i][j]);
                    } else {
                        dos.writeUTF(array[i][j] + delimiter);
                    }
                }
                dos.writeUTF("\n");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CSVWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public <T> void writeArray(T[] array1) {
        String[] array = new String[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array[i] = array1[i].toString();
        }
        Pattern p = Pattern.compile(".+(;|,|\n).+");
        Pattern p1 = Pattern.compile(".+\".+");
        Matcher m;
        for (int i =0; i<array.length; i++){
            m = p1.matcher(array[i]);
            if(m.find()){
                array[i] = array[i].replaceAll("\"", "\"\"");
            }
            m = p.matcher(array[i]);
            if(m.find()){
                array[i] = "\"".concat(array[i]).concat("\"");
            }
        }
        output = new File("output2.csv");
        String str ="";
        int size = array.length;
        int sqrt1 = Math.sqrt(size) > Math.round(Math.sqrt(size)) ? (int) Math.sqrt(size) : (int) Math.sqrt(size) + 1;
        try (FileWriter dos = new FileWriter(output)) {
            for (int i = 0; i < array.length;) {
                for (int j = 0; j < sqrt1; j++) {
                    if (i < array.length) {
                        if(j==sqrt1-1||i==array.length-1){
                            dos.write(array[i]+"\n");    
                        }else{ 
                          dos.write(array[i]+" "+delimiter);
                        }
                        i++;
                    }else{
                        break;
                    }
                }
                System.out.println(str);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CSVWriter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void writeFile(File file) {
        try {
            int size = 0;
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                size++;
                sc.next();
            }
            Scanner sc1 = new Scanner(file);
            Object[] array = new Object[size];
            for (int i = 0; i < size; i++) {
                array[i] = sc1.next();
            }
            this.writeArray(array);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVWriter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        Integer[][] array1 = new Integer[6][3];
        Integer[] array2 = new Integer[]{1, 3, 4, 5, 5, 6, 6, 6, 5, 4};
        String[] str = new String[]{"apple", "htc", "xiaomi", "df,df;d\"f","dfdfdf", "\'s,amsu\"cdffdfng\'", "alcatel", "huawei","\'s,amamam\'", "jbkhjkh", "jbkhjkh", "jbkhjkh", "jbkhjkh", "jbkhjkh", "jbkhjkh", "jbkhjkh", "jbkhjkh", "jbkhjkh", "jbkhjkh"};
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (int) (Math.random() * 100);
            }
        }
        CSVWriter writer = new CSVWriter();
         writer.writeFile(new File("file.txt"));
        writer.writeArray(str);
        writer.write2dArray(array1);
        CSVReader csvr = new CSVReader("output2.csv", ";");
        csvr.readCSV();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.Comparators;

import java.io.File;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author danis_zam
 */
public class ExtensionComparator implements Comparator<File> {

    @Override
    public int compare(File o1, File o2) {
        String o1Name = o1.getName();
        String o2Name = o2.getName();
         Pattern extension = Pattern.compile("(\\.)[a-zA-Z0-9]+$");
         Matcher m = extension.matcher(o1Name);
         Matcher m2 = extension.matcher(o2Name);
         boolean o1b = m.find();
         boolean o2b = m2.find();
         if(o1b&&!o2b){
             if(o1b){
                 return 1;
             } else{
                 return -1;
             }
         } else{
             if (o1b&&o2b){
                 return m.group().compareTo(m2.group());
             } else {
                 return -1;
             }
         }
    }
    
}

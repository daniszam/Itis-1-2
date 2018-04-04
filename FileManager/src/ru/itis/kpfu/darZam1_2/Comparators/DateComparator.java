/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.Comparators;

import java.io.File;
import java.util.Comparator;

/**
 *
 * @author danis_zam
 */
public class DateComparator implements Comparator<File> {

    @Override
    public int compare(File o1, File o2) {
        if (o1.lastModified()>o2.lastModified()){
            return 1;
        } else{
            return -1;
        }
    }

}

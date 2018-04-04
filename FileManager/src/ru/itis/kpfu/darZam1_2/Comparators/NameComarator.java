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
public class NameComarator implements Comparator<File> {

    @Override
    public int compare(File o1, File o2) {
        return o1.getName().compareTo(o2.getName());
    }
    
}

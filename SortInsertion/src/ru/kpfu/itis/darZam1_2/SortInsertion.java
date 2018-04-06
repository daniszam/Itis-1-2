/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kpfu.itis.darZam1_2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danis
 */
public class SortInsertion {

    private int[] array;
    private File file;
    private int size;

    public SortInsertion() {
        file = new File("input.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(SortInsertion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            Scanner sc = new Scanner(file);
            
            while (sc.hasNextInt()) {
                sc.nextInt();
                size += 1;
            }
            Scanner sc1 =new Scanner(file);
            array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = sc1.nextInt();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SortInsertion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sort() {
        for (int i = 1; i < size; i++) {
            int key = i;
            for (int j = i - 1; j >= 0; j--) {
                if (array[key] < array[j]) {
                    int t = array[key];
                    array[key] = array[j];
                    array[j] = t;
                    key--;
                } else{
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(array));
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("output.txt")))) {
            for (int i = 0; i < array.length; i++) {
                dos.writeInt(array[i]);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SortInsertion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SortInsertion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        SortInsertion si = new SortInsertion();
        si.sort();
    }
}

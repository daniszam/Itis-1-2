/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.selectionsort;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danis
 */
public class SelectionSort {
     private int[] array;
    private File input;
    private File output;
    private int size=0;
    
    public SelectionSort(){
        input = new File("input.txt");
        try(DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(input)))){
            Scanner sc = new Scanner(input);
            dis.mark(0);
            while(dis.available()>1){
                System.out.println( dis.readInt()+" "+size);
                size++;
            }
            dis.reset();
            array = new int[size];
            int i=0;
            while(dis.available()>0){
                array[i] = dis.readInt();
                i++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SelectionSort.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SelectionSort.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sortArray(){
        int min = 0;
        for(int i=0; i<size-1; i++){
            for(int j=i; j<size; j++){
                if(array[j]<array[min]){
                    min= j;
                }
            }
            if (min!=i){
                int k = array[min];
                array[min] = array[i];
                array[i] = k;
            }
        }
        System.out.println(Arrays.toString(array));
    }
    public static void main(String[] args) {
         try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("input.txt"))) {
            for (int i = 0; i<99; i++){
                int k = (int) (Math.random()*1000);
                dos.writeInt(k);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SelectionSort.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SelectionSort.class.getName()).log(Level.SEVERE, null, ex);
        }
        SelectionSort ss = new SelectionSort();
        ss.sortArray();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2;

import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danis
 */
public class BubbleSort {
    private int[] array;
    private File input;
    private File output;
    private int size=0;
    
    public BubbleSort(){
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
            Logger.getLogger(BubbleSort.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BubbleSort.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sortArray(){
        for (int i = 0 ; i<size; i++){
            for(int j = i+1; j<size; j++){
                if(array[j]<array[i]){
                    int k = array[i];
                    array[i] = array[j];
                    array[j] = k;
                }
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
            Logger.getLogger(BubbleSort.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BubbleSort.class.getName()).log(Level.SEVERE, null, ex);
        }
        BubbleSort bs = new BubbleSort();
        bs.sortArray();
    }
    
}

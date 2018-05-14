/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.mergesort;

import java.util.Arrays;

/**
 *
 * @author danis_zam
 */
public class MergeSort {
   
    
    public int[] sortMerge(int[] array){
        if(array.length<2){
            return array;
        }
        int middle = array.length/2;
        return merge (sortMerge(Arrays.copyOfRange(array, 0, middle)),
                sortMerge(Arrays.copyOfRange(array, middle, array.length)));
    }
    
    public int[] merge(int[] ar1, int[] ar2){
        int a = 0;
        int b= 0;
        int[] result  = new int[ar1.length+ar2.length];
        for(int i = 0 ; i<ar1.length+ar2.length; i++){
            if(b<ar2.length && a<ar1.length){
                result[i] = ar1[a]>ar2[b] ? ar2[b++] : ar1[a++] ;
            }else{
                result[i] = b < ar2.length ? ar2[b++] : ar1[a++];  
            }
        }
        return result;
    }
    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        System.out.println(Arrays.toString(ms.sortMerge(new int[]{23,56,3,5,7,8,45,4545,767,6,5,7})));
    }
    
}

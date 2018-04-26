/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.timsort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author danis_zam
 */
public class Timsort {

    private int size;
    private int minrun;
    private int[] array;
    private int count = 0;
    private Deque<Integer> stackIndex;
    private Deque<Integer> stackLen;
    
    public Timsort(int[] array){
        this.array = array;
        stackLen = new ArrayDeque<>();
        stackIndex = new ArrayDeque<>();
        this.minrun = this.getMinrun(array.length);
        
 
    
    }
    
    
    public void sort(){
         while(count<array.length){
            this.getRun();
        }
       // System.out.println(Arrays.toString(array));
        while(stackLen.size()>0){
          this.canMerge();
           //System.out.println(stackLen.size());
        }
      //  System.out.println(Arrays.toString(array));
    }
    
    public void random(){
        for(int i =0 ; i<array.length;i++){
            array[i] = (int)(Math.random()*10000);
        }
    }
    private int getMinrun(int n){
        int r = 0;
        while(n>= 64){
           r |= n & 1;
           n>>=1;
        }
        return n+r;
    }
    
    public void getRun(){
        int i = count;
        size = 0;
        boolean type = false ;  
        if((i+1)<(array.length-1)){
            while(array[i]>array[i+1]){
                if((i+1)>(array.length-1)){
                       break;
                }
                type = true;
                i++;
                size++;
            } 
        }
        if(i==count){
            if((i+1)<(array.length-1)){
                while(array[i]<=array[i+1]){
                    if((i+1)>(array.length-1)){
                             break;
                    }
                    i++;
                    size++;
                }
            }    
        }
        if(type){
           int k = i;
           for (int j = count; j<=k;j++){
               int t = array[k] ;
               array[k] = array[j];
               array[j] = array[k];
           }
        }
        if(size<minrun){
            i = count+minrun;
            size = i > array.length? (array.length-count-1) : minrun;    
        }
        stackIndex.add(count);
        stackLen.add(size);
        this.insertionSort(count, size);
        count = i;
    }
    
     public void insertionSort(int index, int size) {
        for (int i = index+1; i < index+size; i++) {
            int key = array[i];
            int j = i-1;
            while(j>=index &&array[j]>key){
                array[j+1] = array[j];
                j = j -1;
            }
            array[j+1] = key;
        }
     }
     
     public boolean hoIsBigger(int length1, int length2){
         if(length1-length2<minrun){
             return true;
         }else{
             return false;
         }
     }
     
     public void canMerge(){
         if(stackLen.size()==0){
             return;
         }
         if(stackLen.size()>2){
            int x = stackIndex.pollFirst();
            int y = stackIndex.pollFirst();
            int z = stackIndex.pollFirst();
            int xlen = stackLen.pollFirst();
            int ylen = stackLen.pollFirst();
            int zlen = stackLen.pollFirst();
            if(this.hoIsBigger(xlen, ylen)){
                stackLen.push(zlen);
                stackIndex.push(z);
                merge(x, xlen, y,ylen);
            } else{
                if((xlen<(ylen+zlen)) && ylen>zlen){
                   stackLen.push(zlen);
                   stackIndex.push(z);
                   merge(x, xlen, y, ylen); 

                }else{
                   if(xlen<=zlen){  stackLen.push(zlen) ; stackIndex.push(z); merge(x, xlen, y, ylen ); }
                   else{
                       List index =new ArrayList();
                       index.addAll(stackIndex);
                       List length = new ArrayList();
                       length.addAll(stackLen);
                       stackLen.clear();
                       stackIndex.clear();
                       boolean end = false;
                       boolean isMerge =false;
                       while(!isMerge){
                            if(this.hoIsBigger(ylen, zlen)){ 
                                merge(y, ylen, z , zlen);
                                stackLen.pollFirst();
                                stackIndex.pollFirst();
                                stackLen.addLast(ylen+zlen);
                                
                                stackIndex.addLast(y);
                                stackLen.addAll(length);
                                stackIndex.addAll(index);
                                
                                isMerge = true;
                            }else{   
                               stackIndex.addLast(y);
                               stackLen.addLast(ylen);
                               
                               y = z;
                               ylen = zlen;
                               if(index.size()<=1){
                                   if(index.size()!=0){
                                    z = (int)index.get(0);
                                    zlen = (int) length.get(0);
                                    index.remove(0);
                                    length.remove(0);                                        
                                        merge(y, ylen, z , zlen);
                                        stackLen.pollFirst();
                                        stackIndex.pollFirst();
                                        stackLen.add(ylen+zlen);
                                        stackIndex.add(y);
                                        end = true;
                                       
                                   }else{
                                       System.arraycopy(array, y, array, y, ylen);
                                   }
                                    isMerge = true;
                               }else{
                                    z =(int)index.get(0);
                                    zlen = (int)length.get(0);
                                    index.remove(0);
                                    length.remove(0);
                               }
                            }
                       } stackLen.push(xlen);
                                stackIndex.push(x);   
                                if(end){
                                    return;
                                }
                   }
                }
            }
         } else{ 
             if(stackLen.size()>1){ merge(stackIndex.pollFirst(), stackLen.pollFirst(), stackIndex.pollFirst(), stackLen.pollFirst());} 
             else{
                int x = stackIndex.pollFirst() ;
                int y =stackLen.pollFirst();
                 merge(0,array.length-y,x,y);
             }
         }
     }
     
     public void merge(int index, int length, int index1, int length1){
         int[] sortAr = new int[length1+length];
            int[] x ;
            int number = 0;
            int number1 = 0;
            int b = 0;
            int c;
            int cSize;
            int bSize;
            int bStart;
            
        if(length<=length1){ 
            x = Arrays.copyOfRange(array, index,index + length);
             c= index1;
             cSize = index1+length1;
             bSize = length;
             bStart = index;
        }else{
             x = Arrays.copyOfRange(array, index1,index1 + length1);
             c= index;
             cSize = index+length;
             bSize = length1;
             bStart = index1;
        }
         for(int i = 0 ; i<length+length1; i++){
            if(b<x.length && c<cSize){
                if(x[b]>array[c]){
                    sortAr[i] = array[c++];
                    number++;
                }else{
                    sortAr[i] = x[b++];
                    number1++;
                }
            }else{
                sortAr[i] = c < cSize? array[c++] : x[b++];  
            }
            if(number-number1>6){
                int z = binarysearch(c, cSize-c, x[b]);
                System.arraycopy(array, c, sortAr, i, z-c);
                number = 0;
                number1=0;
                i= i+(z-c);
                c = z;
            } else{
                if(number1-number>6){
                    int z = binarysearch(b+bStart, bSize-b, array[c]);
                    System.arraycopy(x, b, sortAr, i, z-bStart-b);
                    number = 0;
                    number1=0;
                    i += (z-bStart-b);
                    b=z-bStart;
                }
            }
        }
         if((length1+length)!=array.length){
            stackLen.push(length1+length);
            stackIndex.push(index);
         }
//         for(Object mas: stackIndex.toArray() ){
//             System.out.print(mas+" ");
//         }
//         System.out.println("");
//         for(Object mas: stackLen.toArray() ){
//             System.out.print(mas+" ");
//         }
//         System.out.println("");
       //  System.out.println(Arrays.toString(array));
    //     System.out.println("sort"+Arrays.toString(sortAr));
     //  System.arraycopy(sortAr, 0, array, index, length+length1);
     }
     
     public int binarysearch(int first, int length, int max){
         if(array[first] == max || length<1){
             return first;
         }
         int middle = first + length/2;
         if(array[middle]<max){
             return binarysearch(middle, length/2, max);
         }else{
             return binarysearch(first, length/2 , max);
         }
     }
     

    
    public static void main(String[] args) {
        
        

        
        int sum =0;
     //   CSVWriter cs = new CSVWriter();
//        List ar = new ArrayList();
        for(int i =99; i<100; i++){
//            long st = System.currentTimeMillis();
            int[] z = new int[1000000];
        for(int j =0 ; j<z.length;j++){
             z[j] = (int)(Math.random()*10);
         }
    //   Arrays.sort(z);
           Timsort tms = new Timsort(z);
//            tms.random();
            tms.sort();
//            long finish = System.currentTimeMillis();
//            sum += st-finish;
//          //  System.out.println((double)(finish-st)/1000.000000);
//            ar.add((double)(finish-st)/1000.000000);
        }
      //  cs.writeArray(ar.toArray());
     //   System.out.println((double)sum/100.0000);
//        long finish = System.currentTimeMillis();
//        System.out.println((double)(finish-st)/1000.000000);
    }
    
    
}

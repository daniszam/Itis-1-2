/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.timsort;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

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
        while(stackLen.size()>0){
          this.canMerge();
        }
        System.out.println(Arrays.toString(array));
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
     
     public void canMerge(){
         if(stackLen.size()==0){
             return;
         }
         if(stackLen.size()>2){
            int x = stackIndex.pollFirst();
            int y = stackIndex.pollFirst();
            int z = stackIndex.pollFirst();
            int xlen = stackLen.pollFirst();
            // System.out.println(xlen);
            int ylen = stackLen.pollFirst();
            int zlen = stackLen.pollFirst();
            if(xlen==ylen){
                stackLen.push(zlen);
                stackIndex.push(z);
                merge(x, xlen, y,ylen);
            } else{
                if((xlen>(ylen+zlen)) && ylen>zlen){
                   stackLen.push(zlen);
                   stackIndex.push(z);
                   merge(x, xlen, y, ylen); 

                }else{
                   if(xlen<=zlen){  stackLen.push(zlen) ; stackIndex.push(z); merge(x, xlen, y, ylen ); }
                   else{ merge(y, ylen, z , zlen);  stackLen.push(xlen) ; stackIndex.push(x);
//                            for(Object mas: stackIndex.toArray() ){
//                                    System.out.print(mas+" ");
//                            }
                   }
                }
            }
         } else{ 
             if(stackLen.size()>1){ merge(stackIndex.pollFirst(), stackLen.pollFirst(), stackIndex.pollFirst(), stackLen.pollFirst());} 
             else{
                 int x = stackIndex.pollFirst() ;
                 System.arraycopy(array, x, array,x , stackLen.pollFirst());
             }
         }
     }
     
     public void merge(int index, int length, int index1, int length1){
        // System.out.println(index);
        int[] x = Arrays.copyOfRange(array, index,index + length);
        int number = 0;
        int number1 = 0;
        int ind =index;
        int b = 0;
        int c= index1;
//          for(Object mas: stackIndex.toArray() ){
//             System.out.print("bef"+mas+" ");
//         }
//         
//         System.out.println("");
         for(int i = 0 ; i<length+length1; i++){
            if(b<x.length && c<index1+length1){
                if(x[b]>array[c]){
                    array[ind++] = array[c++];
                    number++;
                }else{
                    array[ind++] = x[b++];
                    number1++;
                }
            }else{
              //  System.out.println(index+ "i"+i+" "+ (length1+length));
                array[ind++] = c < index1+length1? array[c++] : x[b++];  
            }
            if(number-number1>6){
                int z = binarysearch(c, index1-c, x[b]);
                System.arraycopy(array, c, array, ind, z-c);
                number = 0;
                number1=0;
                i= i+(z-c);
                ind=ind+z-c;
            } else{
                if(number1-number>6){
                    int z = binarysearch(b+index, length-b, array[c]);
                    System.arraycopy(x, b, array, ind, z-index-b);
                    number = 0;
                    number1=0;
                    i += (z-index-b);
                    ind = ind + z - index -b;
                    b+=z-index-b;
                }
            }
        }
         stackLen.push(length1+length);
         stackIndex.push(index);
//         for(Object mas: stackIndex.toArray() ){
//             System.out.print(mas+" ");
//         }
//         System.out.println("");
//         for(Object mas: stackLen.toArray() ){
//             System.out.print(mas+" ");
//         }
//         System.out.println("");
     }
     
     public int binarysearch(int first, int length, int max){
         if(array[first] == max || length<2){
             return first;
         }
         int middle = first + length/2;
         if(array[middle]<max){
             return binarysearch(middle-1, length/2, max);
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
             z[j] = (int)(Math.random()*10000);
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

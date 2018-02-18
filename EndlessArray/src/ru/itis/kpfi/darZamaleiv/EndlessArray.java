/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfi.darZamaleiv;

/**
 *
 * @author danis_zam
 * @param <T>
 */
public class EndlessArray<T> {
    protected int count;
     protected int capacity = 16;
     protected T[] arr;
     protected T[] otherArr;
    /**
     * @param args the command line arguments
     */
    public int add(T p) {
        count ++;
        capacity++;
        arr = (T[]) new Object[count];
        for (int i = 0 ; i<count - 1; i++){
            arr[i] = otherArr[i];
        }
        arr[count - 1] = p ;
        otherArr =(T[]) new Object[count];
        for (int i =  0; i<otherArr.length; i++){
            otherArr[i] = arr[i];
        }
        return capacity - 1;
    }
    
    public boolean remove (int i){
        if (i< 0 || i>= capacity - 1){
            return false;
        } else {
            for ( int j = i; j<arr.length - 1 ; i++){
                otherArr[j] = arr[j+1];
            }
        }
        count --;
        return true;
    }
    
    public T get (int i) {
        return otherArr[i];
    }
    
}


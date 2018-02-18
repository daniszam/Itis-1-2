/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kpfu.itis.darZamaliev1_2;

/**
 *
 * @author danis_zam
 */
public class Queue<T> {

    private int capacity = 0;
    private int count = 0;
    private T[] arr;
    private T[] otherArr;
    

    public boolean offer(T t) {
        count++;
        arr = (T[]) new Object[count];
        for (int i = 0; i < count - 1; i++) {
            arr[i] = otherArr[i];
        }
        arr[count - 1] = t;
        otherArr = (T[]) new Object[count];
        for (int i = 0; i < otherArr.length; i++) {
            otherArr[i] = arr[i];
        }
        if (otherArr[count - 1] == t) {
            return true;
        } else {
            return false;
        }

    }

    public T remove() throws Exception {
          if (count ==0){
              throw new NullPointerException("Очередь пуста");
          }
         T t = otherArr[capacity];
         otherArr[capacity] = null;
         capacity ++;
         return t;
    }

    public T poll() {
        if (count == 0 ){
            return null;
        }
          T t = otherArr[capacity];
         otherArr[capacity] = null;
       capacity ++;
       return t;
    }

    public T element() throws Exception {
        if (count == 0 ){
            throw new NullPointerException("Очередь пуста");
        }
        capacity++;
        return otherArr[capacity -1];
        
    }

    public T peek() {
        if (count == 0 ){
            return null;
        }
        capacity ++;
        return otherArr[capacity-1];
    }

}

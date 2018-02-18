/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZamaliev1_2;

import java.util.ArrayList;

/**
 *
 * @author danis_zam
 * @param <T>
 * @param <T1>
 */
public class ListOfStudent<T, T1 extends Number> {
    private ArrayList<T1> array1 = new ArrayList<>();
    private ArrayList<T> array = new ArrayList<>();
    private double average;
    
    
    public void add (T t, T1 t1){
        array.add(t);
        array1.add(t1);
    }
    
  public double average(){
      average =0;
        for (int i = 0; i<array1.size(); i++){
            average += array1.get(i).doubleValue();
        }
       average = average/(array1.size());
       return average;
    }

    
    public boolean deleate (T t){
        int x = array.indexOf(t);
        array.remove(x);
        array1.remove(x);
        return true;
    }
    
    public boolean deleate (T1 t1){
        int x  = array1.indexOf(t1);
        array1.remove(x);
        array.remove(x);
        return true;
    }
    
    public void readAll() {
        for (int i = 0 ; i<array.size(); i++){
            System.out.println(array.get(i)+" - "+array1.get(i));
        }   
    }
}

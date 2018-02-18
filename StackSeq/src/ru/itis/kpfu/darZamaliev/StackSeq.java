/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZamaliev;
    
/**
 *
 * @author danis_zam
 */
public class StackSeq implements ISequence {
    private Object [] a = new Object[10];
    private int capacity = 0 ;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StackSeq b = new StackSeq();
        b.push(100);
        b.push(200);
        System.out.println(b.pop());
        System.out.println( b.pick());
    }
    
    @Override
    public void push(Object o){
        for (int i = 0; i<a.length; i++){
            if  (a[i] == null){
                a[i]=o;
                break;
            }
        }
    }
    
    @Override
  public Object pick(){
       if (a[a.length-1]==null){
            for ( int i = 0; i<a.length;  i++){
                if(a[i]==null){
                    return a[i-1];
                }
            }
        }
        else{ 
             return a[a.length-1]; 
        }
        return null;
       
  }
    
    @Override
    public Object pop(){
        if (a[a.length-1]==null){
            for ( int i = 0; i<a.length;  i++){
                if(a[i]==null){
                    Object b = a[i-1];
                    a[i-1]= null;
                    return b;
                }
            }
        }
        else{ 
            Object b = a[a.length-1] ;
            a[a.length-1] = null;
             return b; 
        }
        return null;
    
}
    
}

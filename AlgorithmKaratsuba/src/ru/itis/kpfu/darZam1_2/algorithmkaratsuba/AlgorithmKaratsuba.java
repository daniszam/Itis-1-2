/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.algorithmkaratsuba;

import java.util.Arrays;

/**
 *
 * @author danis_zam
 */
public class AlgorithmKaratsuba {

    private boolean[] x1;
    private boolean[] x2;
    private boolean[] y1;
    private boolean[] y2;
    private double base =0;

    public AlgorithmKaratsuba(int x, int y) {
        String x1 = Integer.toBinaryString(x);
        String y1 = Integer.toBinaryString(y);
        int maxLength = x1.length() > y1.length() ? x1.length() : y1.length();
        maxLength = maxLength % 2 == 1 ? maxLength + 1 : maxLength;
        this.x1 = new boolean[maxLength / 2];
        this.y1 = new boolean[maxLength / 2];
        this.x2 = new boolean[maxLength / 2];
        this.y2 = new boolean[maxLength / 2];
        for (int i = maxLength - 1; i >= 0; i--) {
            if (i <maxLength / 2) {
                this.x1[i] = x % 2 == 1;
            } else {
                this.x2[i-maxLength/2] = x % 2 == 1;
            }
            x /= 2;
        }
        for (int i = maxLength - 1; i >= 0; i--) {
            if (i < maxLength / 2 ) {
                this.y1[i] = y % 2 == 1;
            } else {
                this.y2[i-maxLength/2] = y % 2 == 1;
            }
            y /= 2;
        }
        base = this.x1.length;
     //   System.out.println(x1 + " " + Arrays.toString(this.x2) + " " + y1 + " " + Arrays.toString(this.y1)+base);
       // this.sumAB(this.x2, this.y2);
        this.karatsuba();
    }
    
    
    public boolean[] multiply(boolean[] x1, boolean[] y1){
        boolean[] x11 = x1;
        boolean[] y11 = y1;
        if(x1.length!=y1.length){
            if(x1.length<y1.length){
               System.arraycopy(x11, 0, x1 = new boolean[y1.length], y11.length-x11.length, x11.length);
            } else{
               System.arraycopy(y11, 0, y1 = new boolean[x1.length], x11.length-y11.length, y11.length);
            }
        }
       boolean[] sum = new boolean[y1.length+1];
       for (int i = x1.length-1; i>=0;i--){
           if(i==x1.length-1){
               if(x1[i]){
                  System.arraycopy(y1, 0, sum, 1, y1.length); 
               }
               i--;
           }
           boolean[] m11 = new boolean[2*y1.length-i-1];
           if (x1[i]){
             System.arraycopy(y1, 0, m11, 0, y1.length);
           }
           
           if (sum.length == this.sumAB(m11, sum).length){
              boolean[] sum1 = this.sumAB(m11, sum); 
              sum = new boolean[sum.length+1];
              System.arraycopy(sum1, 0, sum, 1, sum1.length);
           }else{
               sum = this.sumAB(m11, sum);
           }
       }
       return sum; 
    }
    
    public boolean[] sumAB(boolean[] x1, boolean[] y1){
        boolean[] x11 = x1;
        boolean[] y11 = y1;
        if(x1.length!=y1.length){
            if(x1.length<y1.length){
               System.arraycopy(x11, 0, x1 = new boolean[y1.length], y11.length-x11.length, x11.length);
            } else{
               System.arraycopy(y11, 0, y1 = new boolean[x1.length], x11.length-y11.length, y11.length);
            }
        }
        boolean[] x1y1 = new boolean[x1.length];
        boolean a = false;
        for (int i=x1.length-1; i>=0; i--){
           if(x1[i]){
               if(y1[i]){
                   if(a){
                       x1y1[i] = true;
                       a = true;
                   } else { x1y1[i] = false; a = true; }
               } else{ x1y1[i] = !(a); }                 
           }else{
              if (y1[i]&a){
                  x1y1[i] = false;
              } else{ 
                  if(y1[i]|a){
                      x1y1[i] = true;
                      a = false;
                  }   
              }
           }
        }
        if(a){
            boolean[] xy = new boolean [x1y1.length+1];
            System.arraycopy(x1y1, 0, xy, 1, x1y1.length);
            xy[0] = true; 
            return xy;
        }
        return x1y1;
    }
    
    public boolean[] cut(boolean[] array){
       int size = array.length;
       for(int i =0 ; i<array.length; i++){
           if(array[i]){
               break;
           }
           size--;
       }
        boolean[] xy = new boolean[size];
        System.arraycopy(array, array.length-size, xy, 0, xy.length);
        return xy;
    }
    public void karatsuba(){
        boolean[] x1y1 = this.multiply(this.x1, this.y1);
        x1y1 = this.cut(x1y1);
        boolean[] x1y2 = this.multiply(this.x1, this.y2);
        x1y2 = this.cut(x1y2);
        boolean[] x2y1 = this.multiply(this.x2,this.y1);
        x2y1 = this.cut(x2y1);
        boolean[] x2y2 = this.multiply(this.x2, this.y2);
        x2y2 = this.cut(x2y2);
        boolean[] baseAr = new boolean[(int)base*2+1];
        baseAr[0] = true;
        x1y1 = this.multiply(x1y1, baseAr);
        x1y1 = this.cut(x1y1);
        baseAr = new boolean[(int) base+1];
        baseAr[0] = true;
        x1y2 = this.sumAB(x1y2, x2y1);
        x1y2 = this.cut(this.multiply(this.cut(x1y2), baseAr)) ;
        x1y1 = this.cut(this.sumAB(x1y1, x1y2));
        x1y1 = this.cut(this.sumAB(x1y1, x2y2));
        int sum =0;
        for (int i = 0; i<x1y1.length; i++){
            if (x1y1[i]){
                sum +=(int) Math.pow(2, x1y1.length-i-1);
            }
        }
        System.out.println(sum);
        
    }

    public static void main(String[] args) {
        AlgorithmKaratsuba ak = new AlgorithmKaratsuba(2333, 8888);
    }

}

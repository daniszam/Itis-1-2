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
public class App {
    public static void main(String[] args) throws Exception{
        Queue<Integer> q1 = new Queue<>();
        q1.offer(35);
        q1.offer(37);
        q1.offer(3233);
        System.out.println(q1.remove());
        System.out.println(q1.poll());
        q1.offer(25);
        System.out.println(q1.remove());
        System.out.println(q1.element());
        
    }
 
}



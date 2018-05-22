/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.treap;

/**
 *
 * @author danis_zam
 */
public class App {
    public static void main(String[] args) {
        Treap<Integer, Integer> treap = new Treap<>();
        treap.add(10, 5);
        treap.add(8, 8);
        treap.add(9, 7);
        treap.add(6, 3);
        treap.printTreap();
        
    }
    
}

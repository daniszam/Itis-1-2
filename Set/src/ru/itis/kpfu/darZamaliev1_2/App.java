/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZamaliev1_2;

/**
 *
 * @author danis_zam
 */
public class App {
    public static void main(String[] args) {
    Set<Integer> a = new Set<>();
   a.insert(23);
   a.insert(23);
   a.insert(26);
   a.insert(29);
   a.remove(23);
   a.insert(23);
        System.out.println(a.size());
        System.out.println(a.has(29));
}
}
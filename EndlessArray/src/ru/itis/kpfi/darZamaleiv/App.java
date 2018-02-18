/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfi.darZamaleiv;

import java.util.Arrays;

/**
 *
 * @author danis_zam
 */
public class App {
    
    public static void main(String[] args){
    EndlessArray<Integer> box = new EndlessArray<Integer>();
    box.add(23);
    EndlessArray<String> box1 = new EndlessArray<String>();
    box1.add("sssss");
    System.out.println(box.get(0)+box1.get(0));
    }
}

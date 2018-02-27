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
public class App {

    public static void main(String[] args) {
        Map<Integer, String> map = new Map<>();
        map.put(0, "Ivan");
        map.put(1, "Ivan1");
        map.put(2, "Ivan2");
        map.put(3, "Ivan3");
        map.put(4, "Ivan4");
        map.put(5, "Ivan5");
        map.put(6, "Ivan6");
        map.put(7, "Ivan7");
        map.put(8 , "Ivan8");
        map.put(9 , "Ivan9");
        System.out.println(map.size());
        System.out.println(map.get(8));
        System.out.println(map.get(24));
        System.out.println(map.searchKey(6));
        System.out.println(map.containsKey(7));
        System.out.println(map.containsValue("Ivan2"));
        System.out.println(map.remove(5));
        System.out.println(map.get(5));
        Map<Integer, String > map1 = new Map<>();
        map1.putAll(map);
        System.out.println(map1.equals(map));
         map.clear();
        System.out.println(map.get(3));
        System.out.println(map1.get(4));
        
    }
}

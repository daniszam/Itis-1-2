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
        ListOfStudent<String, Integer> students = new ListOfStudent<>();
        students.add("Ilya Oblomov", 77);
        students.add("vasya Polezhaikin", 86);
        students.add("Petr First", 96);
        students.readAll();
        System.out.println(students.average());
        students.deleate(86);
        students.readAll();
        System.out.println(students.average());
        students.deleate("Ilya Oblomov");
        students.readAll();
        System.out.println(students.average());
        
    }
}

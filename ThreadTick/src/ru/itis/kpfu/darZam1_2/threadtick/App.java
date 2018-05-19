/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.threadtick;

/**
 *
 * @author danis_zam
 */
public class App {
    public static void main(String[] args) {
        
    
    ThreadTick thread = new ThreadTick();
    ConsoleClient cs = new ConsoleClient(thread);
    Thread o = new Thread(thread);
    Thread o1 = new Thread(cs);
    o.start();
    o1.start();
    }
}

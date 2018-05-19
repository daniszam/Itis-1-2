/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.threadtick;

import java.util.Scanner;

/**
 *
 * @author danis_zam
 */
public class ConsoleClient implements Runnable {
    private ThreadTick thread;
    private Thread o;
    
    public ConsoleClient(ThreadTick o){
        this.thread = o;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while(true){
           if(sc.nextLine().equals("get")){
               System.out.println(thread.getTick());
           } 
        }
    }
    
    
}

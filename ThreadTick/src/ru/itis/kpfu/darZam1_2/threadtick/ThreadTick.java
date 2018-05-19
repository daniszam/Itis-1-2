/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.threadtick;

import com.sun.istack.internal.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author danis_zam
 */
public class ThreadTick implements Runnable {

    private int tick = 0;
    private Thread o;

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    @Override
    public void run() {
        while (true) {
            this.tick++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(ThreadTick.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}

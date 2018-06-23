/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danis
 */
public class Consumer implements Runnable {
    private Product product;

    public Consumer(Product product) {
        this.product = product;
    }
    
    
    @Override
    public void run() {
        while(true){
            synchronized(product){
                while(product.getCount()<3){
                    try {
                        product.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println(product.getCount());
                product.notify();
                product.used();
            }
        }
    }
    
}

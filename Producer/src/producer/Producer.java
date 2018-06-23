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
public class Producer implements Runnable{
    private Product product;
    
    public Producer(Product product){
        this.product = product;
    }
    

    @Override
    public void run() {
        while(true){
            synchronized(product){
                while(product.getCount()>=3){
                    try {
                        product.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                product.notify();
                product.create();
                
            }
        }
    }

   
    
}

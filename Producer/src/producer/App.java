/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer;

/**
 *
 * @author danis
 */
public class App {
    public static void main(String[] args) {
        Product product = new Product("Milk", 60);
        Producer pr = new Producer(product);
        Consumer cs = new Consumer(product);
        new Thread(pr).start();
        new Thread(cs).start();
    }
}

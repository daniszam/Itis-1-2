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
public class Product {
   private String name;
   private int price;
   private int count;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
        this.count = 0;
    }

    public int getCount() {
        return count;
    }
    public void used(){
        count -=1;
    }
    public void create(){
        count+=1;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
   
   
}

package javaapplication14;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ist
 */
public class Product implements Serializable{
    private String n;
    private int price;

    public Product(String name, int price) {
        this.n = name;
        this.price = price;
    }

    public String getN() {
        return n;
    }

    public void setN(String name) {
        this.n = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.n);
        hash = 97 * hash + this.price;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.price != other.price) {
            return false;
        }
        if (!Objects.equals(this.n, other.n)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "name=" + n + ", price=" + price + '}';
    }
    
}

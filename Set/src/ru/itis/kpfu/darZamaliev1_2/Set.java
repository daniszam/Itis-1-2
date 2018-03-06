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
public class Set<T> {

    private T[] t;
    public int size;
    private int capacity = 32;

    public Set() {
        t = (T[]) new Object[capacity];
    }

    public void insert(T t1) {
        if (size >= capacity) {
            T[] newData = (T[]) new Object[t.length + 1];
            System.arraycopy(t, 0, newData, 0, t.length);
            t = newData;
            capacity += 1;
        }
        if (size == 0) {
            t[0] = t1;
            size++;
        } else {
            for (int i = 0; i < size; i++) {
                if (t[i].equals(t1)) {
                    System.out.println("такой элемент уже есть");
                    return;
                }
            }
            t[size] = t1;
            size++;
        }
    }

    public boolean has(Object o) {
        for (int i = 0; i < size; i++) {
            if (t[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    public void remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (t[i].equals(o)) {
                t[i] = null;
                System.arraycopy(t, i + 1, t, i, size - i - 1);
                size --;
                break;
            }
        }
    }

    public int size() {
        return size;
    }

}

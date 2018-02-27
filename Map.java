/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZamaliev;

import java.util.Arrays;


/**
 *
 * @author danis_zam
 * @param <K>
 * @param <V>
 */
public class Map<K, V> {

    private K[] t;
    private V[] t1;
    private int capacity = 32;
    private int count = 0;

    public Map() {
        t = (K[]) new Object[capacity];
        t1 = (V[]) new Object[capacity];
    }

    public V get(Object key) {
        for (int i = 0; i < count; i++) {
            if (t[i].equals(key)) {
                return t1[i];
            }
        }
        return null;
    }

    public V put(K key, V value) {
        if (count > capacity - 1) {
            System.out.println("Максимально количество элементов");
        } else {
            if (searchKey(key) == -1) {
                t[count] = key;
                t1[count] = value;
                count++;
                return null;
            } else {
                V a = t1[searchKey(key)];
                t1[searchKey(key)] = value;
                return a;
            }
        }
        return null;
    }

    protected int searchIndexOfKey(K key) {
        for (int i = 0; i < count; i++) {
            if (t[i].equals(key)) {
                return i;
            } 
        }
        return -1;
    }

    public boolean containsValue(Object value) {
        for (int i = 0; i < count; i++) {
            if (t1[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsKey(Object key) {
        for (int i = 0; i < count; i++) {
            if (t[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return count;
    }

    public void clear() {
        for (int i = 0; i < count; i++) {
            t[i] = null;
            t1[i] = null;
        }
        count = 0;
    }

    public V remove(Object key) {
        int x = searchKey((K) key);
        if (x != -1) {
            V v = t1[x];
            t1[x] = null;
            return v;
        } else {
            return null;
        }
    }

    public K[] getT() {
        return t;
    }

    public V[] getT1() {
        return t1;
    }

    public int getCount() {
        return count;
    }
    
    
    public void putAll( Map <? extends K , ? extends V > m ){
        this.count= m.getCount();
    System.arraycopy(m.getT(), 0, t, 0, m.getCount());
     System.arraycopy(m.getT1(), 0, t1, 0, m.getCount());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Arrays.deepHashCode(this.t);
        hash = 29 * hash + Arrays.deepHashCode(this.t1);
        hash = 29 * hash + this.capacity;
        hash = 29 * hash + this.count;
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
        final Map<?, ?> other = (Map<?, ?>) obj;
        if (this.capacity != other.capacity) {
            return false;
        }
        if (this.count != other.count) {
            return false;
        }
        if (!Arrays.deepEquals(this.t, other.t)) {
            return false;
        }
        if (!Arrays.deepEquals(this.t1, other.t1)) {
            return false;
        }
        return true;
    }
    

}



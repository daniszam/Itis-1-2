/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darbinaryheap;

/**
 *
 * @author danis_zam
 */
public class App {
    public static void main(String[] args) {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(2);
        heap.add(6);
        heap.add(4);
        heap.add(8);
        heap.add(9);
        heap.add(33);
        heap.add(22);
        heap.add(45);
       heap.add(29);
        heap.add(1);
        heap.add(0);
        heap.add(99);
        heap.printHeap();
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.binarytree;

/**
 *
 * @author danis_zam
 */
public class App {
    public static void main(String[] args) {
//        BinaryTree<Integer> bt = new BinaryTree<>();
//        bt.add(9);
//        bt.add(7);
//        bt.add(10);
//        bt.add(11);
//        bt.add(3);
//        bt.add(1);
//        bt.add(4);
//        bt.printTree();
//        bt.remove(3);
//        bt.printTree();
        AVL<Integer> avl = new AVL<>();
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(6);
        avl.add(22);
        avl.add(15);
        avl.add(196);
        avl.add(87);
        avl.add(45);
        avl.add(17);
        avl.add(25);
        avl.add(23);
        avl.add(1000);
        avl.add(14);
        avl.add(9);
        avl.add(11);
        avl.add(201);
        avl.add(41);
        avl.add(64);
        avl.add(19);
        avl.add(91);
        System.out.println("parent    "+avl.contains(15).getParent().getValue());
        avl.printTree();
    //    avl.printTree();
    }
}

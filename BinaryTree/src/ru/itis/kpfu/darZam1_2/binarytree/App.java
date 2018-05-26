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
        avl.printTree();
        avl.remove(3);
    //    avl.printTree();
    }
}

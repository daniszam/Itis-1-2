/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darbinaryheap;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author danis_zam
 */
public class BinaryHeap<T extends Comparable<T>> {
    private Node<T> root;
    private Node<T> now;
    
    public BinaryHeap(){
        this.root = null;
        this.now = null;
    }
    
    

    public void add(T element){
       if(root==null){
           this.root =new Node(element, null, null, null);
           now = this.bfs();
       }else{
           now.setX(element);
           //this.printHeap();
           this.restablish(now);
         //  this.printHeap();
           now = this.bfs();  
       }    
    }
       
    private void restablish(Node<T> element){
      while(element.getParent()!=null && element.getX().compareTo(element.getParent().getX())>=0){
         T x = element.getX();
        //  System.out.println("chhheck"+x+" "+element.getParent().getX());
         element.setX(element.getParent().getX());
         element.getParent().setX(x);
         element = element.getParent();
       //   System.out.println("element"+element.x+element.getLeft().x);
      }  
    }
    
    public Node<T> bfs(){
        List<Node<T>> nodes = new LinkedList<>();
        Node<T> check = root;
        nodes.add(check);
        while(check!=null){
            if(check.getLeft()==null){
                check.left = new Node<>(null, null, null, check);
                return check.getLeft(); 
            }else{
               nodes.add(check.getLeft());
               if(check.getRigth()==null){
                   check.rigth = new Node<>(null,null,null, check);
                   return check.getRigth();
               }else{
                   nodes.add(check.getRigth());
               }
            }
            nodes.remove(0);
            check = nodes.get(0);
        }
        return null;
    }
    
    public void printHeap(){
        List<Node<T>> nodes = new LinkedList<>();
        Node<T> check = root;
        while(check!=null){
            System.out.println(check.getX());
            if(check.getLeft()!=null && check.getLeft()!=now){
                nodes.add(check.getLeft());
               System.out.print(check.getLeft().getX()+"left ");
            }
            if(check.getRigth()!=null && check.getRigth()!=now){
                nodes.add(check.getRigth());
                System.out.print(check.getRigth().getX()+"rigth");  
            }
            System.out.println("");
            if(nodes.size()!=0){
                check = nodes.get(0);
                nodes.remove(0);
            }else{
                return;
            }
        }
    }
    
  private class Node<T extends Comparable<T>> {

        private T x;
        private Node<T> parent;
        private Node<T> left;
        private Node<T> rigth;

        public Node(T x,Node<T> left, Node<T> rigth, Node<T> parent) {
            this.x = x;
            this.left = left;
            this.rigth = rigth;
            this.parent = parent;
        }

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public T getX() {
            return x;
        }

        public void setX(T x) {
            this.x = x;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRigth() {
            return rigth;
        }

        public void setRigth(Node<T> rigth) {
            this.rigth = rigth;
        }

    }
    
        
    
}

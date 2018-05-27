/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.binarytree;

import java.util.logging.Logger;

/**
 *
 * @author danis_zam
 */
public class BinaryTree <T extends Comparable<T>> {
    protected Node<T> head;
    protected static final Logger log = Logger.getLogger(BinaryTree.class.getName());
    
    public void add(T value){
      if(this.contains(value)!=null){
          System.out.println("this Node in tree");
          log.info("incorrect node");
      }
      Node<T> node = new Node<>(null,null,null,value, 1);
      if(head==null){
          head = node;
      }else{
         this.addTo(node, head);
      }  
    }
    
    private void addTo(Node node, Node other){
       if(node.compareTo(other)>0){
         if(other.rigth==null){
             other.setRigth(node);
             node.setParent(other);
         }else{
             this.addTo(node, other.rigth);
         } 
       }else{
           if(other.left==null){
               other.setLeft(node);
               node.setParent(other);
           }else{
               this.addTo(node, other.left);
           }
       } 
    }
    
    
    public boolean remove(T value){
       Node<T> removeN = this.contains(value);
       Node<T> parentN = removeN.parent;
       if(removeN==null){
           return false;
       }
       if(removeN.rigth==null){
         if(parentN == null){
            head = removeN.left;
            return true;
         }else{
            parentN.setLeft(removeN.left);
            return true;
         }
       }else{
           Node<T> minRigth = removeN.rigth;
           while(minRigth.left!=null){
               minRigth = minRigth.left;
           }
           if(minRigth.parent.left==minRigth){
                minRigth.parent.left = null;
           }else{
               minRigth.parent.rigth = null;
           }
           minRigth.setLeft(removeN.getLeft());
           minRigth.setRigth(removeN.getRigth());
           minRigth.setParent(removeN.getParent()); 
           removeN.parent.setLeft(minRigth);
           System.out.println("sdfdf"+parentN.value);
           return true;
       }
    }
    
    public void printTree(){
        this.printTree(head);
    }
    public void printTree(Node head){
        System.out.println(head.value+" heigth"+head.getHeigth());
        if(head.left!=null && head.rigth!=null){
            System.out.println("left="+head.left.value+" "+"rigth="+head.rigth.value);
        }else{
            if(head.left!=null){
              System.out.println("left="+head.left.value);  
            }
            if(head.rigth!=null){
                System.out.println("rigth="+head.rigth.value);
            }
        }
        if(head.left!=null){
            this.printTree(head.left);
        }
        if(head.rigth!=null){
            this.printTree(head.rigth);
        }
    }
    
    public Node<T> contains(T value){
       Node<T> contain = head;
       while(contain!=null){
           if(contain.getValue().compareTo(value)==0){
               return contain;
           }
           if(contain.getValue().compareTo(value)<0){
               contain = contain.getRigth();
           }else{
               contain = contain.getLeft();
           }
           if(contain == null){
               return null;
           }
       }
       return contain;
    }
    
  protected class Node<T extends Comparable<T> > implements Comparable<Node<T>>{
      protected int heigth;
      protected Node left;
      protected Node rigth;
      protected T value;
      protected Node parent;

    public Node(Node left, Node rigth,Node parent, T value, int heigth) {
        this.left = left;
        this.rigth = rigth;
        this.value = value;
        this.parent = parent;
        this.heigth = heigth;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRigth() {
        return rigth;
    }

    public void setRigth(Node rigth) {
        this.rigth = rigth;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    

      @Override
    public int compareTo(Node<T> other){
        return this.value.compareTo(other.getValue());
    }
  }
}

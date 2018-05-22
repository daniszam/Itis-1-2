/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.treap;

import java.util.LinkedList;

/**
 *
 * @author danis_zam
 * @param <T>
 * @param <N>
 */
public class Treap<T extends Comparable<T>, N extends Comparable<N>> {

    private Node<T, N> root;
    private Treap<T,N> leftTreap;
    private Treap<T,N> rigthTreap;
    private TreapSplit<T,N> treaps;
    
    
    
    public Treap() {
        this.treaps = new TreapSplit<>(null,null);
    }

    public TreapSplit<T, N> getTreaps() {
        return treaps;
    }

    public void setTreaps(TreapSplit<T, N> treaps) {
        this.treaps = treaps;
    }
    
    
    
    private Treap(Node<T,N> root){
        this.root = root;
        this.treaps = new TreapSplit<>(null,null);
    }
    
    private Treap(Node<T,N> root, Treap<T,N> left, Treap<T,N> rigth){
        this.root = root;
        this.root.setLeft(left.getRoot());
        this.root.setRigth(rigth.getRoot());
        this.treaps = new TreapSplit<>(null,null);
    }

    public Treap<T, N> getLeftTreap() {
        return leftTreap;
    }

    public void setLeftTreap(Treap<T, N> leftTreap) {
        this.leftTreap = leftTreap;
    }

    public Treap<T, N> getRigthTreap() {
        return rigthTreap;
    }

    public void setRigthTreap(Treap<T, N> rigthTreap) {
        this.rigthTreap = rigthTreap;
    }
    

    public Node<T, N> merge(Node<T,N> left1, Node<T,N> rigth1) {
        if(left1==null){
            return rigth1;
        }else{
            if(rigth1==null){
                return left1;
            }
        }
        if(left1.getY().compareTo(rigth1.getY())<0){
            rigth1.setLeft(this.merge(left1, rigth1.getLeft()));
            return rigth1;
        }else{
            left1.setRigth(this.merge(left1.getRigth(), rigth1));
            return left1;
        }
    }
    
    
    public TreapSplit<T,N> split(Node<T,N> treap, T key){
       TreapSplit<T,N> treaps = new TreapSplit<>(null,null);
       if(treap==null){
           return treaps;
       }
       if(treap.getX().compareTo(key)<0){
            treaps = this.split(treap.getRigth(), key);
            treap.setRigth(null);
            return new TreapSplit<T,N> (this.merge(treap, treaps.treapLeft), treaps.treapRigth); 
       }else{
            treaps = this.split(treap.getLeft(), key);
            treap.setLeft(null);
            return new TreapSplit<T,N> (treaps.treapLeft, this.merge(treaps.treapRigth, treap));
       } 
    }

    public void add(T x, N y){
       Node<T,N> node = this.getRoot();
       while(node!=null && node.x !=x){
           if(x.compareTo(node.x)<0){
              node = node.left;
           }else{
               node = node.rigth;
           }
       }
       if(node==null){
           Node<T,N> node1 = new Node<>(x,y,null,null);
           TreapSplit<T,N> p = this.split(this.root, x);
           Treap<T,N> f = new Treap<>(node);
           this.setRoot(merge(p.getTreapLeft(), merge(node1, p.treapRigth)));
       }
    }
    
    public void remove(T t){
        LinkedList<Node> list = new LinkedList();
        Node<T,N> print = this.root;
        while(print!=null){
          if(print.getLeft()!=null){
              list.add(print.getLeft());     
          }
          if(print.getRigth()!=null){
              list.add(print.getRigth());
          }
          if(print.x.compareTo(t)==0){
             if(print.getLeft()!=null && print.getRigth()!=null){
                Treap<T,N> treap = new Treap<>(this.merge(print.getLeft(), print.getRigth()));
                Node<T,N> parent = list.get(list.indexOf(print)-1);
                parent.setLeft(treap.getRoot());
             }else{
                 if(print.getLeft()!=null){
                     if(list.indexOf(print)!=0){
                        Node<T,N> parent = list.get(list.indexOf(print)-1);
                        parent.setLeft(print.getLeft());
                     }
                 }
                 if(print.getRigth()!=null){
                     if(list.indexOf(print)!=0){
                        Node<T,N> parent = list.get(list.indexOf(print)-1);
                        parent.setRigth(print.getLeft());
                     }
                 }
             }            
          }
        }
    }
    
    public void printTreap(){
       LinkedList<Node> list = new LinkedList(); 
       Node<T,N> print = this.root;
       list.add(print);
       while(print!=null){  
            if(print.getLeft()!=null){
                list.add(print.getLeft());     
            }
            if(print.getRigth()!=null){
                list.add(print.getRigth());
            }
            System.out.println(list.getFirst().x+" priority "+ list.getFirst().y);
            if(list.getFirst().getLeft()!=null){
                System.out.print("left "+list.getFirst().getLeft().x);
            }
            if(list.getFirst().getRigth()!=null){
                System.out.println(" rigth "+list.getFirst().getRigth().x);
            }      
            list.removeFirst();
            if(list.size()!=0){
                print = list.getFirst();
            }else{
                break;
            }
       }
    }
    
    public void print(){
        this.print(root, 0);
    }
    
    private void print(Node<T,N> t, int level){
        System.out.println(t.x);
        if(t!=null){
            this.print(t.getLeft(), level+1);
            for(int i =0; i<level; i++){
                System.out.println("...");
                System.out.println(t.getX());
                print(t.getRigth(),level+1);
            }
        }
    }
    public Node<T, N> getRoot() {
        return root;
    }

    public void setRoot(Node<T, N> root) {
        this.root = root;
    }


    private class Node<T extends Comparable<T>, N extends Comparable<N>> {

        private T x;
        private N y;
        private Node<T, N> left;
        private Node<T, N> rigth;

        public Node(T x, N y, Node<T, N> left, Node<T, N> rigth) {
            this.x = x;
            this.y = y;
            this.left = left;
            this.rigth = rigth;
        }

        public T getX() {
            return x;
        }

        public void setX(T x) {
            this.x = x;
        }

        public N getY() {
            return y;
        }

        public void setY(N y) {
            this.y = y;
        }

        public Node<T, N> getLeft() {
            return left;
        }

        public void setLeft(Node<T, N> left) {
            this.left = left;
        }

        public Node<T, N> getRigth() {
            return rigth;
        }

        public void setRigth(Node<T, N> rigth) {
            this.rigth = rigth;
        }

    }
    
    private class TreapSplit<T extends Comparable<T>,N extends Comparable<N>>{
        private Node<T,N> treapLeft;
        private Node<T,N> treapRigth;
        
        public TreapSplit(Node<T,N> treapLeft, Node<T,N> treapRigth){
            this.treapLeft = treapLeft;
            this.treapRigth = treapRigth;
        }

        public Node<T, N> getTreapLeft() {
            return treapLeft;
        }

        public void setTreapLeft(Node<T, N> treapLeft) {
            this.treapLeft = treapLeft;
        }

        public Node<T, N> getTreapRigth() {
            return treapRigth;
        }

        public void setTreapRigth(Node<T, N> treapRigth) {
            this.treapRigth = treapRigth;
        }
        
    }

}

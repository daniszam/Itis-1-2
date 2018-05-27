/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.binarytree;

/**
 *
 * @author danis_zam
 * @param <T>
 */
public class AVL<T extends Comparable<T>> extends BinaryTree<T>{
    
    @Override
    public void add(T element){
       super.add(element);
       this.checkHeigth(super.contains(element));
       Node<T> node = super.contains(element);
        while(node!=null){
          this.balance(node);
          node = node.parent;
        }
    }
    
    @Override
    public boolean remove(T element){
        Node<T> removeN = super.contains(element);
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
             removeN.left.setParent(parentN);
             this.checkHeigth(removeN.left);
             this.balance(head);
             return true;
          }
        }else{
            Node<T> minRigth = removeN.rigth;
            while(minRigth.left!=null){
                minRigth = minRigth.left;
            }
            this.checkHeigth(minRigth);
            if(minRigth.parent.left==minRigth){
                 minRigth.parent.left = null;
            }
            minRigth.setLeft(removeN.getLeft());
            if(minRigth!=removeN.getRigth()){
                minRigth.setRigth(removeN.getRigth());
            }
            int parentRigth = 0;
            if(minRigth.parent.getRigth()!=null){
                parentRigth = minRigth.parent.getRigth().getHeigth();
            }
            minRigth.parent.setHeigth(Math.max(1, parentRigth));
            this.checkHeigth(minRigth.parent);
            minRigth.setParent(removeN.getParent()); 
            minRigth.setHeigth(removeN.getHeigth());
            if(removeN.parent!=null){
                removeN.parent.setLeft(minRigth);
            }else{
                head = minRigth;
            }
            this.printTree();
            this.balance(head);   
            return true;
        }
    }
    
    public void balance(Node<T> node){
        Node<T> root = node;
        int rootLeft = 0;
        int rootRigth = 0;
        if(root.getLeft()!=null){
            rootLeft = root.getLeft().getHeigth();
        }
        if(root.getRigth()!=null){
            rootRigth = root.getRigth().getHeigth();
        }
        if( (rootRigth - rootLeft) ==2 ){
           int a = 0;
           int b = 0;
           if(root.getRigth().getLeft()!=null){
               a = root.getRigth().getLeft().getHeigth();
           }
           if(root.getRigth().getRigth()!=null){
               b = root.getRigth().getRigth().getHeigth();
           }
              if(a<=b){
                  this.rotateLeft(root);
              }
              if(a > b){
                 this.rotateBigLeft(root); 
              }
        }else{
            if((rootLeft - rootRigth) ==2 ){
               int a = 0;
               int b = 0;
               if(root.getLeft().getLeft()!=null){
                    a = root.getLeft().getLeft().getHeigth();
               }
               if(root.getLeft().getRigth()!=null){
                    b = root.getLeft().getRigth().getHeigth();
               }
               if(b<=a){
                    this.rotateRigth(root);
               }
               if(b>a){
                    this.rotateBigRigth(root);
               }
            }
        }
    }
    
    @Override
    public void printTree(){
       super.printTree();
    }
    
    private void rotateBigLeft(Node<T> node){
        this.rotateRigth(node.getRigth());
        this.rotateLeft(node);
    }
    
    private void rotateBigRigth(Node<T> node){
       this.rotateLeft(node.getLeft());
       this.rotateRigth(node);
    }
    
    private void checkHeigth(Node<T> node){
        Node<T> parent = node.getParent();
        if(parent == null){
            return;
        }
        if(node == parent.left){
            if(parent.getRigth()==null || parent.getRigth().getHeigth() < node.getHeigth()){
               parent.setHeigth(node.getHeigth()+1);
               this.checkHeigth(parent);
            }else{
                if(parent.getRigth().getHeigth() > node.getHeigth()){
                    return;
                }
            }
        }else{
           if(parent.getLeft()==null || parent.getLeft().getHeigth() < node.getHeigth()){
               parent.setHeigth(node.getHeigth()+1);
               this.checkHeigth(parent);
            }else{
                if(parent.getLeft().getHeigth() > node.getHeigth()){
                    return;
                } 
           }
        }
    }
    
    
    public void rotateLeft(Node<T> node){
        if(node==null){
            return;
        }
        Node<T> root = node.getRigth();
        int a = 0;
        int b = 0; 
        if(node.getLeft()!=null){
           a = node.getLeft().getHeigth();
            System.out.println(node.getLeft().getValue());
        }
        if(root.getLeft()!=null){
            b = root.getLeft().getHeigth();
            root.getLeft().setParent(node);
        } 
        node.rigth =root.getLeft();
        root.left = node;
        if(node.parent!=null){
            root.parent = node.parent;
        }else{
            root.parent = null;
            head = root;
        }
        node.setHeigth(Math.max(a, b)+1);
        if(node.parent!=null){
            if(node.parent.left==node){
                node.parent.setLeft(root);
            }else{
                node.parent.setRigth(root);
            }
        }
        node.parent = root;
        int rootLeft = 0;
        int rootRigth = 0;
        if(root.getLeft()!=null){
            rootLeft = root.getLeft().getHeigth();
        }
        if(root.getRigth()!=null){
            rootRigth = root.getRigth().getHeigth();
        }
        root.setHeigth(Math.max(rootLeft, rootRigth)+1); 
        this.checkHeigth(root);
        this.checkHeigth(node);
    }
    
    public void rotateRigth(Node<T> node){
       if(node==null){
            return;
        }
       Node<T> root = node.getLeft();
        int a = 0;
        int b = 0; 
        if(node.getRigth()!=null){
           a = node.getRigth().getHeigth();
        }
        if(root.getRigth()!=null){
            b = root.getRigth().getHeigth();
            root.getRigth().setParent(node);  
        } 
       node.left =root.getRigth();
       root.rigth = node;
       if(node.parent!=null){
           root.parent = node.parent;
       }else{
           root.parent = null;
           head = root;
       }
       if(node.parent!=null){
            if(node.parent.left==node){
                node.parent.setLeft(root);
            }else{
                node.parent.setRigth(root);
            }
        }
       node.parent = root;
       node.setHeigth(Math.max(a, b)+1);
       int rootLeft = 0;
        int rootRigth = 0;
        if(root.getLeft()!=null){
            rootLeft = root.getLeft().getHeigth();
        }
        if(root.getRigth()!=null){
            rootRigth = root.getRigth().getHeigth();
        }
        root.setHeigth(Math.max(rootLeft, rootRigth)+1);  
        this.checkHeigth(root);
        this.checkHeigth(node);
    }
    

}

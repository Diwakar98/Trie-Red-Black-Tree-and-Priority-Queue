package RedBlack;

import Util.RBNodeInterface;

import java.util.*;

public class RedBlackNode<T extends Comparable, E> implements RBNodeInterface<E> {

     T key;
     E value;
     public List<E> list;
     RedBlackNode parent;
     RedBlackNode left;
     RedBlackNode right;
     //0 for red, 1 for black
     int color;
     RedBlackNode(T key, E value,int color){
          list=new ArrayList<E>();
          this.parent=null;
          this.key=key;
          this.value=value;
          this.left=this.right=null;
          this.color=color;
     }
     public void setparent(RedBlackNode parent){
          this.parent=parent;
     }
     public void addnumber(E value){
          list.add(value);
     }

     @Override
     public E getValue() {
          return value;
     }

     @Override
     public List<E> getValues() {
          return list;
     }
     public void setcolor(int color){
          this.color=color;
     }
     public int getcolor(){
          return this.color;
     }
}

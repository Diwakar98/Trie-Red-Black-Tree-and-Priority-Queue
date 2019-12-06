package RedBlack;
import ProjectManagement.*;
import java.util.*;
public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {
     RedBlackNode root;
     public static final int RED=0;
     public static final int BLACK=1;

     public int count;
     T name;
     E p;
     public RBTree(){
          count=0;
          this.root=null;
     }
    public RedBlackNode uncle(RedBlackNode root){
         if(root.parent!=null){
              if(root.parent.parent!=null){
                   if(root.parent==root.parent.parent.right){
                        return root.parent.parent.left;
                   }
                   else if(root.parent==root.parent.parent.left){
                        return root.parent.parent.right;
                   }
              }
         }
         return null;
    }
    public void rotateleft(RedBlackNode node){
         RedBlackNode p=node.right;
         RedBlackNode t=p.left;
         if(node.parent!=null){
             if(node.parent.right==node){
                  node.parent.right=p;
             }
             else{
                  node.parent.left=p;
             }
        }
        else{
             root=p;
        }
         p.parent=node.parent;
         p.left=node;
         node.parent=p;
         node.right=t;
         if(t!=null){
              t.parent=node;
         }
    }
    public void rotateright(RedBlackNode node){
         RedBlackNode p=node.left;
         RedBlackNode t=p.right;
         if(node.parent!=null){
              if(node.parent.right==node){
                   node.parent.right=p;
              }
              else{
                   node.parent.left=p;
              }
         }
         else{
              root=p;
         }
         p.parent=node.parent;
         p.right=node;
         node.parent=p;
         node.left=t;
         if(t!=null){
              t.parent=node;
         }
    }
    public void balance(RedBlackNode root){
         if(root.parent==null){
              root.setcolor(BLACK);
              return;
         }
         if(root.parent.color==RED){
              if(uncle(root)!=null){
                   if(uncle(root).getcolor()==RED){
                        root.parent.setcolor(BLACK);
                        root.parent.parent.setcolor(RED);
                        uncle(root).setcolor(BLACK);
                        root=root.parent.parent;
                        balance(root);
                   }
                   else{
                        if(root==root.parent.left && root.parent==root.parent.parent.left){
                            rotateright(root.parent.parent);
                            root.parent.setcolor(BLACK);
                            root.parent.right.setcolor(RED);
                        }
                        else if(root==root.parent.right && root.parent==root.parent.parent.left){
                             rotateleft(root.parent);
                             rotateright(root.parent);
                             root.setcolor(BLACK);
                             root.right.setcolor(RED);
                        }
                        else if(root==root.parent.right && root.parent==root.parent.parent.right){
                             rotateleft(root.parent.parent);
                             root.parent.setcolor(BLACK);
                             root.parent.right.setcolor(RED);
                        }
                        else if(root==root.parent.left && root.parent==root.parent.parent.right){
                             rotateright(root.parent);
                             rotateleft(root.parent);
                             root.setcolor(BLACK);
                             root.left.setcolor(RED);
                        }
                   }
              }
              else{
                   if(root==root.parent.left && root.parent==root.parent.parent.left){
                       rotateright(root.parent.parent);
                       root.parent.setcolor(BLACK);
                       root.parent.right.setcolor(RED);
                   }
                   else if(root==root.parent.right && root.parent==root.parent.parent.left){
                        rotateleft(root.parent);
                        rotateright(root.parent);
                        root.setcolor(BLACK);
                        root.right.setcolor(RED);
                   }
                   else if(root==root.parent.right && root.parent==root.parent.parent.right){
                        rotateleft(root.parent.parent);
                        root.parent.setcolor(BLACK);
                        root.parent.left.setcolor(RED);
                   }
                   else if(root==root.parent.left && root.parent==root.parent.parent.right){
                        rotateright(root.parent);
                        rotateleft(root.parent);
                        root.setcolor(BLACK);
                        root.left.setcolor(RED);
                   }
              }
         }
    }

    boolean check=false;
         public void insert(T key, E value){
              count++;
              RedBlackNode temp=root;
              RedBlackNode temp1=null;
              if(root==null){
                   root=new RedBlackNode(key,value,BLACK);
                   root.addnumber(value);
                   root.setparent(null);
              }
              else{
                   while(temp!=null){
                        //String s1=(String)key;
                        //String s2=(String)temp.key;
                        if(key.compareTo(temp.key)<0){
                             if(temp.left==null){
                                 temp1=new RedBlackNode(key,value,RED);
                                  temp1.setparent(temp);
                                  temp.left=temp1;
                             }
                             temp=temp.left;
                        }
                        else if(key.compareTo(temp.key)>0){
                             if(temp.right==null){
                                   temp1=new RedBlackNode(key,value,RED);
                                  temp1.setparent(temp);
                                  temp.right=temp1;
                             }
                             temp=temp.right;
                        }
                        else{
                             temp1=temp;
                             temp.addnumber(value);
                             break;
                        }
                   }
                   if(temp1.parent==null){
                        return;
                   }
                   if(temp1.parent.parent==null){
                        return;
                   }
                   balance(temp1);
              }
         }

    @Override
    public RedBlackNode<T, E> search(T key) {
        //root=searchdata(root,key);
        //return root;
        RedBlackNode temp=root;
        boolean f=false;
        while(temp!=null){
               //String s1=(String)key;
               //String s2=(String)temp.key;
               T s1=key;
               T s2=(T)temp.key;
               if(s1.compareTo(s2)>0){
                    temp=temp.right;
               }
               else if(s1.compareTo(s2)<0){
                    temp=temp.left;
               }
               else{
                    f=true;
                    break;
               }
         }
         if(f==true){
              return temp;
         }
         else{
              return null;
         }
    }
    public RedBlackNode searchdata(RedBlackNode temp,T key){

         if(temp==null){
              return null;
         }
         else{
              String s1=(String)key;
              String s2=(String)temp.key;

              if(s1.compareTo(s2)<0){
                   temp=searchdata(temp.left,key);
              }
              else if(s1.compareTo(s2)>0){
                   //key.compareTo(root.key)
                   temp=searchdata(temp.right,key);
              }
              else{
                   //
                    Trie.Person p1=(Trie.Person)temp.list.get(0);
                    Trie.Person p2=(Trie.Person)temp.list.get(1);
                   return temp;
              }
         }
         return temp;
    }
    public void inorder()  {

       inorders(root);
       System.out.println();
    }
    public void inorders(RedBlackNode root) {
        if (root != null) {
            inorders(root.left);
            //root.s.printdetail();
            //List<Job> j=(List<Job>)root.list;
            if(root.color==RED){
                 System.out.print("("+root.key+"-RED)");
            }
            if(root.color==BLACK){
                 System.out.print("("+root.key+"-BLACK)");
            }
            inorders(root.right);
        }
    }
}

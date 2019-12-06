package Trie;
import java.util.*;
public class Trie<T> implements TrieInterface {

     TrieNode root;
     //int highestlevel=0;
     List<Integer> list;
     public int noofperson;
     public Trie(){
          list=new ArrayList<>();
          noofperson=0;
          this.root=new TrieNode();
     }

     @Override
     public boolean insert(String word, Object value) {
          int level;
          int length=word.length();
          boolean alreadypresent=false;
          if(search(word)!=null){
               alreadypresent=true;
          }
          else{
               alreadypresent=false;
               noofperson++;
               list.add(length);
               Collections.sort(list);
          //if(length+1>highestlevel){
          //     highestlevel=length+1;
          //}
          int index;
          TrieNode node=root;
          for(level=0; level<length; level++){

               index=word.charAt(level)-32;
               /*
               if(index==-33)
                    index=26;
               if(index==-19)
                    index=27;*/
                    //System.out.println(word.charAt(level)+" = "+index);
               //System.out.println(index);
               if(node.children[index]==null){
                    //System.out.println((char)(index+65));
                    node.children[index]=new TrieNode();
                    node.children[index].parent=node;
               }
               node=node.children[index];
          }

          node.setValue(value);
     }
     if(alreadypresent==true){
          System.out.println("ERROR INSERTING");
          return false;
     }
     else{
          return true;
     }
     }

     @Override
     public boolean delete(String word) {
          TrieNode node=root;
          int length=word.length();
          boolean success=false;
          boolean otherchildpresent=false;
          int level;
          //System.out.println("X"+word+".");
          if(search(word)==null){
               //System.out.println("Y");
               success=false;

          }
          else{
               list.remove(new Integer(length));
               noofperson--;
               // System.out.println("Z");
               success=true;
               int c=0;
               for(level=0; level<length; level++){
                    int index=word.charAt(level)-32;
                    //System.out.println(level+" = "+index);
                    /*if(index==-33)
                         index=26;
                    if(index==-19)
                         index=27;*/
                    if(node.children[index].getValue()!=null){
                         //Person p=(Person)node.children[index].getValue();
                         //System.out.println(p.getName());
                         c=level;
                         //System.out.println("CHILD"+c);
                    }

                    node=node.children[index];


               }
               node.setValue(null);
               // System.out.println("X");
               for(int i=0 ;i<95; i++){
                    if(node.children[i]!=null){

                         otherchildpresent=true;
                         break;
                    }
               }

               level=word.length();
               boolean flag=true;
               if(otherchildpresent==true){


                    node.setValue(null);
               }
               else{
                    // System.out.println("X");
                    while(flag){
                         //System.out.print("$");
                         node=node.parent;
                         level--;

                         // System.out.println("X1");
                         int index=word.charAt(level)-32;
                         // System.out.println("X2");
                         // System.out.println((char)(index+32)+"  "+level+"===="+index);
                         if(level==0){
                              flag=false;
                              node.children[index]=null;
                              break;
                         }


                         else{
                         for(int i=0; i<95; i++){
                              if(node.children[i]!=null && i!=index){
                                   //System.out.print("$");
                              //     System.out.println("^ "+i+"-"+index+" "+(char)(i+65));


                              //          System.out.println((char)(index+65)+" "+i+" "+level);
                                        /*if(index==-33)
                                             index=26;
                                        if(index==-19)
                                             index=27;*/
                                        node.children[index]=null;
                                        flag=false;
                                        break;

                              }
                         }}
                    }
               }

          }
          if(success){
               System.out.println("DELETED");
          }
          else{
               System.out.println("ERROR DELETING");
          }
          return success;
     }

     @Override
     public TrieNode search(String word) {
          try{
          TrieNode node=root;
          int length=word.length();
          boolean found=true;
          int level;
          for(level=0; level<length; level++){
               int index=word.charAt(level)-32;
               /*if(index==-33)
                    index=26;
               if(index==-19)
                    index=27;*/
               if(node.children[index]==null){
                    found=false;
                    break;
               }
               node=node.children[index];
          }
          if(node.value==null){
               found=false;
          }
          if(found==true){
               return node;
          }
          else
               return null;
          }
          catch(Exception e){
               return null;
          }
     }

     @Override
     public TrieNode startsWith(String prefix) {
          int level;
          int length=prefix.length();
          int index;
          TrieNode node=root;
          for(level=0; level<length; level++){
               index=prefix.charAt(level)-32;
               /*if(index==-33)
                    index=26;
               if(index==-19)
                    index=27;*/
               if(node==null)
                    break;
               node=node.children[index];
          }
          return node;
     }

     @Override
     public void printTrie(TrieNode trieNode) {
          for(int index=0; index<95; index++){
               //System.out.print("("+index+trieNode.children[index]+")"+"//");
               /*if(trieNode.children[index]!=null){
                    if(trieNode.getValue()!=null){
                         System.out.print(index+"..");
                         Person p=(Person)trieNode.getValue();
                         System.out.println("[Name: "+p.getName()+", Phone="+p.getNumber()+"]");
                    }
                    printTrie(trieNode.children[index]);
               }*/
               if(trieNode!=null){
                    if(trieNode.getValue()!=null){
                         Person p=(Person)trieNode.getValue();
                         System.out.println("[Name: "+p.getName()+", Phone="+p.getNumber()+"]");
                         break;
                    }
                    printTrie(trieNode.children[index]);
               }
          }
     }

     TrieNode printingnode;
     int output[];
     int count=0;


     @Override
     public void printLevel(int level) {
          output=new int[noofperson+1];
          count=0;
          //System.out.println("##");
          for(int i=0; i<noofperson+1; i++){
               output[i]=0;
          }
          printing(level,root);
          System.out.print("Level "+level+": ");

          for(int i=0; i<noofperson+1; i++){
               for(int j=0; j<noofperson+1; j++){
                    if(output[i]<output[j]){
                         int k=output[i];
                         output[i]=output[j];
                         output[j]=k;
                    }
               }
          }
          for(int i=1; i<noofperson; i++){
               if(output[i]!=0){
                    System.out.print((char)output[i]+",");
               }
          }
          System.out.print((char)output[noofperson]);
          System.out.println();
     }


     public void printing(int level,TrieNode node){
          //System.out.print("****"+level+"****");
          if(node!=null){
          if(level==1){
          //     System.out.print("entering level 1");
               for(int i=0; i<95; i++){
          //          System.out.print("("+i+node.children[i]+")"+"//");
                    if(node.children[i]!=null)
                    {
          //                    System.out.print("$$$$"+count+"$$$$");
                         //if(i!=26)
                         //output[count++]=(i+65);
                         if(i==26)
                         {
                              //output[count++]=32;
                         }

                         //else if(i==27)
                         //output[count++]=46;
                         //else
                         if(i!=0)
                         output[count++]=(i+32);
          //               System.out.print("..output..");
                    }
               }
          //     System.out.print("exiting level 1");
          }
          else{
               for(int i=0; i<95; i++){
          //          System.out.print("("+i+node.children[i]+")"+"//");
                    if(node.children[i]!=null){
          //               System.out.print("entering");
                         printing(level-1,node.children[i]);
          //               System.out.print("exiting");
                    }

               }
          //     System.out.print("%%");
          }
          //System.out.print("^^^^"+level+"^^^^");
     }
     }
     @Override
     public void print() {
          System.out.println("-------------");
          System.out.println("Printing Trie");

          //System.out.println(list.get(0));
          int i=1;
          int k=0;
          if(list.size()>0){
               k=list.get(list.size()-1);
          }
          for(i=1; i<=k; i++){
               printLevel(i);
          }
          System.out.println("Level "+i+":");
          System.out.println("-------------");
     }
}

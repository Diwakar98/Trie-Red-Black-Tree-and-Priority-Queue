package Trie;


import Util.NodeInterface;


public class TrieNode<T> implements NodeInterface<T> {

     public T value;
     TrieNode[] children=new TrieNode[95];
     TrieNode parent;
     boolean isendofword;
     TrieNode(){
          isendofword=false;
          value=null;
          for(int i=0; i<95; i++){
               children[i]=null;
               parent=null;
          }
     }
     public void setValue(T value){
          isendofword=true;
          this.value=value;
     }
     @Override
     public T getValue() {
          return value;
     }
}

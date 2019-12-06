package PriorityQueue;
import java.util.*;
import ProjectManagement.*;
public class MaxHeap<T extends Comparable> implements PriorityQueueInterface<T> {
     public List<Tuple<T>> list;
     public int global=1;
     public MaxHeap(){
          list=new ArrayList<Tuple<T>>();
     }
     public T search(String name){
          for(int i=0; i<list.size(); i++){
               Tuple t=list.get(i);
               Job j=(Job)t.value;
               if(j.name.equals(name)){
                    return (T)j;
               }
          }
          return null;
     }
     public void swap(int i,int j){
          Tuple t=list.get(i);
          list.set(i,list.get(j));
          list.set(j,t);
     }
    public void heapify(List<Tuple<T>> list,int a){
         if(2*a+1<list.size()){
              if(list.get(2*a+1).compareTo(list.get(a))>0){
                   swap(2*a+1,a);
                   heapify(list,2*a+1);
              }
         }
         if(2*a+2<list.size()){
            if(list.get(2*a+2).compareTo(list.get(a))>0) {
                swap(2*a+2,a);
                heapify(list,2*a+2);
            }
        }
    }
    public void insert(T element){
        list.add(new Tuple<>(element,global));
        global++;
        int i= list.size()-1;
        Tuple<T> A= list.get(i);
        Tuple<T> B= list.get(i/2);
        while(A.compareTo(B)>0 && i!=0){
            swap(i,i/2);
            i=i/2;
            A=list.get(i);
            B=list.get(i/2);
        }
    }
    public T extractMax(){
        if(list.size()==0) return null;
        Tuple<T> max = list.get(0);
        if(list.size()==1){
            return list.remove(0).value;
        }
        else{
            list.set(0,list.remove(list.size()-1));
            heapify(list,0);
        }
        return max.value ;
    }
}

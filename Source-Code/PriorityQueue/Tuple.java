package PriorityQueue;
import ProjectManagement.*;
public class Tuple<T extends Comparable>{
     public T value;
     public int index;
     public Tuple(T value,int index){
          this.value=value;
          this.index=index;
     }
     public int compareTo(Tuple tuple) {
        if(this.value.compareTo(tuple.value)<0){
              // System.out.println("#1");
             return -1;
        }
        else if(this.value.compareTo(tuple.value)>0){
              // System.out.println("#2");
             return 1;
        }
        else{
             //Job j=(Job)this.value;
             //Job j1=(Job)tuple.value;
              // System.out.println("#3 "+this.index+"="+tuple.index);
             if(this.index>tuple.index){
                  // System.out.println("#4");
                  return -1;
             }
             else if(this.index<tuple.index){
                  // System.out.println("#5");
                  return 1;
             }
             else{

                  // System.out.println("#6");
                  return 0;
             }
        }
    }
    public T getValue(){
         return value;
    }
}

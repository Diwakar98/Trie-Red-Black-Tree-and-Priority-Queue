package ProjectManagement;
import java.util.*;
import ProjectManagement.*;
public class FIFO{
     public List<Job> jobs;
     int priority;
     public FIFO(int priority){
          this.priority=priority;
          this.jobs=new ArrayList<Job>();
     }
     public void add(Job job){
          jobs.add(job);
     }
     public Job remove(){
          Job temp=null;
          if(jobs.size()>0){
               temp=jobs.get(0);
          }
          return temp;
     }
     public int compareTo(Job job) {
        if(this.priority<job.priority){
             return -1;
        }
        else if(this.priority>job.priority){
             return 1;

        }
        else{
             return 0;
        }
    }
}

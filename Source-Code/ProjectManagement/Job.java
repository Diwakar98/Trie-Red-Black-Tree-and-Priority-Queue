package ProjectManagement;

public class Job implements Comparable<Job> {

     public String name;
     public String project;
     public int priority;
     public String user;
     public int runtime;
     public String status;
     public int completedtime;
     int index;
     public Job(String name,String project,int priority,String user, int runtime,int index){
          this.priority=priority;
          this.name=name;
          this.project=project;
          this.user=user;
          this.runtime=runtime;
          this.status="REQUESTED";
          this.index=index;
     }
     public void setcompletedtime(int completedtime){
          this.completedtime=completedtime;
     }
     public void setstatus(String status){
          this.status=status;
     }
    @Override
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

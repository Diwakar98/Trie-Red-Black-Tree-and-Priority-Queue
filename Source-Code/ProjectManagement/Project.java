package ProjectManagement;


public class Project {
     public String name;
     public int priority;
     public int budget;
     public Project(String name, int priority,int budget){
          this.name=name;
          this.budget=budget;
          this.priority=priority;
     }
     public void decreaseBudget(int reduction){
          this.budget-=reduction;
     }
}

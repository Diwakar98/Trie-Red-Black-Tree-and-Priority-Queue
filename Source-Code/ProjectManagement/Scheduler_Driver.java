package ProjectManagement;

import PriorityQueue.PriorityQueueDriverCode;
import Trie.*;
import PriorityQueue.*;
import RedBlack.*;
import java.io.*;
import java.util.*;
import ProjectManagement.*;
import java.net.URL;

public class Scheduler_Driver extends Thread implements SchedulerInterface {

     Trie projects= new Trie();
     //MaxHeap<FIFO> fifos=new MaxHeap();
     MaxHeap<Job> jobs=new MaxHeap();
     Trie users=new Trie();
     public static int global;
     List<Job> discardedjob=new ArrayList<Job>();
     RBTree<Integer,Job> rbt=new RBTree<Integer,Job>();
     List<Job> finishedjob=new ArrayList<Job>();
     public int globaltime=0;
     static boolean flag=false;


    public static void main(String[] args) throws IOException {
        Scheduler_Driver scheduler_driver = new Scheduler_Driver();
        global=1;
        flag=false;
        File file;
        if (args.length == 0) {
            URL url = PriorityQueueDriverCode.class.getResource("INP");
            file = new File(url.getPath());
        } else {
            file = new File(args[0]);
        }

        scheduler_driver.execute(file);
    }

    public void execute(File file) throws IOException {


        URL url = Scheduler_Driver.class.getResource("INP");
        file = new File(url.getPath());

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. "+file.getAbsolutePath());
        }
        String st;
        while ((st = br.readLine()) != null) {
            String[] cmd = st.split(" ");
            if (cmd.length == 0) {
                System.err.println("Error parsing: " + st);
                return;
            }

            switch (cmd[0]) {
                case "PROJECT":
                    handle_project(cmd);
                    break;
                case "JOB":
                    handle_job(cmd);
                    break;
                case "USER":
                    handle_user(cmd[1]);
                    break;
                case "QUERY":
                    handle_query(cmd[1]);
                    break;
                case "":
                    handle_empty_line();
                    break;
                case "ADD":
                    handle_add(cmd);
                    break;
                default:
                    System.err.println("Unknown command: " + cmd[0]);
            }
            // System.out.println("<<<<<-------------------");
            // for(int i=0; i<jobs.list.size(); i++){
            //      Tuple t=jobs.list.get(i);
            //      Job j=(Job)t.value;
            //      System.out.println(j.name);
            // }
            // System.out.println("====================>>>>>>");
            //System.out.println("----------------");
            //rbt.inorder();
            //System.out.println("----------------");
        }

        //users.print();
        //System.out.println(users.search("Carrye"));
        flag=true;
        run_to_completion();
        print_stats();

    }




    @Override
    public void run() {
        //while(jobs.list.size()>0)
        //schedule();
    }


    @Override
    public void run_to_completion() {
         while(jobs.list.size()>0)
         handle_empty_line();
    }

    @Override
    public void handle_project(String[] cmd) {
         System.out.println("Creating project");
         Project newproject=new Project(cmd[1],Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]));
         projects.insert(cmd[1],newproject);
         //jobs.insert(Integer.parseInt(cmd[2]));
         //projects.print();
    }

    @Override
    public void handle_job(String[] cmd) {
         System.out.println("Creating job ");
         if(projects.search(cmd[2])!=null && users.search(cmd[3])!=null){
              //System.out.println("CCCC");
             Project p=(Project)(projects.search(cmd[2]).value);
             Job newjob=new Job(cmd[1],cmd[2],p.priority,cmd[3],Integer.parseInt(cmd[4]),global++);
             jobs.insert(newjob);
        }
        if(projects.search(cmd[2])==null){
             System.out.println("No such project exists. "+cmd[2]);
        }
        if(users.search(cmd[3])==null){
             System.out.println("No such user exists: "+cmd[3]);
        }
         //System.out.println("^^^^"+users.search(cmd[3]));
         /*for(int i=0; i<jobs.list.size(); i++){
              System.out.print(((Job)(jobs.list.get(i))).name+" / ");
         }*/
    }

    @Override
    public void handle_user(String name) {
         System.out.println("Creating user ");
         User newuser=new User(name);
         users.insert(name,newuser);
    }

    @Override
    public void handle_query(String key) {
         System.out.println("Querying ");
         Job j=jobs.search(key);
         // System.out.println("Querying1");
         boolean f=false;
         for(int i=0; i<finishedjob.size(); i++){
               Job j1=finishedjob.get(i);
               if(j1.name.equals(key)){
                    System.out.println(key+": COMPLETED");
                    f=true;
                    break;
               }
         }
         if(f==false){
              if(j!=null){
                   if(j.status.equals("REQUESTED"))
                  System.out.println(key+": NOT FINISHED");
                  else
                  System.out.println(key+": "+j.status);
            }
            else{
                  System.out.println(key+": NO SUCH JOB");
            }
         }
    }

    @Override
    public void handle_empty_line() {
         System.out.println("Running code");
         System.out.println("Remaining jobs: "+jobs.list.size());
         //System.out.println("$$");

         while(true){
              if(jobs.list.size()>0){
                   //System.out.println("X");
                   Job extractedjob=jobs.extractMax();

                   int t=extractedjob.runtime;
                   TrieNode node=projects.search(extractedjob.project);
                   Project project=(Project)node.getValue();
                   int B=project.budget;
                   System.out.println("Executing: "+extractedjob.name+" from: "+project.name);
                   if(B>=t){
                        extractedjob.setstatus("COMPLETED");
                        globaltime+=t;
                        extractedjob.setcompletedtime(globaltime);
                        project.decreaseBudget(t);
                        System.out.println("Project: "+project.name+" budget remaining: "+project.budget+" ");
                        if(flag==false){
                             System.out.println("Execution cycle completed");
                        }
                        else{
                             System.out.println("System execution completed");
                        }
                        finishedjob.add(extractedjob);
                        break;
                   }
                   else{
                        extractedjob.setstatus("REQUESTED");
                        System.out.println("Un-sufficient budget.");
                        rbt.insert(extractedjob.priority,extractedjob);
                        discardedjob.add(extractedjob);
                   }
              }
              else{

                   System.out.println("Execution cycle completed");
                   break;
              }
              /*System.out.println("!!<<<<<-------------------");
             for(int i=0; i<jobs.list.size(); i++){
                  System.out.println(jobs.list.get(i).name);
             }
             System.out.println("====================>>>>>>!!!");*/
         }
    }

    @Override
    public void handle_add(String[] cmd) {

         Project project=(Project)(projects.search(cmd[1]).value);
         if(project!=null){
              System.out.println("ADDING Budget");
              project.budget+=Integer.parseInt(cmd[2]);
              List<Job> j=rbt.search(project.priority).list;

              //rbt.search(project.priority).list.clear();
              //System.out.println("----------------");
              for(int i=0; i<j.size(); i++){
                   jobs.insert(j.get(i));
                   discardedjob.remove(j.get(i));
              }
              //System.out.println("----------------");
         }
         else{
              System.out.println("No such project exists. "+cmd[1]);
         }
    }

    @Override
    public void print_stats() {
         System.out.println("--------------STATS---------------");
         System.out.println("Total jobs done: "+finishedjob.size());
         for(int i=0; i<finishedjob.size(); i++){
              Job j=finishedjob.get(i);
              System.out.println("Job{user=’"+j.user+"’, project=’"+j.project+"’, jobstatus="+j.status+", execution_time="+j.runtime+", end_time="+j.completedtime+", name=’"+j.name+"’} ");
         }
         System.out.println("------------------------");
         System.out.println("Unfinished jobs:");
         for(int i=0; i<discardedjob.size(); i++){
              Job j=discardedjob.get(i);
              System.out.println("Job{user=’"+j.user+"’, project=’"+j.project+"’, jobstatus="+j.status+", execution_time="+j.runtime+", end_time=null, name=’"+j.name+"’} ");
         }
         System.out.println("Total unfinished jobs: "+discardedjob.size());
         System.out.println("--------------STATS DONE---------------");
    }

    @Override
    public void schedule() {

    }
}

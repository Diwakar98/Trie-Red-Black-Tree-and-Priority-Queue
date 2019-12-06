package PriorityQueue;

public class Student implements Comparable<Student> {
    private String name;
    private Integer marks;
    public static int noofstudent=0;
    int index;
    public Student(String name, int marks) {
         this.name=name;
         this.marks=new Integer(marks);
         this.index=++noofstudent;
    }


    @Override
    public int compareTo(Student student) {
         // System.out.println("^^");
        if(this.marks<student.marks){
             //System.out.println("<");
             return -1;
        }
        else if(this.marks>student.marks){
             // System.out.println(">");
             return 1;

        }
        else{

             return 0;
        }

    }

    public String getName() {
        return name;
    }
    public int getMarks(){
         return marks;
    }
    public String toString(){
         String output="Student{name=’"+this.name+"’, marks="+this.marks+"}";
         return output;
    }
}

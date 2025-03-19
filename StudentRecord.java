import java.util.Scanner;

public class StudentRecord {
   static class Student{
        int rollNo;
        String name;
        int age;
        String grade;
        Student next;

        Student(int rollNo, String name, int age,String grade){
            this.rollNo=rollNo;
            this.name=name;
            this.age=age;
            this.grade=grade;
            this.next=null;
        }
    }

    static class StudentList{
        private Student head; //points to first student in linked list

        public void addStudentAtFirst(int rollno,String name,int age,String grade){
            Student newStudent=new Student(rollno,name,age,grade);
            if(head==null){ //if ll is empty
                head=newStudent;
                return;
            }
            newStudent.next=head;
            head=newStudent;
            System.out.println("student added at the beginning successfully");
        }

        public void addStudentAtLast(int rollno,String name,int age,String grade){
            Student newStudent=new Student(rollno,name,age,grade);
            if(head==null){ //if ll is empty
                head=newStudent;
                return;
            }
            Student currStudent=head; //to traverse the ll
            while(currStudent.next!=null){
                currStudent=currStudent.next; //update krte jao jb tk null pe ni jata
            }
            currStudent.next=newStudent;
            System.out.println("student added at the end successfully");
        }

        public void addStudentAtPosition(int rollno, String name, int age, String grade, int position){
            Student newStudent=new Student(rollno, name, age, grade);
            if(position==1){
                newStudent.next=head;
                head=newStudent;
                return;
            }
            Student currStudent=head;
            for(int i=1;currStudent!=null && i<position-1;i++){
                currStudent=currStudent.next;
            }
            if(currStudent==null){
                System.out.println("out of range");
                return;
            }
            newStudent.next=currStudent.next;
            currStudent.next=newStudent;
            System.out.println("student added at position "+position+" successfully");
        }
        public void deleteStudent(int rollno){
            Student currStudent=head;
            if(head==null){
                System.out.println("list empty");
                return;
            }
            if(head.rollNo==rollno){
                head=head.next;
                System.out.println("student deleted ");
                return;
            }
            while (currStudent.next!=null){
                if(currStudent.next.rollNo==rollno){
                    currStudent.next=currStudent.next.next;
                    System.out.println("Student deleted successfully.");
                   return;
                }
                currStudent=currStudent.next;
            }
            System.out.println("Student with Roll Number " + rollno + " not found.");
        }

        public boolean searchStudent(int rollNo){
            Student currStudent=head;
            while(currStudent!=null){
                if(currStudent.rollNo==rollNo){
                    System.out.println("Student found: Roll No: " + currStudent.rollNo + ", Name: " + currStudent.name);
                    return true;
                }
                currStudent=currStudent.next;
            }
            System.out.println("Student with Roll Number " + rollNo + " not found.");
            return false;
        }

        public void updateStudent(int rollNo, String newGrade){
            Student currStudent=head;
            while(currStudent!=null){
                if(currStudent.rollNo==rollNo){
                    currStudent.grade=newGrade;
                    System.out.println("Grade updated successfully for Roll Number: " + rollNo);
                }
                currStudent=currStudent.next;
            }
            System.out.println("Student with Roll Number " + rollNo + " not found.");
        }

        public void displayStudents(){
            Student current=head;
            if(current==null){
                System.out.println("No records found");
                return;
            }
            while(current!=null){
                System.out.println("Roll Number: " + current.rollNo + ", Name: " + current.name +
                        ", Age: " + current.age + ", Grade: " + current.grade);
                current=current.next;
            }

        }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentList list = new StudentList();
        list.addStudentAtFirst(2110990913,"Naman", 21, "O");
        list.addStudentAtLast(2110990862,"manvi",22,"A+");
        list.addStudentAtPosition(2121112121, "reerfer", 21,"A",3);
        list.deleteStudent(2110990913);
        list.searchStudent(2110990862);
        list.updateStudent(2121112121,"C");
        list.displayStudents();






    }

}

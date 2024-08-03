import java.util.Scanner;
import java.util.ArrayList;

class Student{
    int studentID;
    String name;
    int age;
    String department;

    public Student(int studentID, String name, int age, String department){
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public int getStudentID(){
        return studentID;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getDepartment(){
        return department;
    }

    public String toString(){
        return "Student ID: " + studentID + ", Name: " + name + ", Age: " + age + ", Department: " + department;
    }
}

class StudentRecordSystem{
    ArrayList<Student> students;
     int count;

    public StudentRecordSystem(){
        students = new ArrayList<>();
        count = 0;
    }

    public void addStudent(Student student){
        students.add(student);
        count++;
    }

    public Student getStudent(int studentID){
        for(Student student : students){
            if (student.getStudentID() == studentID){
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents(){
        for(Student student : students){
            System.out.println(student);
        }
    }
}

public class StudentRecordMGMT{
    public static void main(String[] args){
        StudentRecordSystem record = new StudentRecordSystem();
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("1. Add Student");
            System.out.println("2. Get Student by ID");
            System.out.println("3. Display All Students");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch(choice){
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();
                    Student student = new Student(id, name, age, department);
                    record.addStudent(student);
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    int searchId = scanner.nextInt();
                    Student foundStudent = record.getStudent(searchId);
                    if(foundStudent != null){
                        System.out.println("Student Found: " + foundStudent);
                    }
                    else{
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    record.displayAllStudents();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

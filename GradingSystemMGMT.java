import java.util.Scanner;

class Student{
    int studentID;
    String name;

    public Student(int studentID, String name){
        this.studentID = studentID;
        this.name = name;
    }

    public int getStudentID(){
        return studentID;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return "Student ID: " + studentID + ", Name: " + name;
    }
}

class Grade{
    int studentID;
    int courseID;
    double grade;

    public Grade(int studentID, int courseID, double grade){
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    public int getStudentID(){
        return studentID;
    }

    public int getCourseID(){
        return courseID;
    }

    public double getGrade(){
        return grade;
    }
}

class GradingSystem{
    Student[] students;
    Grade[] grades;
    int studentCount;
    int gradeCount;

    public GradingSystem(int maxStudents, int maxGrades){
        students = new Student[maxStudents];
        grades = new Grade[maxGrades];
        studentCount = 0;
        gradeCount = 0;
    }

    public void addStudent(Student student){
        if (studentCount < students.length){
            students[studentCount++] = student;
        }
        else{
            System.out.println("Cannot add more students.");
        }
    }

    public void addGrade(Grade grade){
        if (gradeCount < grades.length){
            grades[gradeCount++] = grade;
        }
        else{
            System.out.println("Cannot add more grades.");
        }
    }

    public double calculateGPA(int studentID){
        int totalCourses = 0;
        double totalGrades = 0.0;

        for (int i = 0; i < gradeCount; i++){
            if (grades[i].getStudentID() == studentID){
                totalGrades += grades[i].getGrade();
                totalCourses++;
            }
        }

        if (totalCourses == 0){
            return 0.0;
        }

        return totalGrades / totalCourses;
    }

    public void printGradeReport(int studentID){
        System.out.println("Grade report for Student ID " + studentID + ":");

        for (int i = 0; i < gradeCount; i++){
            if (grades[i].getStudentID() == studentID){
                System.out.println("Course ID: " + grades[i].getCourseID() + ", Grade: " + grades[i].getGrade());
            }
        }

        double gpa = calculateGPA(studentID);
        System.out.println("GPA: " + gpa);
    }
}

public class GradingSystemMGMT{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        GradingSystem gradingSystem = new GradingSystem(100, 1000);

        while(true){
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Calculate GPA");
            System.out.println("4. Print Grade Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch(choice)
            {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    Student student = new Student(studentID, name);
                    gradingSystem.addStudent(student);
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    System.out.print("Enter Course ID: ");
                    int courseID = scanner.nextInt();
                    System.out.print("Enter Grade: ");
                    double grade = scanner.nextDouble();
                    Grade newGrade = new Grade(studentID, courseID, grade);
                    gradingSystem.addGrade(newGrade);
                    System.out.println("Grade added successfully.");
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    double gpa = gradingSystem.calculateGPA(studentID);
                    System.out.println("GPA for Student ID " + studentID + ": " + gpa);
                    break;

                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    gradingSystem.printGradeReport(studentID);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

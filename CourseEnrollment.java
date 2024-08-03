import java.util.Scanner;
class Course{
    int courseID;
    String courseName;
    int credits;

    public Course(int courseID, String courseName, int credits){
        this.courseID = courseID;
        this.courseName = courseName;
        this.credits = credits;
    }

    public int getCourseID(){
        return courseID;
    }

    public String getCourseName(){
        return courseName;
    }

    public int getCredits(){
        return credits;
    }

    public String toString(){
        return "Course ID: " + courseID + ", Course Name: " + courseName + ", Credits: " + credits;
    }
}

class Enrollment{
    int[][] studentCourses;
    int[] count;
    int maxCourses;

    public Enrollment(int maxStudents, int maxCourses){
        studentCourses = new int[maxStudents][maxCourses];
        count = new int[maxStudents];
        this.maxCourses = maxCourses;
        for (int i = 0; i < maxStudents; i++){
            for (int j = 0; j < maxCourses; j++){
                studentCourses[i][j] = -1;
            }
            count[i] = 0;
        }
    }

    public void enroll(int studentID, int courseID){
        if (count[studentID] < maxCourses){
            for (int i = 0; i < maxCourses; i++){
                if (studentCourses[studentID][i] == courseID){
                    System.out.println("Student " + studentID + " is already enrolled in course " + courseID);
                    return;
                }
                if (studentCourses[studentID][i] == -1){
                    studentCourses[studentID][i] = courseID;
                    count[studentID]++;
                    System.out.println("Student " + studentID + " enrolled in course " + courseID);
                    return;
                }
            }
        }
        else{
            System.out.println("Student " + studentID + " cannot enroll in more courses.");
        }
    }

    public void drop(int studentID, int courseID){
        for (int i = 0; i < maxCourses; i++){
            if (studentCourses[studentID][i] == courseID){
                studentCourses[studentID][i] = -1;
                count[studentID]--;
                System.out.println("Student " + studentID + " dropped course " + courseID);
                return;
            }
        }
        System.out.println("Student " + studentID + " is not enrolled in course " + courseID);
    }

    public void getEnrolledCourses(int studentID, Course[] courseCatalog){
        System.out.println("Student " + studentID + " is enrolled in the following courses:");
        for (int i = 0; i < maxCourses; i++){
            if (studentCourses[studentID][i] != -1){
                for (Course course : courseCatalog){
                    if (course.getCourseID() == studentCourses[studentID][i]){
                        System.out.println(course);
                    }
                }
            }
        }
    }
}

public class CourseEnrollment{
    Course[] courseCatalog;
    Enrollment enrollment;

    public CourseEnrollment(){
        courseCatalog = new Course[]{
            new Course(1, "DSA", 3),
            new Course(2, "Java", 4),
            new Course(3, "COA", 3),
            new Course(4, "Maths", 4)
        };
        enrollment = new Enrollment(100, 10);
    }

    public void enrollStudent(int studentID, int courseID){
        enrollment.enroll(studentID, courseID);
    }

    public void dropCourse(int studentID, int courseID){
        enrollment.drop(studentID, courseID);
    }

    public void viewEnrolledCourses(int studentID){
        enrollment.getEnrolledCourses(studentID, courseCatalog);
    }

    public static void main(String[] args){
        CourseEnrollment system = new CourseEnrollment();
        Scanner scanner = new Scanner(System.in);
        
        while (true){
            System.out.println("1. Enroll a student in a course");
            System.out.println("2. Drop a course for a student");
            System.out.println("3. View all courses a student is enrolled in");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch(choice){
                case 1:
                    System.out.print("Enter student ID: ");
                    int studentID = scanner.nextInt();
                    System.out.print("Enter course ID: ");
                    int courseID = scanner.nextInt();
                    system.enrollStudent(studentID, courseID);
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextInt();
                    System.out.print("Enter course ID: ");
                    courseID = scanner.nextInt();
                    system.dropCourse(studentID, courseID);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextInt();
                    system.viewEnrolledCourses(studentID);
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

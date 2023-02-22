import java.util.*;

public class StudentDatabase {

    static Student head;

    static class Student {

        private String name;

        public 
        // arrays are used to take values of multiple semester
        int sem[] = new int[2];
        int math[] = new int[2];
        int science[] = new int[2];
        int english[] = new int[2];

        Student next;

        Student(String name) {
            this.name = name;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        System.out.println("\n\t\t\tWelcome to the Program\n");
        showContent();
    }

    public static void showContent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Table of content -> ");
        System.out.println("1) Add Student");
        System.out.println("2) See Report");
        System.out.println("3) Exit");
        System.out.print("\nEnter your choice : ");
        int choice = sc.nextInt();

        performAction(choice);
    }

    public static void performAction(int choice) {
        switch (choice) {
            case 1:
                addStudent();
                System.out.println();
                showContent();
                break;

            case 2:
                seeReportContent();
                System.out.println();
                showContent();
                break;

            case 3:
                terminate();
                break;

            default:
                System.out.println("\nInvalid choice !\n");
                showContent();
                break;
        }
    }

    public static void addStudent() {

        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter name : ");
        String name = sc.nextLine();

        Student newStudent = new Student(name);

        newStudent.sem[0] = setValue("\nEnter semester : ", true);

        System.out.println("Enter Marks(out of 100)");

        newStudent.science[0] = setValue("Science : ", false);
        newStudent.math[0] = setValue("Maths : ", false);
        newStudent.english[0] = setValue("English : ", false);

        newStudent.sem[1] = setValue("\nEnter another semester : ", true);

        if (newStudent.sem[0] == newStudent.sem[1]) {
            System.out.println("Semester already exist !");
            newStudent.sem[1] = setValue("Enter second semester : ", true);
        }

        System.out.println("Enter Marks(out of 100)");

        newStudent.science[1] = setValue("Science : ", false);
        newStudent.math[1] = setValue("Maths : ", false);
        newStudent.english[1] = setValue("English : ", false);

        if (head == null) {
            head = newStudent;
            return;
        }
        Student temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStudent;

        System.out.println("\nStudent added successfully!\n");
    }

    public static int setValue(String message, boolean flag) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        int data = sc.nextInt();
        if (flag == true) {
            if (data > 10) {
                System.out.println("Please enter valid number !");
                data = setValue(message, flag);
            }
            if (data < 0) {
                System.out.println("Semester number can't be negative \nPlease enter valid number !");
                data = setValue(message, flag);
            }
        } else {
            if (data > 100) {
                System.out.println("Please enter valid number !");
                data = setValue(message, flag);
            }
            if (data < 0) {
                System.out.println("Marks can't be negative \nPlease enter valid number !");
                data = setValue(message, flag);
            }
        }
        return data;
    }

    public static void seeReportContent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nTable of Contents : ->");
        System.out.println("1) Average percentage of whole class");
        System.out.println("2) Average marks of students");
        System.out.println("3) Top 2 consistent student");
        System.out.print("\nEnter your choice : ");
        int choice = sc.nextInt();
        seeReport(choice);
    }

    public static void seeReport(int choice) {
        switch (choice) {
            case 1:
                averagePercentage();
                showContent();
                break;

            case 2:
                averageMarks();
                showContent();
                break;

            case 3:
                topStudents();
                showContent();
                break;

            default:
                System.out.print("\nInvalid Choice !");
                seeReportContent();
        }
    }

    public static void averagePercentage() {

        checkHead();

        System.out.println("\nAverage Percentage of class :\n");
        System.out.println("---------------------------");
        System.out.println("|   Name     | Percentage |");
        System.out.println("---------------------------");

        Student temp = head;
        while (temp != null) {
            float sum = temp.math[0] + temp.math[1] + temp.science[0] + temp.science[1] + temp.english[0] + temp.english[1];
            System.out.printf("| %-10s |    %.2f   |\n", temp.name, sum / 6);
            temp = temp.next;
        }
        System.out.println("---------------------------\n");
    }

    public static void averageMarks() {

        checkHead();

        Student temp = head;

        System.out.println("\nAverage marks of students :\n");
        System.out.println("--------------------------------------------");
        System.out.println("|   Name     | Science |  Maths  | English |");
        System.out.println("--------------------------------------------");

        while (temp != null) {
            float english = 0, math = 0, science = 0;

            math += temp.math[0] + temp.math[1];
            science += temp.science[0] + temp.science[1];
            english += temp.english[0] + temp.english[1];

            System.out.printf("| %-10s |   %.2f |   %.2f |   %.2f |\n", temp.name, science / 2, math / 2, english / 2);
            temp = temp.next;
        }
        System.out.println("--------------------------------------------\n");
    }

    public static void topStudents() {

        checkHead();

        Student temp = head;
        String top1 = temp.name, top2 = temp.name;
        int sum1 = 0, sum2 = 0;
        while (temp != null) {
            if (sum1 < temp.math[0] + temp.english[0] + temp.science[0]) {
                sum1 = temp.math[0] + temp.english[0] + temp.science[0];
                top1 = temp.name;
            }
            if (sum2 < temp.math[1] + temp.english[1] + temp.science[1]) {
                sum2 = temp.math[1] + temp.english[1] + temp.science[1];
                top2 = temp.name;
            }

            temp = temp.next;
        }

        System.out.println("\nTop student of first semester : " + top1 + "\n");

        System.out.println("Top student of second semester : " + top2 + "\n");
    }

    public static void checkHead() {
        if (head == null) {
            System.out.println("\nEmpty table!\n");
            showContent();
            return;
        }
    }

    public static void terminate() {
        System.out.println("\nThanks...\n");
        System.out.println("Program terminated !");
        System.exit(0);
    }

}
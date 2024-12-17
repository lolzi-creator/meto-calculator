import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    private final ArrayList<Student> students = new ArrayList<>();
    private int nextId = 1; // Auto-increment ID for students

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("\nWelcome to the Student Management System!");

        while (keepRunning) {
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student Grades");
            System.out.println("4. View Average Grade");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addStudent(scanner);
                case "2" -> viewStudents();
                case "3" -> updateStudentGrades(scanner);
                case "4" -> viewAverageGrade();
                case "5" -> {
                    System.out.println("Exiting Student Management System. Goodbye!");
                    keepRunning = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Add a new student
    private void addStudent(Scanner scanner) {
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial grade (0-100): ");
        double grade = getValidDouble(scanner, 0, 100);

        Student student = new Student(nextId++, name, grade);
        students.add(student);
        System.out.println("Student added successfully!");
    }

    // View all students
    private void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students recorded.");
        } else {
            System.out.println("\n--- List of Students ---");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Update a student's grades
    private void updateStudentGrades(Scanner scanner) {
        System.out.print("Enter the ID of the student to update: ");
        int id = getValidInt(scanner);

        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("No student found with the given ID.");
            return;
        }

        System.out.print("Enter the new grade (0-100): ");
        double grade = getValidDouble(scanner, 0, 100);
        student.setGrade(grade);
        System.out.println("Student's grade updated successfully!");
    }

    // View average grade of all students
    private void viewAverageGrade() {
        if (students.isEmpty()) {
            System.out.println("No students recorded.");
            return;
        }

        double total = 0;
        for (Student student : students) {
            total += student.getGrade();
        }
        double average = total / students.size();
        System.out.printf("\nAverage Grade: %.2f%n", average);
    }

    // Helper: Find a student by ID
    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Helper: Get valid integer input
    private int getValidInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
            }
        }
    }

    // Helper: Get valid double input within range
    private double getValidDouble(Scanner scanner, double min, double max) {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine());
                if (value < min || value > max) {
                    throw new IllegalArgumentException();
                }
                return value;
            } catch (IllegalArgumentException e) {
                System.out.printf("Invalid input. Please enter a number between %.1f and %.1f: ", min, max);
            }
        }
    }
}
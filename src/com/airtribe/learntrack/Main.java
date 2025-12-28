package com.airtribe.learntrack;

import java.util.List;
import java.util.Scanner;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();

    public static void main(String[] args) {
        System.out.println("===== Welcome to LearnTrack =====");

        while (true) {
            showMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> viewStudents();
                    case 3 -> addCourse();
                    case 4 -> viewCourses();
                    case 5 -> enrollStudent();
                    case 6 -> viewEnrollmentsByStudent();
                    case 0 -> {
                        System.out.println("Exiting LearnTrack. Goodbye!");
                        System.exit(0);
                    }
                    default -> throw new InvalidInputException("Invalid menu option");
                }

            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n----- MENU -----");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Add Course");
        System.out.println("4. View Courses");
        System.out.println("5. Enroll Student in Course");
        System.out.println("6. View Enrollments by Student");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    // -------- Student flows --------

    private static void addStudent() {
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Batch: ");
        String batch = scanner.nextLine();

        Student student = studentService.addStudent(firstName, lastName, email, batch);
        System.out.println("✅ Student added with ID: " + student.getId());
    }

    private static void viewStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        students.forEach(s ->
            System.out.println(s.getId() + " | " + s.getDisplayName() + " | Active: " + s.isActive())
        );
    }

    // -------- Course flows --------

    private static void addCourse() {
        System.out.print("Course Name: ");
        String name = scanner.nextLine();

        System.out.print("Description: ");
        String desc = scanner.nextLine();

        System.out.print("Duration (weeks): ");
        int duration = Integer.parseInt(scanner.nextLine());

        Course course = courseService.addCourse(name, desc, duration);
        System.out.println("✅ Course added with ID: " + course.getId());
    }

    private static void viewCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        courses.forEach(c ->
            System.out.println(c.getId() + " | " + c.getCourseName() + " | Status: " + c.getStatus())
        );
    }

    // -------- Enrollment flows --------

    private static void enrollStudent() throws EntityNotFoundException {
        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Course ID: ");
        int courseId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enrollment Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId, date);
        System.out.println("✅ Enrollment successful. Enrollment ID: " + enrollment.getId());
    }

    private static void viewEnrollmentsByStudent() {
        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for this student.");
            return;
        }

        enrollments.forEach(e ->
            System.out.println(
                "Enrollment ID: " + e.getId() +
                " | Course ID: " + e.getCourseId() +
                " | Status: " + e.getStatus()
            )
        );
    }
}

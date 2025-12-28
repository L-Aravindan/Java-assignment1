# LearnTrack – Student & Course Management System (Core Java)

## About

LearnTrack is a simple **console-based application** built using **Core Java** to manage students, courses, and enrollments.

I built this project to practice **Java fundamentals and OOP concepts**, and to understand how a small application can be structured cleanly without using frameworks or databases.

## What I Practiced
- Core Java basics
- Object-Oriented Programming (encapsulation, inheritance, polymorphism)
- Collections (`ArrayList`)
- Custom exception handling
- Layered application structure
- Menu-driven console programs

## Features
- Add and view students
- Add and view courses
- Enroll students into courses
- View enrollments by student
- Activate/deactivate entities instead of deleting them


## Project Structure

```
src/
└── com/
    └── airtribe/
        └── learntrack/
            ├── Main.java
            │
            ├── entity/
            │   ├── Person.java
            │   ├── Student.java
            │   ├── Course.java
            │   └── Enrollment.java
            │
            ├── repository/
            │   ├── StudentRepository.java
            │   ├── CourseRepository.java
            │   └── EnrollmentRepository.java
            │
            ├── service/
            │   ├── StudentService.java
            │   ├── CourseService.java
            │   └── EnrollmentService.java
            │
            ├── util/
            │   ├── IdGenerator.java
            │   └── InputValidator.java
            │
            ├── exception/
            │   ├── EntityNotFoundException.java
            │   └── InvalidInputException.java
            │
            ├── enums/
            │   ├── EnrollmentStatus.java
            │   └── CourseStatus.java
            │
            └── constants/
                ├── MenuOptions.java
                └── AppConstants.java
```

##CLASS DIAGRAM

```

                ┌──────────────────┐
                │      Person      │
                │──────────────────│
                │ id               │
                │ firstName        │
                │ lastName         │
                │ email            │
                │──────────────────│
                │ getDisplayName() │
                └─────────▲────────┘
                          │
                     extends
                          │
                ┌──────────────────┐
                │      Student     │
                │──────────────────│
                │ batch            │
                │ active           │
                │──────────────────│
                │ getDisplayName() │
                └──────────────────┘


┌──────────────────┐        ┌──────────────────┐
│      Course      │        │    Enrollment    │
│──────────────────│        │──────────────────│
│ id               │        │ id               │
│ courseName       │        │ studentId        │
│ description      │        │ courseId         │
│ durationInWeeks  │        │ enrollmentDate   │
│ status           │        │ status            │
└──────────────────┘        └──────────────────┘


Repositories (In-Memory)
-----------------------
StudentRepository
- stores List<Student>

CourseRepository
- stores List<Course>

EnrollmentRepository
- stores List<Enrollment>


Services (Business Logic)
------------------------
StudentService
- uses StudentRepository

CourseService
- uses CourseRepository

EnrollmentService
- uses EnrollmentRepository

```


## How to Run
From the project root:

USE BASH:

javac -d out $(find src -name "*.java")
java -cp out com.airtribe.learntrack.Main
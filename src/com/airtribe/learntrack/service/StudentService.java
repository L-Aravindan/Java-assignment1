package com.airtribe.learntrack.service;

import java.util.List;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;

public class StudentService {

    private final StudentRepository studentRepository = new StudentRepository();

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        int id = IdGenerator.nextStudentId();
        Student student = new Student(id, firstName, lastName, email, batch);
        studentRepository.save(student);
        return student;
    }

    public Student getStudentById(int id) throws EntityNotFoundException {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new EntityNotFoundException("Student with ID " + id + " not found");
        }
        return student;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deactivateStudent(int id) throws EntityNotFoundException {
        Student student = getStudentById(id);
        student.setActive(false);
    }
}

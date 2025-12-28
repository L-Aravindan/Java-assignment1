package com.airtribe.learntrack.service;

import java.util.List;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

    public Enrollment enrollStudent(int studentId, int courseId, String enrollmentDate) {
        int id = IdGenerator.nextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId, enrollmentDate);
        enrollmentRepository.save(enrollment);
        return enrollment;
    }

    public Enrollment getEnrollmentById(int id) throws EntityNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(id);
        if (enrollment == null) {
            throw new EntityNotFoundException("Enrollment with ID " + id + " not found");
        }
        return enrollment;
    }

    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public void updateEnrollmentStatus(int enrollmentId, EnrollmentStatus status)
            throws EntityNotFoundException {
        Enrollment enrollment = getEnrollmentById(enrollmentId);
        enrollment.setStatus(status);
    }
}

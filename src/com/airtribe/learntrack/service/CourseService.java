package com.airtribe.learntrack.service;

import java.util.List;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;

public class CourseService {

    private final CourseRepository courseRepository = new CourseRepository();

    public Course addCourse(String name, String description, int durationInWeeks) {
        int id = IdGenerator.nextCourseId();
        Course course = new Course(id, name, description, durationInWeeks);
        courseRepository.save(course);
        return course;
    }

    public Course getCourseById(int id) throws EntityNotFoundException {
        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new EntityNotFoundException("Course with ID " + id + " not found");
        }
        return course;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void deactivateCourse(int id) throws EntityNotFoundException {
        Course course = getCourseById(id);
        course.setStatus(CourseStatus.INACTIVE);
    }

    public void activateCourse(int id) throws EntityNotFoundException {
        Course course = getCourseById(id);
        course.setStatus(CourseStatus.ACTIVE);
    }
}

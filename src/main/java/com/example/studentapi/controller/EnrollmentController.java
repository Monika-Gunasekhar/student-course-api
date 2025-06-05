package com.example.studentapi.controller;

import com.example.studentapi.model.Course;
import com.example.studentapi.model.Enrollment;
import com.example.studentapi.repository.CourseRepository;
import com.example.studentapi.repository.EnrollmentRepository;
import com.example.studentapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public List<Enrollment> enrollStudent(@RequestBody Map<String, Long> request) {
        Long studentId = request.get("studentId");
        Long courseId = request.get("courseId");

        Enrollment enrollment = new Enrollment(studentId,courseId);

        enrollmentRepository.save(enrollment);
        return enrollmentRepository.findByStudentId(studentId);
    }


    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @GetMapping("/students/{studentId}/courses")
    public List<Course> getCoursesByStudent(@PathVariable Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        return enrollments.stream()
                .map(e -> courseRepository.findById(e.getCourseId()).orElse(null))
                .collect(Collectors.toList());
    }

}

package com.Universitydemo.demo.serviceinterface;

import com.Universitydemo.demo.entity.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    List<Student> findAllStudents();

    List<Student> findStudentsByGuardianName(String guardianName);

    Student findStudentById(Long studentId);

    Student updateStudent(Long studentId, Student updatedStudent);

    void deleteStudent(Long studentId);

    Student addCourseToStudent(Long studentId, Long courseId);
    Student removeCourseFromStudent(Long studentId, Long courseId);

}

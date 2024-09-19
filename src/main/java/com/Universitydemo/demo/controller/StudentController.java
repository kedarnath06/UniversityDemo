package com.Universitydemo.demo.controller;

import com.Universitydemo.demo.entity.Student;
import com.Universitydemo.demo.serviceinterface.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
//        if (studentRepository.existsByGuardianEmail(student.getGuardianEmail())) {
//            throw new DuplicateGuardianEmailException("Guardian email already exists");
//        }
        return ResponseEntity.status(201).body(studentService.createStudent(student));
    }

    @GetMapping("/findAllStudents")
    public ResponseEntity<List<Student>> findAllStudents() {
        List<Student> studentList = studentService.findAllStudents();
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/findStudentByStudentId/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable Long id) {
        Student student = studentService.findStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/findStudentsGuardianName/{guardianName}")
    public ResponseEntity<List<Student>> getStudentsByGuardianName(@PathVariable String guardianName) {
        List<Student> students = studentService.findStudentsByGuardianName(guardianName);
        return ResponseEntity.ok(students); // HTTP 200 OK
    }

    @PutMapping("/updateStudentsById/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent); // HTTP 200 OK
    }

    @DeleteMapping("/deleteStudentById/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

    // Add a course to a student
    @PostMapping("/addCourseToStudent/{studentId}/{courseId}")
    public ResponseEntity<Student> addCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        Student updatedStudent = studentService.addCourseToStudent(studentId, courseId);
        return ResponseEntity.ok(updatedStudent); // HTTP 200 OK
    }

    // Remove a course from a student
    @DeleteMapping("/removeCourseFromStudent/{studentId}/{courseId}")
    public ResponseEntity<Student> removeCourseFromStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        Student updatedStudent = studentService.removeCourseFromStudent(studentId, courseId);
        return ResponseEntity.ok(updatedStudent); // HTTP 200 OK
    }

}

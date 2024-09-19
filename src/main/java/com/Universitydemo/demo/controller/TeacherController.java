package com.Universitydemo.demo.controller;

import com.Universitydemo.demo.entity.Teacher;
import com.Universitydemo.demo.serviceinterface.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // Create a new teacher
    @PostMapping("/createTeacher")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher createdTeacher = teacherService.createTeacher(teacher);
        return ResponseEntity.status(201).body(createdTeacher); // HTTP 201 Created
    }

    // Get all teachers
    @GetMapping("/getAllTeachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.findAllTeachers();
        return ResponseEntity.ok(teachers); // HTTP 200 OK
    }

    // Get teacher by ID
    @GetMapping("/GetTeacherByTeacherId/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long teacherId) {
        Teacher teacher = teacherService.findTeacherById(teacherId);
        return ResponseEntity.ok(teacher); // HTTP 200 OK
    }

    // Update teacher by ID
    @PutMapping("/updateTeacherByTeacherId/{teacherId}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long teacherId, @RequestBody Teacher updatedTeacher) {
        Teacher updated = teacherService.updateTeacher(teacherId, updatedTeacher);
        return ResponseEntity.ok(updated); // HTTP 200 OK
    }

    // Delete teacher by ID
    @DeleteMapping("/deleteTeacherByTeacherId/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

    // Add a course to a teacher
    @PostMapping("/addCourseToTeacher/{teacherId}/{courseId}")
    public ResponseEntity<Teacher> addCourseToTeacher(@PathVariable Long teacherId, @PathVariable Long courseId) {
        Teacher updatedTeacher = teacherService.addCourseToTeacher(teacherId, courseId);
        return ResponseEntity.ok(updatedTeacher); // HTTP 200 OK
    }

    // Remove a course from a teacher
    @DeleteMapping("/removeCourseFromTeacher/{teacherId}/{courseId}")
    public ResponseEntity<Teacher> removeCourseFromTeacher(@PathVariable Long teacherId, @PathVariable Long courseId) {
        Teacher updatedTeacher = teacherService.removeCourseFromTeacher(teacherId, courseId);
        return ResponseEntity.ok(updatedTeacher); // HTTP 200 OK
    }
}

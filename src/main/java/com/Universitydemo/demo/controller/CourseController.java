package com.Universitydemo.demo.controller;

import com.Universitydemo.demo.entity.Course;
import com.Universitydemo.demo.serviceinterface.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService=courseService;
    }

//

    @PostMapping("/createCourse")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            Course createdCourse = courseService.createCourse(course);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAllCourses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.findAll();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/findCoursesByCourseId/{id}")
    public ResponseEntity<Course> getCoursesByCourseId(@PathVariable Long id) {
        Course course = courseService.findById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/findCourseByTeacherId/{teacherId}")
    public ResponseEntity<List<Course>> getCoursesByTeacherId(@PathVariable Long teacherId) {
        List<Course> course = courseService.findCourseByTeacherId(teacherId);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/updateCourseByStudentId/{id}")
    public ResponseEntity<Course> updateCourseById(@PathVariable Long id, @RequestBody Course course) {
        Course updatedCourse = courseService.updateById(id);
        return ResponseEntity.ok(updatedCourse); // HTTP 200 OK
    }

    @DeleteMapping("/deleteCourseByStudentId/{id}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable Long id) {
        courseService.deleteById(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

}

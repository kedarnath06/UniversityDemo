package com.Universitydemo.demo.controller;

import com.Universitydemo.demo.entity.CourseMaterial;
import com.Universitydemo.demo.exception.CourseMaterialNotFoundException;
import com.Universitydemo.demo.serviceinterface.CourseMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-materials")
public class CourseMaterialController {

    @Autowired
    private CourseMaterialService courseMaterialService;

    @PostMapping("/addCourse-materials")
    public ResponseEntity<CourseMaterial> createCourseMaterials(@RequestBody CourseMaterial courseMaterial) {
        CourseMaterial savedcourseMaterial = courseMaterialService.saveCourseMaterial(courseMaterial);
        return ResponseEntity.ok(savedcourseMaterial);
    }

    @GetMapping("/findAll-materials")
    public ResponseEntity<List<CourseMaterial>> getAllCourseMaterials() {
        List<CourseMaterial> courseMaterials = courseMaterialService.findAll();
        return ResponseEntity.ok(courseMaterials);
    }

    @GetMapping("/findCourse-materialsbyid/{id}")
    public ResponseEntity<CourseMaterial> getCourseMaterialsById(@PathVariable Long id) {
        CourseMaterial courseMaterial = courseMaterialService.findById(id)
                .orElseThrow(() -> new CourseMaterialNotFoundException("CourseMaterial with id " + id + " not found"));
        return ResponseEntity.ok(courseMaterial);
    }

    @GetMapping("/findcourse-materialsbycourseId/{courseId}")
    public ResponseEntity<CourseMaterial> getCourseMaterialByCourseId(@PathVariable Long courseId) {
        CourseMaterial courseMaterial = courseMaterialService.findCourseMaterialByCourseId(courseId);
        return ResponseEntity.ok(courseMaterial);
    }

    @PutMapping("/updateCourse-materialsById/{id}")
    public ResponseEntity<CourseMaterial> updateCourseMaterials(@PathVariable Long id) {
        CourseMaterial updatedcourseMaterial = courseMaterialService.updateById(id);
        return ResponseEntity.ok(updatedcourseMaterial);// HTTP 200 OK with updated entity
    }

    @DeleteMapping("/deletecourse-materialsById/{id}")
    public ResponseEntity<CourseMaterial> deleteCourseMaterials(@PathVariable Long id) {
        courseMaterialService.deleteById(id);
        return ResponseEntity.noContent().build();// HTTP 204 No Content
    }

}

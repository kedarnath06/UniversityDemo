package com.Universitydemo.demo.serviceinterface;

import com.Universitydemo.demo.entity.Course;
import com.Universitydemo.demo.entity.CourseMaterial;

import java.util.List;
import java.util.Optional;

public interface CourseMaterialService {

    List<CourseMaterial> findAll();
    Optional<CourseMaterial> findById(Long id);
    CourseMaterial saveCourseMaterial(CourseMaterial courseMaterial);
    CourseMaterial findCourseMaterialByCourseId(Long courseId);
    CourseMaterial updateById(Long id);
    void deleteById(Long id);

}

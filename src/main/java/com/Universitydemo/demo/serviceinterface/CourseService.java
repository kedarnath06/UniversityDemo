package com.Universitydemo.demo.serviceinterface;

import com.Universitydemo.demo.entity.Course;

import java.util.List;

public interface CourseService {

    Course createCourse(Course course);
    List<Course> findAll();
    Course findById(Long id);
    List<Course> findCourseByTeacherId(Long teacherId);
//    Course findByName(String name);
    Course updateById(Long id);
    void deleteById(Long id);


}

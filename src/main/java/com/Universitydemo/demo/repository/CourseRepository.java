package com.Universitydemo.demo.repository;

import com.Universitydemo.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    // Custom query method to find courses by the teacher's ID
    List<Course> findByTeacherTeacherId(Long teacherId);

}

package com.Universitydemo.demo.repository;

import com.Universitydemo.demo.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial,Long> {

    Optional<CourseMaterial> findByCourseCourseId(Long courseId);

}

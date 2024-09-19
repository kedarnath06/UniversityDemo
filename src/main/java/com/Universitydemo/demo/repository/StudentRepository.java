package com.Universitydemo.demo.repository;

import com.Universitydemo.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    List<Student> findStudentByGuardian_Name(String guardianName);

}

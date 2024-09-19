package com.Universitydemo.demo.serviceinterface;

import com.Universitydemo.demo.entity.Teacher;
import java.util.List;

public interface TeacherService {

    // Create a new teacher
    Teacher createTeacher(Teacher teacher);


    // Retrieve all teachers
    List<Teacher> findAllTeachers();

    // Find a teacher by ID
    Teacher findTeacherById(Long teacherId);

    // Update an existing teacher
    Teacher updateTeacher(Long teacherId, Teacher updatedTeacher);

    // Delete a teacher by ID
    void deleteTeacher(Long teacherId);

    // Add a course to a teacher's list of courses
    Teacher addCourseToTeacher(Long teacherId, Long courseId);

    // Remove a course from a teacher's list of courses
    Teacher removeCourseFromTeacher(Long teacherId, Long courseId);
}

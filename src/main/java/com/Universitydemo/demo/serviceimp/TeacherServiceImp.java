package com.Universitydemo.demo.serviceimp;

import com.Universitydemo.demo.entity.Course;
import com.Universitydemo.demo.entity.Teacher;
import com.Universitydemo.demo.exception.TeacherNotFoundException;
import com.Universitydemo.demo.repository.CourseRepository;
import com.Universitydemo.demo.repository.TeacherRepository;
import com.Universitydemo.demo.serviceinterface.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImp implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public TeacherServiceImp(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + teacherId));
    }

    @Override
    public Teacher updateTeacher(Long teacherId, Teacher updatedTeacher) {
        return teacherRepository.findById(teacherId).map(existingTeacher -> {
            existingTeacher.setFirstName(updatedTeacher.getFirstName());
            existingTeacher.setLastName(updatedTeacher.getLastName());
            existingTeacher.setCourses(updatedTeacher.getCourses());
            return teacherRepository.save(existingTeacher);
        }).orElseThrow(() -> new TeacherNotFoundException("Teacher with id: " + teacherId + " not found."));
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        if (teacherRepository.existsById(teacherId)) {
            teacherRepository.deleteById(teacherId);
        } else {
            throw new TeacherNotFoundException("Teacher with id: " + teacherId + " not found.");
        }
    }

    @Override
    public Teacher addCourseToTeacher(Long teacherId, Long courseId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with id: " + teacherId + " does not exist."));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new TeacherNotFoundException("Course with id: " + courseId + " does not exist."));

        if (!teacher.getCourses().contains(course)) {
            teacher.getCourses().add(course);
            teacherRepository.save(teacher);
        }
        return teacher;
    }

    @Override
    public Teacher removeCourseFromTeacher(Long teacherId, Long courseId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with id: " + teacherId + " does not exist."));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new TeacherNotFoundException("Course with id: " + courseId + " does not exist."));

        if (teacher.getCourses().contains(course)) {
            teacher.getCourses().remove(course);
            teacherRepository.save(teacher);
        }
        return teacher;
    }
}

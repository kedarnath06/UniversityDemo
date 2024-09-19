package com.Universitydemo.demo.serviceimp;

import com.Universitydemo.demo.entity.Course;
import com.Universitydemo.demo.entity.Student;
import com.Universitydemo.demo.exception.StudentNotFoundException;
import com.Universitydemo.demo.repository.CourseRepository;
import com.Universitydemo.demo.repository.StudentRepository;
import com.Universitydemo.demo.serviceinterface.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentServiceImp implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentServiceImp(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findStudentsByGuardianName(String guardianName) {
        List<Student> studentList = studentRepository.findStudentByGuardian_Name(guardianName);
        return studentList;
    }

    @Override
    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + studentId));
    }

    @Override
    public Student updateStudent(Long studentId, Student updatedStudent) {
        return studentRepository.findById(studentId).map(existingStudent -> {
            existingStudent.setFirstname(updatedStudent.getFirstname());
            existingStudent.setLastname(updatedStudent.getLastname());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setGuardian(updatedStudent.getGuardian());
            existingStudent.setCourseList(updatedStudent.getCourseList());
            return studentRepository.save(existingStudent);
        }).orElseThrow(() -> new StudentNotFoundException("Student with id: " + studentId + " not found."));
    }

    @Override
    public void deleteStudent(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
        } else {
            throw new StudentNotFoundException("Student with id: " + studentId + " not found.");
        }
    }

    @Override
    public Student addCourseToStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new StudentNotFoundException("Course not found with id: " + courseId));

        if (!student.getCourseList().contains(course)) {
            student.getCourseList().add(course);
            studentRepository.save(student);
        }
        return student;
    }

    @Override
    public Student removeCourseFromStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new StudentNotFoundException("Course not found with id: " + courseId));

        if (student.getCourseList().contains(course)) {
            student.getCourseList().remove(course);
            studentRepository.save(student);
        }
        return student;
    }


}

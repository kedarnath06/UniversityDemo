package com.Universitydemo.demo.serviceimp;

import com.Universitydemo.demo.entity.Course;
import com.Universitydemo.demo.entity.Teacher;
import com.Universitydemo.demo.exception.CourseNotFoundException;
import com.Universitydemo.demo.exception.TeacherNotFoundException;
import com.Universitydemo.demo.repository.CourseRepository;
import com.Universitydemo.demo.repository.TeacherRepository;
import com.Universitydemo.demo.serviceinterface.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImp implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Course createCourse(Course course) {
//        if (course.getTeacher() != null && course.getTeacher().getTeacherId() != null) {
//            Teacher teacher = teacherRepository.findById(course.getTeacher().getTeacherId())
//                    .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + course.getTeacher().getTeacherId()));
//            course.setTeacher(teacher);
//        }
        return courseRepository.save(course);
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            throw new CourseNotFoundException("No Courses Found.");
        }
        return courses;
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
    }

//    @Override
//    public Course findByName(String name) {
//        return courseRepository.f
//    }

    @Override
    public Course updateById(Long id) {
        Optional<Course> existingCourse = courseRepository.findById(id);
        if (existingCourse.isPresent()) {
            Course updatedCourse = existingCourse.get();
            updatedCourse.setCredit(updatedCourse.getCredit());
            updatedCourse.setTitle(updatedCourse.getTitle());
            updatedCourse.setTeacher(updatedCourse.getTeacher());
            updatedCourse.setStudents(updatedCourse.getStudents());
            return courseRepository.save(updatedCourse);
        }
        else
            throw new CourseNotFoundException("Course with this"+id+"is not found");
    }

    @Override
    // Find all courses by teacher's ID
    public List<Course> findCourseByTeacherId(Long teacherId) {
        List<Course> courses = courseRepository.findByTeacherTeacherId(teacherId);
        if (courses.isEmpty()) {
            throw new CourseNotFoundException("No courses found for teacher with id: " + teacherId);
        }
        return courses;
    }

    @Override
    public void deleteById(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new CourseNotFoundException("Course with id: " + id + " not found.");
        }
    }
}


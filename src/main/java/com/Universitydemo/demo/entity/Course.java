package com.Universitydemo.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "*course_details")
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    @Column(unique = true)
    private String title;
    private Integer credit;

//    @OneToOne(mappedBy = "course")
//    private CourseMaterial courseMaterials;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course mapped by student",
            joinColumns = @JoinColumn(name = "courseId"),
            inverseJoinColumns = @JoinColumn(name = "studentId")
    )
    private List<Student> students;

    public void addStudents(Student student) {
        if (students==null) students = new ArrayList<>();
        students.add(student);
    }

}
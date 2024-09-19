package com.Universitydemo.demo.serviceimp;

import com.Universitydemo.demo.entity.CourseMaterial;
import com.Universitydemo.demo.exception.CourseMaterialNotFoundException;
import com.Universitydemo.demo.repository.CourseMaterialRepository;
import com.Universitydemo.demo.serviceinterface.CourseMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseMaterialServiceImp implements CourseMaterialService {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Override
    public CourseMaterial saveCourseMaterial(CourseMaterial courseMaterial) {
        return courseMaterialRepository.save(courseMaterial);
    }

    @Override
    public List<CourseMaterial> findAll() {
        return courseMaterialRepository.findAll();
    }

    @Override
    public Optional<CourseMaterial> findById(Long id) {
        return courseMaterialRepository.findById(id);
    }

    @Override
    public CourseMaterial findCourseMaterialByCourseId(Long courseId) {
        return courseMaterialRepository.findByCourseCourseId(courseId)
                .orElseThrow(() -> new CourseMaterialNotFoundException("\"CourseMaterial not found for courseId: \" + courseId"));
    }

    @Override
    public CourseMaterial updateById(Long id) {
        Optional<CourseMaterial> existingCourseMaterial = courseMaterialRepository.findById(id);

        if (existingCourseMaterial.isPresent()) {
            CourseMaterial updatedCourseMaterial = existingCourseMaterial.get();
            updatedCourseMaterial.setUrl(updatedCourseMaterial.getUrl());
            updatedCourseMaterial.setCourse(updatedCourseMaterial.getCourse());
            return courseMaterialRepository.save(updatedCourseMaterial);
        } else {
            throw new CourseMaterialNotFoundException("Course Material with" + id + "does not found");
        }
    }

    @Override
    public void deleteById(Long id) {
        boolean exists = courseMaterialRepository.existsById(id);
        if (!exists) {
            throw new CourseMaterialNotFoundException("CourseMaterial with id " + id + " does not exist.");
        }
        courseMaterialRepository.deleteById(id);
    }

}

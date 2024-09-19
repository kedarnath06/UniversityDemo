package com.Universitydemo.demo.Advice;

import com.Universitydemo.demo.exception.CourseMaterialNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CourseMaterialNotFoundExceptionHandler {

    @ExceptionHandler(CourseMaterialNotFoundException.class)
    public ResponseEntity<String> handleCourseMaterialNotFoundException(CourseMaterialNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

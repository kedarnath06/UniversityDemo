package com.Universitydemo.demo.Advice;

import com.Universitydemo.demo.exception.CourseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CourseNotFoundExceptionHandler {

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> courseNotFound(CourseNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}

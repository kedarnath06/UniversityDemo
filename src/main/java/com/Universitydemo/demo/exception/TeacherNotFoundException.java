package com.Universitydemo.demo.exception;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(String message) {
        super(message);
    }
}

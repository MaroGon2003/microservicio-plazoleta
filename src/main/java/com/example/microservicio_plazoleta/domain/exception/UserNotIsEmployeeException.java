package com.example.microservicio_plazoleta.domain.exception;

public class UserNotIsEmployeeException extends RuntimeException {
    public UserNotIsEmployeeException(String message) {
        super(message);
    }
}

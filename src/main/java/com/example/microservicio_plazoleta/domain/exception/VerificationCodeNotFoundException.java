package com.example.microservicio_plazoleta.domain.exception;

public class VerificationCodeNotFoundException extends RuntimeException{
    public VerificationCodeNotFoundException(String message) {
        super(message);
    }
}

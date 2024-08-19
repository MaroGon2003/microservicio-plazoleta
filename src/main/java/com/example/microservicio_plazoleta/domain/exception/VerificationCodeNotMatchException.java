package com.example.microservicio_plazoleta.domain.exception;

public class VerificationCodeNotMatchException extends RuntimeException {

    public VerificationCodeNotMatchException(String message) {
        super(message);
    }
}

package com.example.microservicio_plazoleta.domain.exception;

public class PinIsRequiredException extends RuntimeException{
    public PinIsRequiredException(String message) {
        super(message);
    }
}

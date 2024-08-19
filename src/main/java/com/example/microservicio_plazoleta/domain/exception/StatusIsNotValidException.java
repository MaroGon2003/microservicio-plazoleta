package com.example.microservicio_plazoleta.domain.exception;

public class StatusIsNotValidException extends RuntimeException{
    public StatusIsNotValidException(String message) {
        super(message);
    }
}

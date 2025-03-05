package com.example.microservicio_plazoleta.domain.exception;

public class OrderNotReadyException extends RuntimeException{
    public OrderNotReadyException(String message) {
        super(message);
    }
}

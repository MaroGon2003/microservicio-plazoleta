package com.example.microservicio_plazoleta.domain.exception;

public class OrderNotBelongEmployeeException extends RuntimeException {
    public OrderNotBelongEmployeeException(String message) {
        super(message);
    }
}

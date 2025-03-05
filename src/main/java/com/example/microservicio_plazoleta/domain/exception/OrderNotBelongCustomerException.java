package com.example.microservicio_plazoleta.domain.exception;

public class OrderNotBelongCustomerException extends RuntimeException {
    public OrderNotBelongCustomerException(String message) {
        super(message);
    }
}

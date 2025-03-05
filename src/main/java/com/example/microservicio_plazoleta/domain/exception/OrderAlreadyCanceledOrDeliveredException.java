package com.example.microservicio_plazoleta.domain.exception;

public class OrderAlreadyCanceledOrDeliveredException extends RuntimeException {
    public OrderAlreadyCanceledOrDeliveredException(String message) {
        super(message);
    }
}

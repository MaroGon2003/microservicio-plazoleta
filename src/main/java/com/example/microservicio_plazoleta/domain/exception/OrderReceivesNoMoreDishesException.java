package com.example.microservicio_plazoleta.domain.exception;

public class OrderReceivesNoMoreDishesException extends RuntimeException {
    public OrderReceivesNoMoreDishesException(String message) {
        super(message);
    }
}

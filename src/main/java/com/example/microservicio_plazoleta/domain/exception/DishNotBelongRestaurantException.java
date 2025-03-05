package com.example.microservicio_plazoleta.domain.exception;

public class DishNotBelongRestaurantException extends RuntimeException {
    public DishNotBelongRestaurantException(String message) {
        super(message);
    }
}

package com.example.microservicio_plazoleta.domain.exception;

public class DishesRequiredException extends RuntimeException{
    public DishesRequiredException(String message) {
        super(message);
    }
}

package com.example.microservicio_plazoleta.domain.exception;

public class OrderCannotBeCanceledException extends RuntimeException{

        public OrderCannotBeCanceledException(String message) {
            super(message);
        }
}

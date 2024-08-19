package com.example.microservicio_plazoleta.domain.exception;

public class OrderAlreadyCanceledException extends RuntimeException{

        public OrderAlreadyCanceledException(String message) {
            super(message);
        }
}

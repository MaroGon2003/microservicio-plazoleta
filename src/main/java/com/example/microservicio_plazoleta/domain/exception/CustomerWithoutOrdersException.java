package com.example.microservicio_plazoleta.domain.exception;

public class CustomerWithoutOrdersException extends RuntimeException{

        public CustomerWithoutOrdersException(String message) {
            super(message);
        }
}

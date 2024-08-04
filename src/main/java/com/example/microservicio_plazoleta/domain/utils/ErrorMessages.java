package com.example.microservicio_plazoleta.domain.utils;

public class ErrorMessages {

        public static final String RESTAURANT_ALREADY_EXISTS = "Restaurant already exists";
        public static final String RESTAURANT_IS_NULL = "Restaurant is null";

        // Constructor privado para prevenir la instanciación
        private ErrorMessages() {
                throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
        }
}

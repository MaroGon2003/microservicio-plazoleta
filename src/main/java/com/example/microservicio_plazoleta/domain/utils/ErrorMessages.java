package com.example.microservicio_plazoleta.domain.utils;

public class ErrorMessages {

        public static final String RESTAURANT_ALREADY_EXISTS = "Restaurant already exists";
        public static final String RESTAURANT_IS_NULL = "Restaurant is null";
        public static final String RESTAURANT_NOT_FOUND = "Restaurant not found";
        public static final String CATEGORY_NOT_FOUND = "Category not found";

        public static final String DISH_IS_NULL = "Dish is null";
    public static final String DISH_NOT_FOUND = "Dish not found";

    // Constructor privado para prevenir la instanciación
        private ErrorMessages() {
                throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
        }
}

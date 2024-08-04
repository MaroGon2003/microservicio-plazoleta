package com.example.microservicio_plazoleta.infrastructure.configuration;

import com.example.microservicio_plazoleta.domain.exception.CategoryNotFoundException;
import com.example.microservicio_plazoleta.domain.exception.RestaurantAlreadyExistsException;
import com.example.microservicio_plazoleta.domain.exception.RestaurantNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(RestaurantAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> restaurantAlreadyExistsException(RestaurantAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Map<String,String>> restaurantNotFoundException(RestaurantNotFoundException e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String,String>> categoryNotFoundException(CategoryNotFoundException e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }

}

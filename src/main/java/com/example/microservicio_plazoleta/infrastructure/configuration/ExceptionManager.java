package com.example.microservicio_plazoleta.infrastructure.configuration;

import com.example.microservicio_plazoleta.domain.exception.*;
import com.example.microservicio_plazoleta.infrastructure.exception.OwnerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ExceptionManager {

    private static final String ERROR = "error";

    @ExceptionHandler(RestaurantAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> restaurantAlreadyExistsException(RestaurantAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Map<String,String>> restaurantNotFoundException(RestaurantNotFoundException e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String,String>> categoryNotFoundException(CategoryNotFoundException e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<Map<String,String>> ownerNotFoundException(OwnerNotFoundException e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

    @ExceptionHandler(DishNotFoundException.class)
    public ResponseEntity<Map<String,String>> dishNotFoundException(DishNotFoundException e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

    @ExceptionHandler(UserNotOwnerException.class)
    public ResponseEntity<Map<String,String>> userNotOwnerException(UserNotOwnerException e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

    @ExceptionHandler(OwnerDishUpdateException.class)
    public ResponseEntity<Map<String,String>> ownerDishUpdateException(OwnerDishUpdateException e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String,String>> orderNotFoundException(OrderNotFoundException e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

    @ExceptionHandler(OrderNotBelongCustomerException.class)
    public ResponseEntity<Map<String,String>> orderNotBelongCustomerException(OrderNotBelongCustomerException e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

    @ExceptionHandler(OrderReceivesNoMoreDishesException.class)
    public ResponseEntity<Map<String,String>> orderReceivesNoMoreDishesException(OrderReceivesNoMoreDishesException e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

    @ExceptionHandler(OrderAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> orderAlreadyExistsException(OrderAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

    @ExceptionHandler(DishNotBelongRestaurantException.class)
    public ResponseEntity<Map<String,String>> dishNotBelongRestaurantException(DishNotBelongRestaurantException e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

    @ExceptionHandler(DishesRequiredException.class)
    public ResponseEntity<Map<String,String>> dishesRequiredException(DishesRequiredException e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

}

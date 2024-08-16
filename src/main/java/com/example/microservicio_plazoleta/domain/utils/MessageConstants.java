package com.example.microservicio_plazoleta.domain.utils;

public class MessageConstants {

    public static final String RESTAURANT_ALREADY_EXISTS = "Restaurant already exists";
    public static final String RESTAURANT_IS_NULL = "Restaurant is null";
    public static final String RESTAURANT_NOT_FOUND = "Restaurant not found";
    public static final String CATEGORY_NOT_FOUND = "Category not found";
    public static final String DISH_IS_NULL = "Dish is null";
    public static final String RESTAURANT_LIST_IS_EMPTY = "Restaurant list is empty";
    public static final String DISH_NOT_FOUND = "Dish not found";
    public static final String USER_NOT_OWNER = "User is not owner";
    public static final String OWNER_DISH_UPDATE = "Owner can only update their dishes";
    public static final String DISH_LIST_IS_EMPTY = "Dish list is empty";
    public static final String ORDER_ALREADY_EXISTS = "Order already exists";
    public static final String CANCELED_STATUS = "CANCELED";
    public static final String DELIVERED_STATUS = "DELIVERED";
    public static final String PENDING_STATUS = "PENDING";
    public static final String PREPARING_STATUS = "PREPARING";
    public static final String ORDER_NOT_FOUND = "Order not found";
    public static final String ORDER_NOT_BELONG_CUSTOMER = "Order does not belong to the customer";
    public static final String ORDER_RECEIVES_NO_MORE_DISHES = "Order receives no more dishes";
    public static final String DISH_NOT_BELONG_RESTAURANT = "Dish does not belong to the restaurant";
    public static final String DISHES_REQUIRED = "Dishes are required";
    public static final String EMPLOYEE_ALREADY_CONTRACTED = "Employee already contracted";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_NOT_IS_EMPLOYEE = "User is not an employee";

    // Constructor privado para prevenir la instanciación
    private MessageConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

package com.example.microservicio_plazoleta.domain.model;

import java.time.LocalDate;

public class RestaurantEmployeeModel {

    private Long employeeId;
    private RestaurantModel restaurantModel;
    private LocalDate contractEmployeeDate;

    public RestaurantEmployeeModel() {
    }

    public RestaurantEmployeeModel(LocalDate contractEmployeeDate, Long employeeId, RestaurantModel restaurantModel) {
        this.contractEmployeeDate = contractEmployeeDate;
        this.employeeId = employeeId;
        this.restaurantModel = restaurantModel;
    }

    public LocalDate getContractEmployeeDate() {
        return contractEmployeeDate;
    }

    public void setContractEmployeeDate(LocalDate contractEmployeeDate) {
        this.contractEmployeeDate = contractEmployeeDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public RestaurantModel getRestaurantModel() {
        return restaurantModel;
    }

    public void setRestaurantModel(RestaurantModel restaurantModel) {
        this.restaurantModel = restaurantModel;
    }
}

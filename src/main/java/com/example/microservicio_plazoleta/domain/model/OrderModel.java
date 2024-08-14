package com.example.microservicio_plazoleta.domain.model;

import java.time.LocalDateTime;

public class OrderModel {

    private Long id;
    private Long customerId;
    private Long employeeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private Long restaurantId;

    public OrderModel() {
    }

    public OrderModel(Long customerId, Long employeeId, LocalDateTime endTime, Long id, Long restaurantId, LocalDateTime startTime, String status) {
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.endTime = endTime;
        this.id = id;
        this.restaurantId = restaurantId;
        this.startTime = startTime;
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

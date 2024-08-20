package com.example.microservicio_plazoleta.domain.model;

public class TraceabilityModel {

    private String  id;
    private Long orderId;
    private Long customerId;
    private String customerEmail;
    private String date;
    private String previousStatus;
    private String newStatus;
    private Long employeeId;
    private String employeeEmail;

    public TraceabilityModel() {
    }

    public TraceabilityModel(String customerEmail, Long customerId, String date, String employeeEmail, Long employeeId, String id, String previousStatus, Long orderId, String newStatus) {
        this.customerEmail = customerEmail;
        this.customerId = customerId;
        this.date = date;
        this.employeeEmail = employeeEmail;
        this.employeeId = employeeId;
        this.id = id;
        this.previousStatus = previousStatus;
        this.orderId = orderId;
        this.newStatus = newStatus;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(String previousStatus) {
        this.previousStatus = previousStatus;
    }
}


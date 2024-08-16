package com.example.microservicio_plazoleta.domain.model;

public class TraceabilityModel {

    private String  id;
    private Long orderId;
    private Long customerId;
    private String customerEmail;
    private String previousStatus;
    private String newStatus;
    private Long employeeId;
    private String employeeEmail;

    public TraceabilityModel() {
    }

    public TraceabilityModel(String customerEmail, Long customerId, String employeeEmail, Long employeeId, String  id, Long orderId, String previousStatus, String newStatus) {
        this.customerEmail = customerEmail;
        this.customerId = customerId;
        this.employeeEmail = employeeEmail;
        this.employeeId = employeeId;
        this.id = id;
        this.orderId = orderId;
        this.previousStatus = previousStatus;
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

    public String  getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(String previousStatus) {
        this.previousStatus = previousStatus;
    }
}


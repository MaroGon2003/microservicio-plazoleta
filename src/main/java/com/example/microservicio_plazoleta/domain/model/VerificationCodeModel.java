package com.example.microservicio_plazoleta.domain.model;

public class VerificationCodeModel {

    private Long id;

    int pin;

    private Long idOrder;

    public VerificationCodeModel() {
    }

    public VerificationCodeModel(Long id, Long idOrder, int pin) {
        this.id = id;
        this.idOrder = idOrder;
        this.pin = pin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}

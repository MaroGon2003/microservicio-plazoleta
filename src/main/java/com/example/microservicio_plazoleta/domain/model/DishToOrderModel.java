package com.example.microservicio_plazoleta.domain.model;

public class DishToOrderModel {

    private Long orderModelId;
    private Long dishModelId;
    private int amount;

    public DishToOrderModel() {
    }

    public DishToOrderModel(int amount, Long dishModelId, Long orderModelId) {
        this.amount = amount;
        this.dishModelId = dishModelId;
        this.orderModelId = orderModelId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getDishModelId() {
        return dishModelId;
    }

    public void setDishModelId(Long dishModelId) {
        this.dishModelId = dishModelId;
    }

    public Long getOrderModelId() {
        return orderModelId;
    }

    public void setOrderModelId(Long orderModelId) {
        this.orderModelId = orderModelId;
    }
}

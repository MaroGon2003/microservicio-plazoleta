package com.example.microservicio_plazoleta.domain.model;

public class DishToOrderModel {

    private OrderModel orderModel;
    private DishModel dishModel;
    private int amount;

    public DishToOrderModel() {
    }

    public DishToOrderModel(int amount, DishModel dishModel, OrderModel orderModel) {
        this.amount = amount;
        this.dishModel = dishModel;
        this.orderModel = orderModel;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public DishModel getDishModel() {
        return dishModel;
    }

    public void setDishModel(DishModel dishModel) {
        this.dishModel = dishModel;
    }

    public OrderModel getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }
}

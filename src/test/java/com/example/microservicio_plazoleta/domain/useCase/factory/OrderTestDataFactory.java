package com.example.microservicio_plazoleta.domain.useCase.factory;

import com.example.microservicio_plazoleta.domain.model.DishToOrderModel;
import com.example.microservicio_plazoleta.domain.model.OrderModel;

import java.time.LocalDateTime;
import java.util.List;

public class OrderTestDataFactory {

    public static OrderModel getOrderWithSetters() {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(1L);
        orderModel.setCustomerId(1L);
        orderModel.setEmployeeId(null);
        orderModel.setStartTime(LocalDateTime.now());
        orderModel.setEndTime(null);
        orderModel.setStatus("PENDING");
        orderModel.setRestaurantModel(RestaurantTestDataFactory.getRestaurantWithSetters());

        return orderModel;
    }

    public static OrderModel getOrderCanceledWithSetters() {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(1L);
        orderModel.setCustomerId(1L);
        orderModel.setEmployeeId(null);
        orderModel.setStartTime(LocalDateTime.now());
        orderModel.setEndTime(null);
        orderModel.setStatus("CANCELED");
        orderModel.setRestaurantModel(RestaurantTestDataFactory.getRestaurantWithSetters());

        return orderModel;
    }



    public static DishToOrderModel getDishToOrderWithSetters() {
        DishToOrderModel dishToOrderModel = new DishToOrderModel();
        dishToOrderModel.setAmount(1);
        dishToOrderModel.setDishModel(DishTestDataFactory.getDishWithSetters());
        dishToOrderModel.setOrderModel(OrderTestDataFactory.getOrderWithSetters());

        return dishToOrderModel;
    }

    public static List<DishToOrderModel> getDishToOrderListWithSetters() {
        return List.of(getDishToOrderWithSetters());
    }

    public static List<DishToOrderModel> getDishToOrderListWithSettersCanceled() {

        DishToOrderModel dishToOrderModel = new DishToOrderModel();
        dishToOrderModel.setAmount(1);
        dishToOrderModel.setDishModel(DishTestDataFactory.getDishWithSetters());
        dishToOrderModel.setOrderModel(OrderTestDataFactory.getOrderCanceledWithSetters());

        return List.of(dishToOrderModel);

    }

    public static List<OrderModel> getOrderListWithSettersCanceled() {
        return List.of(getOrderCanceledWithSetters());
    }

}

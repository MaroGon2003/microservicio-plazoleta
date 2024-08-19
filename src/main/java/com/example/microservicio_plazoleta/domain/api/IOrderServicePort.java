package com.example.microservicio_plazoleta.domain.api;

import com.example.microservicio_plazoleta.domain.model.DishToOrderModel;

import java.util.List;

public interface IOrderServicePort {

    void saveOrder(Long restaurantId, List<DishToOrderModel> dishes);

    List<DishToOrderModel> getAllOrdersByStatus(int page, int size,Long restaurantId, String status);

    void assignEmployee(Long id);

    void readyOrder(Long id);

    void deliveryOrder(Long id, int pin);

}

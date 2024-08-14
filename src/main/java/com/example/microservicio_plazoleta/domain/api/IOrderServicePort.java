package com.example.microservicio_plazoleta.domain.api;

import com.example.microservicio_plazoleta.domain.model.DishToOrderModel;

import java.util.List;

public interface IOrderServicePort {

    void saveOrder(Long restaurantId, List<DishToOrderModel> dishes);

}

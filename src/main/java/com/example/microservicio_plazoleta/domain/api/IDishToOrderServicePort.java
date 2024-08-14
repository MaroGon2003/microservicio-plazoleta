package com.example.microservicio_plazoleta.domain.api;

import com.example.microservicio_plazoleta.domain.model.DishToOrderModel;

public interface IDishToOrderServicePort {

    void addDishToOrder(DishToOrderModel dishToOrderModel);

}

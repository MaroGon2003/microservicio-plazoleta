package com.example.microservicio_plazoleta.application.handler;

import com.example.microservicio_plazoleta.application.dto.request.DishToOrderRequestDto;

public interface IDishToOrderHandler {

    void addDishToOrder(DishToOrderRequestDto dishToOrderRequestDto);

}

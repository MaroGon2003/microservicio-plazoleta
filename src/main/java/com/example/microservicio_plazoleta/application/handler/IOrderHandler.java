package com.example.microservicio_plazoleta.application.handler;

import com.example.microservicio_plazoleta.application.dto.request.OrderRequestDto;

public interface IOrderHandler {

    void saveOrder(OrderRequestDto orderRequestDto);

}

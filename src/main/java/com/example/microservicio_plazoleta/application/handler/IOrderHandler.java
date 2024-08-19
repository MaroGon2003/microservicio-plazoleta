package com.example.microservicio_plazoleta.application.handler;

import com.example.microservicio_plazoleta.application.dto.request.OrderRequestDto;
import com.example.microservicio_plazoleta.application.dto.response.OrderResponseDto;

import java.util.List;

public interface IOrderHandler {

    void saveOrder(OrderRequestDto orderRequestDto);

    List<OrderResponseDto> getAllOrdersByStatus(int page, int size,Long restaurantId, String status);

    void assignEmployee(Long id);

    void changeStatus(Long id, String status);

    void delyveryOrder(Long id, int pin);

}

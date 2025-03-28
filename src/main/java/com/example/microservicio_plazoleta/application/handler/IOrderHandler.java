package com.example.microservicio_plazoleta.application.handler;

import com.example.microservicio_plazoleta.application.dto.request.OrderRequestDto;
import com.example.microservicio_plazoleta.application.dto.response.OrderRankingDto;
import com.example.microservicio_plazoleta.application.dto.response.OrderResponseDto;
import com.example.microservicio_plazoleta.application.dto.response.OrderTimeResponseDto;

import java.util.List;

public interface IOrderHandler {

    void saveOrder(OrderRequestDto orderRequestDto);

    List<OrderResponseDto> getAllOrdersByStatus(int page, int size,Long restaurantId, String status);

    void assignEmployee(Long id);

    void readyOrder(Long id);

    void deliveryOrder(Long id, int pin);

    void cancelOrder(Long id);

    List<OrderTimeResponseDto> showOrderPeriod();

    List<OrderRankingDto> showOrderRanking();

}

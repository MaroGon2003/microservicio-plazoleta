package com.example.microservicio_plazoleta.application.handler.impl;

import com.example.microservicio_plazoleta.application.dto.request.OrderRequestDto;
import com.example.microservicio_plazoleta.application.handler.IOrderHandler;
import com.example.microservicio_plazoleta.application.mapper.IDishToOrderRequestMapper;
import com.example.microservicio_plazoleta.domain.api.IOrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IDishToOrderRequestMapper dishToOrderRequestMapper;

    @Override
    public void saveOrder(OrderRequestDto orderRequestDto) {
        orderServicePort.saveOrder(orderRequestDto.getRestaurantId(), dishToOrderRequestMapper.toDishToOrderModelList(orderRequestDto.getDishes()));
    }
}

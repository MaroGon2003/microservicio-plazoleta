package com.example.microservicio_plazoleta.application.handler.impl;

import com.example.microservicio_plazoleta.application.dto.request.DishToOrderRequestDto;
import com.example.microservicio_plazoleta.application.handler.IDishToOrderHandler;
import com.example.microservicio_plazoleta.application.mapper.IDishToOrderRequestMapper;
import com.example.microservicio_plazoleta.domain.api.IDishToOrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishToOrderHandler implements IDishToOrderHandler {

    private final IDishToOrderServicePort dishToOrderServicePort;
    private final IDishToOrderRequestMapper dishToOrderRequestMapper;

    @Override
    public void addDishToOrder(DishToOrderRequestDto dishToOrderRequestDto) {
        dishToOrderServicePort.addDishToOrder(dishToOrderRequestMapper.toDishToOrderModel(dishToOrderRequestDto));
    }
}

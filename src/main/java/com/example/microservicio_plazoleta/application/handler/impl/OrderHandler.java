package com.example.microservicio_plazoleta.application.handler.impl;

import com.example.microservicio_plazoleta.application.handler.IOrderHandler;
import com.example.microservicio_plazoleta.domain.api.IOrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;

    @Override
    public void saveOrder(Long restaurantId) {
        orderServicePort.saveOrder(restaurantId);
    }
}

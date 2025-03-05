package com.example.microservicio_plazoleta.application.handler.impl;

import com.example.microservicio_plazoleta.application.dto.request.RestaurantEmployeeRequestDto;
import com.example.microservicio_plazoleta.application.handler.IRestaurantEmployeeHandler;
import com.example.microservicio_plazoleta.application.mapper.IRestaurantEmployeeRequestMapper;
import com.example.microservicio_plazoleta.domain.api.IRestaurandEnployeeServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantEmployeeHandler implements IRestaurantEmployeeHandler {

    private final IRestaurandEnployeeServicePort restaurandEnployeeServicePort;
    private final IRestaurantEmployeeRequestMapper restaurantEmployeeRequestMapper;

    @Override
    public void contractEmployee(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto) {
        restaurandEnployeeServicePort.contractEmployee(restaurantEmployeeRequestMapper.toModel(restaurantEmployeeRequestDto));
    }
}

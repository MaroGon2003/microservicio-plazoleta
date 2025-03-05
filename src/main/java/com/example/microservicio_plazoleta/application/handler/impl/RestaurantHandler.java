package com.example.microservicio_plazoleta.application.handler.impl;

import com.example.microservicio_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.microservicio_plazoleta.application.dto.response.RestaurantResponseDto;
import com.example.microservicio_plazoleta.application.handler.IRestaurantHandler;
import com.example.microservicio_plazoleta.application.mapper.IRestaurantRequestMapper;
import com.example.microservicio_plazoleta.application.mapper.IRestaurantResponseMapper;
import com.example.microservicio_plazoleta.domain.api.IRestaurantServicePort;
import com.example.microservicio_plazoleta.domain.model.RestaurantModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        RestaurantModel restaurant = restaurantRequestMapper.toRestaurantModel(restaurantRequestDto);
        restaurantServicePort.saveRestaurant(restaurant);
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurants(int pageNumber, int pageSize) {
        return restaurantResponseMapper.toRestaurantResponseDto(restaurantServicePort.getAllRestaurants(pageNumber, pageSize));
    }
}

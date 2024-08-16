package com.example.microservicio_plazoleta.application.handler;

import com.example.microservicio_plazoleta.application.dto.request.RestaurantEmployeeRequestDto;

public interface IRestaurantEmployeeHandler {

    void contractEmployee(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto);

}

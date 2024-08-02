package com.example.microservicio_plazoleta.domain.api;

import com.example.microservicio_plazoleta.domain.model.RestaurantModel;

public interface IRestaurantServicePort {

    void saveRestaurant(RestaurantModel restaurant);

}

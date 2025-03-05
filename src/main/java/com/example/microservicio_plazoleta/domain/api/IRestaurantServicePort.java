package com.example.microservicio_plazoleta.domain.api;

import com.example.microservicio_plazoleta.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantServicePort {

    void saveRestaurant(RestaurantModel restaurant);

    List<RestaurantModel> getAllRestaurants(int pageNumber, int pageSize);

}

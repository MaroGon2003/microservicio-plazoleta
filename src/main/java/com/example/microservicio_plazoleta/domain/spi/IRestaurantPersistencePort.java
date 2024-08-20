package com.example.microservicio_plazoleta.domain.spi;

import com.example.microservicio_plazoleta.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantPersistencePort {

    void saveRestaurant(RestaurantModel restaurant);

    boolean existsRestaurantByNit(String nit);

    List<RestaurantModel> getAllRestaurants(int pageNumber, int pageSize);

    RestaurantModel getRestaurantById(Long id);

    RestaurantModel getRestaurantByOwnerId(Long ownerId);

}

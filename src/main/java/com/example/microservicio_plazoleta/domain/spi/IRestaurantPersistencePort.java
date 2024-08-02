package com.example.microservicio_plazoleta.domain.spi;

import com.example.microservicio_plazoleta.domain.model.RestaurantModel;

public interface IRestaurantPersistencePort {

    void saveRestaurant(RestaurantModel restaurant);

    boolean existsRestaurantByNit(String nit);

}

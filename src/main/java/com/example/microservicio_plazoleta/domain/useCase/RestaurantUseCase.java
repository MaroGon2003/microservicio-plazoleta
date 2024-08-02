package com.example.microservicio_plazoleta.domain.useCase;

import com.example.microservicio_plazoleta.domain.api.IRestaurantServicePort;
import com.example.microservicio_plazoleta.domain.exception.ErrorMessages;
import com.example.microservicio_plazoleta.domain.exception.RestaurantAlreadyExistsException;
import com.example.microservicio_plazoleta.domain.model.RestaurantModel;
import com.example.microservicio_plazoleta.domain.spi.IRestaurantPersistencePort;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }


    @Override
    public void saveRestaurant(RestaurantModel restaurant) {

        if(restaurant == null) {
            throw new NullPointerException(ErrorMessages.RESTAURANT_IS_NULL);
        }

        if(restaurantPersistencePort.existsRestaurantByNit(restaurant.getNit())){
            throw new RestaurantAlreadyExistsException(ErrorMessages.RESTAURANT_ALREADY_EXISTS);
        }

        //Faltan validaciones

        restaurantPersistencePort.saveRestaurant(restaurant);

    }
}

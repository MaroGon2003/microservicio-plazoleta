package com.example.microservicio_plazoleta.domain.useCase;

import com.example.microservicio_plazoleta.domain.api.IRestaurantServicePort;
import com.example.microservicio_plazoleta.domain.api.IUserFeignServicePort;
import com.example.microservicio_plazoleta.domain.exception.UserNotOwnerException;
import com.example.microservicio_plazoleta.domain.utils.ErrorMessages;
import com.example.microservicio_plazoleta.domain.exception.RestaurantAlreadyExistsException;
import com.example.microservicio_plazoleta.domain.model.RestaurantModel;
import com.example.microservicio_plazoleta.domain.spi.IRestaurantPersistencePort;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserFeignServicePort userFeignServicePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, IUserFeignServicePort userFeignServicePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userFeignServicePort = userFeignServicePort;
    }


    @Override
    public void saveRestaurant(RestaurantModel restaurant) {

        if(restaurant == null) {
            throw new NullPointerException(ErrorMessages.RESTAURANT_IS_NULL);
        }

        if(restaurantPersistencePort.existsRestaurantByNit(restaurant.getNit())){
            throw new RestaurantAlreadyExistsException(ErrorMessages.RESTAURANT_ALREADY_EXISTS);
        }

        if(!userFeignServicePort.validateOwner(restaurant.getIdOwner())){
            throw new UserNotOwnerException(ErrorMessages.USER_NOT_OWNER);
        }

        restaurantPersistencePort.saveRestaurant(restaurant);

    }
}

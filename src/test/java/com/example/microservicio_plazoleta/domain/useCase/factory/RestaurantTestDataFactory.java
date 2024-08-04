package com.example.microservicio_plazoleta.domain.useCase.factory;

import com.example.microservicio_plazoleta.domain.model.RestaurantModel;

public class RestaurantTestDataFactory {

    public static RestaurantModel getRestaurantWithSetters() {
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(1L);
        restaurantModel.setName("McDonalds");
        restaurantModel.setAddress("Calle 1 # 1-1");
        restaurantModel.setIdOwner(1L);
        restaurantModel.setPhone("+571234567890");
        restaurantModel.setLogoUrl("https://www.mcdonalds.com");
        restaurantModel.setNit("1234567890");

        return restaurantModel;
    }

}

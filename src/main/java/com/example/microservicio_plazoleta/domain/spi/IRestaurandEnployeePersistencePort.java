package com.example.microservicio_plazoleta.domain.spi;

import com.example.microservicio_plazoleta.domain.model.RestaurantEmployeeModel;

public interface IRestaurandEnployeePersistencePort {

    void contractEmployee(RestaurantEmployeeModel restaurantEmployeeModel);

    boolean isEmployeeContracted(Long employeeId);

    boolean existRestaurant(Long restaurantId);

    String getAuthenticatedRole();

}

package com.example.microservicio_plazoleta.domain.useCase;

import com.example.microservicio_plazoleta.domain.api.IRestaurandEnployeeServicePort;
import com.example.microservicio_plazoleta.domain.api.IUserFeignServicePort;
import com.example.microservicio_plazoleta.domain.exception.EmployeeAlreadyContractedException;
import com.example.microservicio_plazoleta.domain.exception.RestaurantNotFoundException;
import com.example.microservicio_plazoleta.domain.exception.UserNotFoundException;
import com.example.microservicio_plazoleta.domain.exception.UserNotIsEmployeeException;
import com.example.microservicio_plazoleta.domain.model.RestaurantEmployeeModel;
import com.example.microservicio_plazoleta.domain.model.UserModel;
import com.example.microservicio_plazoleta.domain.spi.IRestaurandEnployeePersistencePort;
import com.example.microservicio_plazoleta.domain.utils.MessageConstants;

import java.time.LocalDate;

public class RestaurantEmployeeUseCase implements IRestaurandEnployeeServicePort {

    private final IRestaurandEnployeePersistencePort restauranEmployeePersistencePort;
    private final IUserFeignServicePort userFeignServicePort;

    public RestaurantEmployeeUseCase(IRestaurandEnployeePersistencePort restauranEmployeePersistencePort, IUserFeignServicePort userFeignServicePort) {
        this.restauranEmployeePersistencePort = restauranEmployeePersistencePort;
        this.userFeignServicePort = userFeignServicePort;
    }


    @Override
    public void contractEmployee(RestaurantEmployeeModel restaurantEmployeeModel) {

        if (restaurantEmployeeModel == null) {
                throw new NullPointerException();
        }

        Long employeeId = restaurantEmployeeModel.getEmployeeId();
        UserModel userModel = userFeignServicePort.getUserById(employeeId);
        if (userModel == null) {
            throw new UserNotFoundException(MessageConstants.USER_NOT_FOUND);
        }
        if (!userModel.getRolId().equals(3L)) {
            throw new UserNotIsEmployeeException(MessageConstants.USER_NOT_IS_EMPLOYEE);
        }

        if(restauranEmployeePersistencePort.isEmployeeContracted(employeeId)){
            throw new EmployeeAlreadyContractedException(MessageConstants.EMPLOYEE_ALREADY_CONTRACTED);
        }

        Long restaurantId = restaurantEmployeeModel.getRestaurantModel().getId();

        if(!restauranEmployeePersistencePort.existRestaurant(restaurantId)){
            throw new RestaurantNotFoundException(MessageConstants.RESTAURANT_NOT_FOUND);
        }

        restaurantEmployeeModel.setContractEmployeeDate(LocalDate.now());

        restauranEmployeePersistencePort.contractEmployee(restaurantEmployeeModel);

    }
}

package com.example.microservicio_plazoleta.domain.useCase;

import com.example.microservicio_plazoleta.domain.api.IUserFeignServicePort;
import com.example.microservicio_plazoleta.domain.exception.RestaurantAlreadyExistsException;
import com.example.microservicio_plazoleta.domain.exception.UserNotOwnerException;
import com.example.microservicio_plazoleta.domain.model.RestaurantModel;
import com.example.microservicio_plazoleta.domain.spi.IRestaurantPersistencePort;
import com.example.microservicio_plazoleta.domain.useCase.factory.RestaurantTestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantUseCaseTest {

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IUserFeignServicePort userFeignServicePort;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    @Test
    void shoulTrowRestaurantIsNull(){

        assertThrows(NullPointerException.class, () -> restaurantUseCase.saveRestaurant(null));

    }

    @Test
    void shouldThrowrRestaurantAlreadyExists(){

        RestaurantModel restaurantModel = RestaurantTestDataFactory.getRestaurantWithSetters();

        when(restaurantPersistencePort.existsRestaurantByNit(any(String.class))).thenReturn(true);

        assertThrows(RestaurantAlreadyExistsException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));

    }

    @Test
    void shouldThrowUserNotOwner(){

        RestaurantModel restaurantModel = RestaurantTestDataFactory.getRestaurantWithSetters();

        when(restaurantPersistencePort.existsRestaurantByNit(any(String.class))).thenReturn(false);
        when(userFeignServicePort.validateOwner(any(Long.class))).thenReturn(false);

        assertThrows(UserNotOwnerException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));

    }

    @Test
    void shouldSaveRestaurant(){

        RestaurantModel restaurantModel = RestaurantTestDataFactory.getRestaurantWithSetters();

        when(restaurantPersistencePort.existsRestaurantByNit(any(String.class))).thenReturn(false);
        when(userFeignServicePort.validateOwner(any(Long.class))).thenReturn(true);
        restaurantUseCase.saveRestaurant(restaurantModel);

        verify(restaurantPersistencePort).saveRestaurant(restaurantModel);

    }

}

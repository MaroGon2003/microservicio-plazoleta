package com.example.microservicio_plazoleta.domain.useCase;

import com.example.microservicio_plazoleta.domain.exception.RestaurantNotFoundException;
import com.example.microservicio_plazoleta.domain.model.RestaurantModel;
import com.example.microservicio_plazoleta.domain.spi.IOrderPersistencePort;
import com.example.microservicio_plazoleta.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderUseCaseTest {

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IOrderPersistencePort orderPersistencePort;

    @InjectMocks
    private OrderUseCase orderUseCase;


    @Test
    void shouldThrowNullPointerExceptionWhenRestaurantIdIsNull() {
        assertThrows(NullPointerException.class, () -> orderUseCase.saveOrder(null));
    }

    @Test
    void shouldThrowRestaurantNotFoundExceptionWhenRestaurantDoesNotExist() {
        when(restaurantPersistencePort.getRestaurantById(1L)).thenReturn(null);
        assertThrows(RestaurantNotFoundException.class, () -> orderUseCase.saveOrder(1L));
    }

    @Test
    void shouldThrowNullPointerExceptionWhenCostumerIdIsNull() {
        when(restaurantPersistencePort.getRestaurantById(1L)).thenReturn(new RestaurantModel());
        when(orderPersistencePort.getAuthenticatedUserId()).thenReturn(null);

        assertThrows(NullPointerException.class, () -> orderUseCase.saveOrder(1L));
    }

}

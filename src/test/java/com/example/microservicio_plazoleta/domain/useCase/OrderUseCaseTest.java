package com.example.microservicio_plazoleta.domain.useCase;


import com.example.microservicio_plazoleta.domain.exception.RestaurantNotFoundException;

import com.example.microservicio_plazoleta.domain.spi.IDishPersistencePort;
import com.example.microservicio_plazoleta.domain.spi.IDishToOrderPersistencePort;
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

    @Mock
    private IDishToOrderPersistencePort dishToOrderPersistencePort;

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @InjectMocks
    private OrderUseCase orderUseCase;

    @Test
    void saveOrderRestaurantNotFound() {
        Long restaurantId = 1L;
        when(restaurantPersistencePort.getRestaurantById(restaurantId)).thenReturn(null);

        assertThrows(RestaurantNotFoundException.class, () -> orderUseCase.saveOrder(restaurantId, null));
    }



}

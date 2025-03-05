package com.example.microservicio_plazoleta.domain.useCase;


import com.example.microservicio_plazoleta.domain.api.ITraceabilityFeignClientPort;
import com.example.microservicio_plazoleta.domain.api.ITwilioFeignClientPort;
import com.example.microservicio_plazoleta.domain.api.IUserFeignServicePort;
import com.example.microservicio_plazoleta.domain.api.IVerificationCodePort;
import com.example.microservicio_plazoleta.domain.exception.*;

import com.example.microservicio_plazoleta.domain.model.DishToOrderModel;
import com.example.microservicio_plazoleta.domain.model.OrderModel;
import com.example.microservicio_plazoleta.domain.spi.*;
import com.example.microservicio_plazoleta.domain.useCase.factory.DishTestDataFactory;
import com.example.microservicio_plazoleta.domain.useCase.factory.OrderTestDataFactory;
import com.example.microservicio_plazoleta.domain.useCase.factory.RestaurantTestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @Mock
    private IRestaurandEnployeePersistencePort restauranEmployeePersistencePort;

    @Mock
    private IUserFeignServicePort userFeignServicePort;

    @Mock
    private ITraceabilityFeignClientPort traceabilityFeignClientPort;

    @Mock
    private ITwilioFeignClientPort twilioFeignClientPort;

    @Mock
    private IVerificationCodePort verificationCodePort;

    @Mock
    private IVerificationCodePersistencePort verificationCodePersistencePort;

    @InjectMocks
    private OrderUseCase orderUseCase;

    //saveOrder()
    @Test
    void saveOrderRestaurantNotFound() {
        Long restaurantId = 1L;
        when(restaurantPersistencePort.getRestaurantById(restaurantId)).thenReturn(null);

        assertThrows(RestaurantNotFoundException.class, () -> orderUseCase.saveOrder(restaurantId, null));
    }

    @Test
    void shouldTrowDishesRequiredException() {
        Long restaurantId = 1L;
        List<DishToOrderModel> dishes = List.of();
        when(restaurantPersistencePort.getRestaurantById(restaurantId)).thenReturn(RestaurantTestDataFactory.getRestaurantWithSetters());

        assertThrows(DishesRequiredException.class, () -> orderUseCase.saveOrder(restaurantId, dishes));
    }

    @Test
    void shouldTrhowNullPointerException() {
        Long restaurantId = 1L;
        List<DishToOrderModel> dishes = OrderTestDataFactory.getDishToOrderListWithSetters();
        when(restaurantPersistencePort.getRestaurantById(restaurantId)).thenReturn(RestaurantTestDataFactory.getRestaurantWithSetters());
        when(orderPersistencePort.getAuthenticatedUserId()).thenReturn(null);

        assertThrows(NullPointerException.class, () -> orderUseCase.saveOrder(restaurantId, dishes));
    }

    @Test
    void shouldTrhowOrderNotBelongCustomerException() {
        Long restaurantId = 1L;
        List<DishToOrderModel> dishes = OrderTestDataFactory.getDishToOrderListWithSetters();
        List<OrderModel> order = List.of();
        when(restaurantPersistencePort.getRestaurantById(restaurantId)).thenReturn(RestaurantTestDataFactory.getRestaurantWithSetters());
        when(orderPersistencePort.getAuthenticatedUserId()).thenReturn(1L);
        when(orderPersistencePort.findOrderByCustomerIdAndRestaurantId(1L, restaurantId)).thenReturn(order);

        assertThrows(OrderNotBelongCustomerException.class, () -> orderUseCase.saveOrder(restaurantId, dishes));
    }

    @Test
    void shouldTrhowOrderAlreadyExistsException() {
        Long restaurantId = 1L;
        List<DishToOrderModel> dishes = OrderTestDataFactory.getDishToOrderListWithSetters();
        List<OrderModel> order = List.of(OrderTestDataFactory.getOrderWithSetters());
        when(restaurantPersistencePort.getRestaurantById(restaurantId)).thenReturn(RestaurantTestDataFactory.getRestaurantWithSetters());
        when(orderPersistencePort.getAuthenticatedUserId()).thenReturn(1L);
        when(orderPersistencePort.findOrderByCustomerIdAndRestaurantId(1L, restaurantId)).thenReturn(order);

        assertThrows(OrderAlreadyExistsException.class, () -> orderUseCase.saveOrder(restaurantId, dishes));
    }

    @Test
    void shouldTrhowOrderReceivesNoMoreDishesException() {
        Long restaurantId = 1L;
        List<DishToOrderModel> dishes = OrderTestDataFactory.getDishToOrderListWithSetters();
        List<OrderModel> order = List.of();
        when(restaurantPersistencePort.getRestaurantById(restaurantId)).thenReturn(RestaurantTestDataFactory.getRestaurantWithSetters());
        when(orderPersistencePort.getAuthenticatedUserId()).thenReturn(1L);
        when(orderPersistencePort.findOrderByCustomerIdAndRestaurantId(1L, restaurantId)).thenReturn(order);
        when(dishToOrderPersistencePort.existsOrderByOrderIdAndCustomerId(anyLong(), anyLong())).thenReturn(true);
        when(dishToOrderPersistencePort.existsOrderByOrderIdAndCustomerIdAndStatus(anyLong(), anyLong())).thenReturn(false);

        assertThrows(OrderReceivesNoMoreDishesException.class, () -> orderUseCase.saveOrder(restaurantId, dishes));
    }

    @Test
    void shouldSaveOrder() {
        Long restaurantId = 1L;
        List<DishToOrderModel> dishes = OrderTestDataFactory.getDishToOrderListWithSetters();
        List<OrderModel> order = List.of();
        Long customerId = 1L;
        when(restaurantPersistencePort.getRestaurantById(restaurantId)).thenReturn(RestaurantTestDataFactory.getRestaurantWithSetters());
        when(orderPersistencePort.getAuthenticatedUserId()).thenReturn(customerId);
        when(orderPersistencePort.findOrderByCustomerIdAndRestaurantId(customerId, restaurantId)).thenReturn(order);
        when(dishToOrderPersistencePort.existsOrderByOrderIdAndCustomerId(anyLong(), anyLong())).thenReturn(true);
        when(dishToOrderPersistencePort.existsOrderByOrderIdAndCustomerIdAndStatus(anyLong(), anyLong())).thenReturn(true);
        when(dishPersistencePort.getDishById(anyLong())).thenReturn(DishTestDataFactory.getDishWithSetters());
        when(dishPersistencePort.getDishByRestaurantIdAndDishId(anyLong(), anyLong())).thenReturn(DishTestDataFactory.getDishWithSetters());

        assertDoesNotThrow(() -> orderUseCase.saveOrder(restaurantId, dishes));

    }



}

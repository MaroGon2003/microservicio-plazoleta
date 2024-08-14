package com.example.microservicio_plazoleta.domain.useCase;

import com.example.microservicio_plazoleta.domain.api.IDishToOrderServicePort;
import com.example.microservicio_plazoleta.domain.exception.*;
import com.example.microservicio_plazoleta.domain.model.DishModel;
import com.example.microservicio_plazoleta.domain.model.DishToOrderModel;
import com.example.microservicio_plazoleta.domain.model.OrderModel;
import com.example.microservicio_plazoleta.domain.spi.IDishPersistencePort;
import com.example.microservicio_plazoleta.domain.spi.IDishToOrderPersistencePort;
import com.example.microservicio_plazoleta.domain.spi.IRestaurantPersistencePort;
import com.example.microservicio_plazoleta.domain.utils.MessageConstants;

import java.util.Objects;

public class DishToOrderUseCase implements IDishToOrderServicePort {

    private final IDishToOrderPersistencePort dishToOrderPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public DishToOrderUseCase(IDishToOrderPersistencePort dishToOrderPersistencePort, IDishPersistencePort dishPersistencePort, IRestaurantPersistencePort restaurantPersistencePort) {
        this.dishToOrderPersistencePort = dishToOrderPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void addDishToOrder(DishToOrderModel dishToOrderModel) {

        if (dishToOrderModel == null) {
            throw new NullPointerException("DishToOrderModel is null");
        }

        Long orderId = dishToOrderModel.getOrderModelId();
        if(!dishToOrderPersistencePort.existsOrderById(orderId)) {
            throw new OrderNotFoundException(MessageConstants.ORDER_NOT_FOUND);
        }

        Long userId = dishToOrderPersistencePort.getAuthenticatedUserId();
        if(!dishToOrderPersistencePort.existsOrderByOrderIdAndCustomerId(orderId, userId)) {
            throw new OrderNotBelongCustomerException(MessageConstants.ORDER_NOT_BELONG_CUSTOMER);
        }
        if (!dishToOrderPersistencePort.existsOrderByOrderIdAndCustomerIdAndStatus(orderId, userId)) {
            throw new OrderReceivesNoMoreDishesException(MessageConstants.ORDER_RECEIVES_NO_MORE_DISHES);
        }

        Long dishId = dishToOrderModel.getDishModelId();
        DishModel dish = dishPersistencePort.getDishById(dishId);
        if(dish == null) {
            throw new DishNotFoundException(MessageConstants.DISH_NOT_FOUND);
        }

        OrderModel order = dishToOrderPersistencePort.getOrderById(orderId);

        Long restaurantId = order.getRestaurantId();
        if(restaurantPersistencePort.getRestaurantById(restaurantId) == null) {
            throw new RestaurantNotFoundException(MessageConstants.RESTAURANT_NOT_FOUND);
        }
        if (!Objects.equals(dish.getRestaurantId(), restaurantId)) {
            throw new DishNotBelongRestaurantException(MessageConstants.DISH_NOT_BELONG_RESTAURANT);
        }

                dishToOrderPersistencePort.addDishToOrder(dishToOrderModel);

    }
}

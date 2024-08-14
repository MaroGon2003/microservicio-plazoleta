package com.example.microservicio_plazoleta.domain.useCase;

import com.example.microservicio_plazoleta.domain.api.IOrderServicePort;
import com.example.microservicio_plazoleta.domain.exception.OrderAlreadyExistsException;
import com.example.microservicio_plazoleta.domain.exception.RestaurantNotFoundException;
import com.example.microservicio_plazoleta.domain.model.OrderModel;
import com.example.microservicio_plazoleta.domain.spi.IOrderPersistencePort;
import com.example.microservicio_plazoleta.domain.spi.IRestaurantPersistencePort;
import com.example.microservicio_plazoleta.domain.utils.MessageConstants;

import java.time.LocalDateTime;
import java.util.List;

public class OrderUseCase implements IOrderServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IOrderPersistencePort orderPersistencePort;

    public OrderUseCase(IRestaurantPersistencePort restaurantPersistencePort, IOrderPersistencePort orderPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public void saveOrder(Long restaurantId) {

        if (restaurantId == null) {
            throw new NullPointerException("restaurantId is required");
        }

        if (restaurantPersistencePort.getRestaurantById(restaurantId) == null) {
            throw new RestaurantNotFoundException(MessageConstants.RESTAURANT_NOT_FOUND);
        }

        Long costumerId = orderPersistencePort.getAuthenticatedUserId();

        if (costumerId == null) {
            throw new NullPointerException("costumerId is required");
        }

        List<OrderModel> orders = orderPersistencePort.findOrderByCustomerIdAndRestaurantId(costumerId, restaurantId);

        if (!orders.isEmpty()) {
            for (OrderModel order :
                    orders) {
                if (!(order.getStatus().equalsIgnoreCase(MessageConstants.CANCELED_STATUS) ||
                        order.getStatus().equalsIgnoreCase(MessageConstants.DELIVERED_STATUS))) {
                    throw new OrderAlreadyExistsException(MessageConstants.ORDER_ALREADY_EXISTS);
                }
            }
        }

        OrderModel orderModel = new OrderModel();
        orderModel.setRestaurantId(restaurantId);
        orderModel.setCustomerId(costumerId);
        orderModel.setStartTime(LocalDateTime.now());
        orderModel.setStatus(MessageConstants.PENDING_STATUS);

        orderPersistencePort.saveOrder(orderModel);

    }
}

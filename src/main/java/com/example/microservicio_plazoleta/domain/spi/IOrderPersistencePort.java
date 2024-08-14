package com.example.microservicio_plazoleta.domain.spi;

import com.example.microservicio_plazoleta.domain.model.OrderModel;

import java.util.List;

public interface IOrderPersistencePort {

    void saveOrder(OrderModel orderModel);

    Long getAuthenticatedUserId();

    List<OrderModel> findOrderByCustomerIdAndRestaurantId(Long customerId, Long restaurantId);

}
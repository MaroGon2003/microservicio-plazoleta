package com.example.microservicio_plazoleta.domain.spi;

import com.example.microservicio_plazoleta.domain.model.OrderModel;

import java.util.List;

public interface IOrderPersistencePort {

    Long saveOrder(OrderModel orderModel);

    Long getAuthenticatedUserId();

    List<OrderModel> findOrderByCustomerIdAndRestaurantId(Long customerId, Long restaurantId);

    List<OrderModel> findOrderByCustomerId(Long customerId);

    OrderModel getOrderById(Long orderId);

    void updateOrder(OrderModel orderModel);

}

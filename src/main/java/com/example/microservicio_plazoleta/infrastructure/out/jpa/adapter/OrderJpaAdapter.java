package com.example.microservicio_plazoleta.infrastructure.out.jpa.adapter;

import com.example.microservicio_plazoleta.domain.model.OrderModel;
import com.example.microservicio_plazoleta.domain.spi.IOrderPersistencePort;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.IOrderRepository;
import com.example.microservicio_plazoleta.infrastructure.utils.JwtAuthorizationExtractor;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Transactional
@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    @Override
    public Long saveOrder(OrderModel orderModel) {

        OrderEntity orderEntity =  orderRepository.save(orderEntityMapper.toOrderEntity(orderModel));
        return orderEntity.getId();

    }

    @Override
    public Long getAuthenticatedUserId() {
        return JwtAuthorizationExtractor.extractUserId();
    }

    @Override
    public List<OrderModel> findOrderByCustomerIdAndRestaurantId(Long customerId, Long restaurantId) {
        return orderEntityMapper.toModelList(orderRepository.findAllByIdCustomerAndRestaurantEntityId(customerId, restaurantId));
    }

    @Override
    public List<OrderModel> findOrderByCustomerId(Long customerId) {
        return orderEntityMapper.toModelList(orderRepository.findAllByIdCustomer(customerId));
    }

    @Override
    public OrderModel getOrderById(Long orderId) {
        return orderEntityMapper.toOrderModel(orderRepository.findById(orderId).orElse(null));
    }

    @Override
    public void updateOrder(OrderModel orderModel) {
        orderRepository.save(orderEntityMapper.toOrderEntity(orderModel));
    }

    @Override
    public List<OrderModel> showOrderPeriod(Long ownerId) {
        return orderEntityMapper.toModelList(orderRepository.findAllByRestaurantEntityIdOwnerAndEndTimeNotNull(ownerId));
    }

    @Override
    public List<Object[]> showOrderRanking(Long restaurantId) {
        return orderRepository.getAverageDurationPerEmployee(restaurantId);
    }

}

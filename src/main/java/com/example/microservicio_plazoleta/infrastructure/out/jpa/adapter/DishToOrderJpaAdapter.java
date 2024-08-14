package com.example.microservicio_plazoleta.infrastructure.out.jpa.adapter;

import com.example.microservicio_plazoleta.domain.model.DishToOrderModel;
import com.example.microservicio_plazoleta.domain.model.OrderModel;
import com.example.microservicio_plazoleta.domain.spi.IDishToOrderPersistencePort;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper.IDishToOrderEntityMapper;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.IDishToOrderRepository;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.IOrderRepository;
import com.example.microservicio_plazoleta.infrastructure.utils.JwtAuthorizationExtractor;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class DishToOrderJpaAdapter implements IDishToOrderPersistencePort {

    private final IDishToOrderRepository dishToOrderRepository;
    private final IDishToOrderEntityMapper dishToOrderEntityMapper;
    private final IOrderRepository orderRepository;

    @Override
    public boolean existsOrderById(Long idOrder) {
        return orderRepository.existsById(idOrder);
    }

    @Override
    public boolean existsOrderByOrderIdAndCustomerId(Long idOrder, Long idCustomer) {
        return orderRepository.existsByIdAndIdCustomer(idOrder, idCustomer);
    }

    @Override
    public boolean existsOrderByOrderIdAndCustomerIdAndStatus(Long idOrder, Long idCustomer) {
        return orderRepository.existsByIdAndIdCustomerAndStatusContainingIgnoreCase(idOrder, idCustomer, "PENDING");
    }

    @Override
    public Long getAuthenticatedUserId() {
        return JwtAuthorizationExtractor.extractUserId();
    }

    @Override
    public OrderModel getOrderById(Long idOrder) {
        return dishToOrderEntityMapper.toOrderModel(orderRepository.findById(idOrder).orElse(null));
    }

    @Override
    public void addDishToOrder(DishToOrderModel dishToOrderModel) {
        dishToOrderRepository.save(dishToOrderEntityMapper.toDishToOrderEntity(dishToOrderModel));
    }
}

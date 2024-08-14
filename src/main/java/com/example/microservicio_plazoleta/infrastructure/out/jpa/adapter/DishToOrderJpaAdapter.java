package com.example.microservicio_plazoleta.infrastructure.out.jpa.adapter;

import com.example.microservicio_plazoleta.domain.model.DishToOrderModel;
import com.example.microservicio_plazoleta.domain.spi.IDishToOrderPersistencePort;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper.IDishToOrderEntityMapper;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.IDishToOrderRepository;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.IOrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Transactional
@RequiredArgsConstructor
public class DishToOrderJpaAdapter implements IDishToOrderPersistencePort {

    private final IDishToOrderRepository dishToOrderRepository;
    private final IDishToOrderEntityMapper dishToOrderEntityMapper;
    private final IOrderRepository orderRepository;

    @Override
    public boolean existsOrderByOrderIdAndCustomerId(Long idOrder, Long idCustomer) {
        return orderRepository.existsByIdAndIdCustomer(idOrder, idCustomer);
    }

    @Override
    public boolean existsOrderByOrderIdAndCustomerIdAndStatus(Long idOrder, Long idCustomer) {
        return orderRepository.existsByIdAndIdCustomerAndStatusContainingIgnoreCase(idOrder, idCustomer, "PENDING");
    }

    @Override
    public void saveAll(List<DishToOrderModel> dishToOrderModelList) {
        dishToOrderRepository.saveAll(dishToOrderEntityMapper.toDishToOrderEntityList(dishToOrderModelList));
    }
}

package com.example.microservicio_plazoleta.infrastructure.out.jpa.repository;

import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    boolean existsByIdAndIdCustomer(Long idOrder, Long idCustomer);

    boolean existsByIdAndIdCustomerAndStatusContainingIgnoreCase(Long idOrder, Long idCustomer, String status);

    List<OrderEntity> findAllByIdCustomerAndRestaurantEntityId(long idCustomer, long idRestaurant);

    List<OrderEntity> findAllByIdCustomer(long idCustomer);

}

package com.example.microservicio_plazoleta.infrastructure.out.jpa.repository;

import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByIdCustomerAndIdRestaurantId(long idCustomer, long idRestaurant);

    boolean existsByIdAndIdCustomer(Long idOrder, Long idCustomer);

    boolean existsByIdAndIdCustomerAndStatusContainingIgnoreCase(Long idOrder, Long idCustomer, String status);

}

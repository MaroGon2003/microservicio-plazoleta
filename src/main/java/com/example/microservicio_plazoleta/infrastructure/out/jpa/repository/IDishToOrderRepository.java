package com.example.microservicio_plazoleta.infrastructure.out.jpa.repository;

import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.DishToOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishToOrderRepository extends JpaRepository<DishToOrderEntity, DishToOrderEntity.DishToOrderId> {

    Page<DishToOrderEntity> findAllByOrderEntityRestaurantEntityIdAndOrderEntityStatusContainingIgnoreCase(Long restaurantId, String status, Pageable pageable);

}

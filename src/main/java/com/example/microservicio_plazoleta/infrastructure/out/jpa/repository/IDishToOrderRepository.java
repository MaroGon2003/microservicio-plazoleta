package com.example.microservicio_plazoleta.infrastructure.out.jpa.repository;

import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.DishToOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishToOrderRepository extends JpaRepository<DishToOrderEntity, DishToOrderEntity.DishToOrderId> {
}

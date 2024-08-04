package com.example.microservicio_plazoleta.infrastructure.out.jpa.repository;

import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {

}

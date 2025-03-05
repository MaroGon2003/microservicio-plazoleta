package com.example.microservicio_plazoleta.infrastructure.out.jpa.repository;

import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {

    Page<DishEntity> findByIdRestaurantIdAndIdCategoryId(long idRestaurant, long idCategory, Pageable pageable);

    Optional<DishEntity> findByIdAndIdRestaurantId(Long id, Long idRestaurant);

}

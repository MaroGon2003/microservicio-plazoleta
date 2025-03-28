package com.example.microservicio_plazoleta.infrastructure.out.jpa.repository;

import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

}

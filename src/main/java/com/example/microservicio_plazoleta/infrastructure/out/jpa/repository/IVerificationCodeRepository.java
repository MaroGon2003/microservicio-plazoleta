package com.example.microservicio_plazoleta.infrastructure.out.jpa.repository;

import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.VerificationCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVerificationCodeRepository extends JpaRepository<VerificationCodeEntity, Long> {

    VerificationCodeEntity findByorderEntityId(Long orderId);

}

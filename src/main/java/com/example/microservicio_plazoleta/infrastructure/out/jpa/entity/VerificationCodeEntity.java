package com.example.microservicio_plazoleta.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orden_codigo")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VerificationCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int pin;

    @OneToOne
    @JoinColumn(name = "id_order", nullable = false)
    private OrderEntity orderEntity;

}

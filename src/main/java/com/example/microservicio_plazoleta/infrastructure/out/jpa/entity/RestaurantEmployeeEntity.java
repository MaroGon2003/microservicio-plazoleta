package com.example.microservicio_plazoleta.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "restaurante_empleado")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantEmployeeEntity {

    @EmbeddedId
    private RestaurantEmployeeId restaurantEmployeeId;

    @Column(name = "employee_id", nullable = false, insertable = false, updatable = false)
    private Long employeeId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false, insertable = false, updatable = false)
    private RestaurantEntity restaurantEntity;

    private LocalDate contractEmployeeDate;

    @Data
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RestaurantEmployeeId implements Serializable {

        @Column(name = "employee_id")
        private Long employeeId;

        @Column(name = "restaurant_id")
        private Long restaurantId;

    }

}

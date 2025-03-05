package com.example.microservicio_plazoleta.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "pedidos_platos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DishToOrderEntity {

    @EmbeddedId
    private DishToOrderId dishToOrderId;

    @ManyToOne
    @MapsId("idOrder")
    @JoinColumn(name = "id_order", nullable = false)
    private OrderEntity orderEntity;

    @ManyToOne
    @MapsId("idDish")
    @JoinColumn(name = "id_dish", nullable = false)
    private DishEntity dishEntity;

    @Column(nullable = false)
    private int amount;

    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DishToOrderId implements Serializable {

        @Column(name = "id_order")
        private Long idOrder;
        @Column(name = "id_dish")
        private Long idDish;

    }

}

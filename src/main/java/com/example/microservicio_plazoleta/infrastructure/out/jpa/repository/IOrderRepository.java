package com.example.microservicio_plazoleta.infrastructure.out.jpa.repository;

import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    boolean existsByIdAndIdCustomer(Long idOrder, Long idCustomer);

    boolean existsByIdAndIdCustomerAndStatusContainingIgnoreCase(Long idOrder, Long idCustomer, String status);

    List<OrderEntity> findAllByIdCustomerAndRestaurantEntityId(long idCustomer, long idRestaurant);

    List<OrderEntity> findAllByIdCustomer(long idCustomer);

    List<OrderEntity> findAllByRestaurantEntityIdOwnerAndEndTimeNotNull(Long ownerId);

    @Query("SELECT o.idEmployee, AVG(TIMESTAMPDIFF(second, o.startTime, o.endTime)) " +
            "FROM OrderEntity o " +
            "WHERE o.endTime IS NOT NULL " +
            "AND o.restaurantEntity.id = :restaurantId " +
            "GROUP BY o.idEmployee " +
            "ORDER BY AVG(TIMESTAMPDIFF(second, o.startTime, o.endTime))")
    List<Object[]> getAverageDurationPerEmployee(@Param("restaurantId") Long restaurantId);

}

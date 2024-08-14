package com.example.microservicio_plazoleta.domain.spi;

import com.example.microservicio_plazoleta.domain.model.DishToOrderModel;
import com.example.microservicio_plazoleta.domain.model.OrderModel;

public interface IDishToOrderPersistencePort {

    boolean existsOrderById(Long idOrder);

    boolean existsOrderByOrderIdAndCustomerId(Long idOrder, Long idCustomer);

    boolean existsOrderByOrderIdAndCustomerIdAndStatus(Long idOrder, Long idCustomer);

    Long getAuthenticatedUserId();

    OrderModel getOrderById(Long idOrder);

    void addDishToOrder(DishToOrderModel dishToOrderModel);

}

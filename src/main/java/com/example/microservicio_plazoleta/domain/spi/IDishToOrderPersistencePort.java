package com.example.microservicio_plazoleta.domain.spi;

import com.example.microservicio_plazoleta.domain.model.DishToOrderModel;

import java.util.List;

public interface IDishToOrderPersistencePort {

    boolean existsOrderByOrderIdAndCustomerId(Long idOrder, Long idCustomer);

    boolean existsOrderByOrderIdAndCustomerIdAndStatus(Long idOrder, Long idCustomer);

    void saveAll(List<DishToOrderModel> dishToOrderModelList);

}

package com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper;

import com.example.microservicio_plazoleta.domain.model.DishToOrderModel;
import com.example.microservicio_plazoleta.domain.model.OrderModel;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.DishToOrderEntity;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishToOrderEntityMapper {

    @Mapping(source = "orderModelId", target = "idOrder.id")
    @Mapping(source = "dishModelId", target = "idDish.id")
    @Mapping(target = "dishToOrderId", expression = "java(new DishToOrderEntity.DishToOrderId(" +
            "model.getOrderModelId(), model.getDishModelId()))")
    DishToOrderEntity toDishToOrderEntity(DishToOrderModel model);

    @Mapping(target = "restaurantId", source = "idRestaurant.id")
    @Mapping(target = "customerId", source = "idCustomer")
    OrderModel toOrderModel(OrderEntity orderEntity);

    List<DishToOrderEntity> toDishToOrderEntityList(List<DishToOrderModel> dishToOrderModelList);

}

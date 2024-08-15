package com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper;

import com.example.microservicio_plazoleta.domain.model.DishToOrderModel;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.DishToOrderEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishToOrderEntityMapper {

    @Mapping(target = "orderEntity", source = "orderModel")
    @Mapping(target = "dishEntity", source = "dishModel")
    @Mapping(target = "dishToOrderId", expression = "java(new DishToOrderEntity.DishToOrderId(" +
              "dishToOrderModel.getOrderModel().getId(), dishToOrderModel.getDishModel().getId()))")
    DishToOrderEntity toDishToOrderEntity(DishToOrderModel dishToOrderModel);

    @Mapping(target = "orderModel", source = "orderEntity")
    @Mapping(target = "dishModel", source = "dishEntity")
    @Mapping(target = "orderModel.restaurantModel", source = "orderEntity.restaurantEntity")
    @Mapping(target = "orderModel.customerId", source = "orderEntity.idCustomer")
    DishToOrderModel toOrderDishModel(DishToOrderEntity dishToOrderEntity);

    List<DishToOrderEntity> toDishToOrderEntityList(List<DishToOrderModel> dishToOrderModelList);

    List<DishToOrderModel> toDishToOrderModelList(List<DishToOrderEntity> dishToOrderEntityList);

}

package com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper;

import com.example.microservicio_plazoleta.domain.model.OrderModel;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {

    @Mapping(source = "restaurantModel", target = "restaurantEntity")
    @Mapping(source = "customerId", target = "idCustomer")
    OrderEntity toOrderEntity(OrderModel orderModel);

    List<OrderModel> toModelList(List<OrderEntity> orderEntityList);

    OrderModel toOrderModel(OrderEntity orderEntity);

}

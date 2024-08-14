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

    @Mapping(source = "restaurantId", target = "idRestaurant.id")
    @Mapping(source = "customerId", target = "idCustomer")
    OrderEntity toOrderEntity(OrderModel orderModel);

    @Mapping(source = "idCustomer", target = "customerId")
    @Mapping(source = "idRestaurant.id", target = "restaurantId")
    List<OrderModel> toModelList(List<OrderEntity> orderEntityList);

}

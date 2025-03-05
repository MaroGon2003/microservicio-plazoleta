package com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper;

import com.example.microservicio_plazoleta.domain.model.RestaurantEmployeeModel;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.RestaurantEmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEmployeeEntityMapper {

    @Mapping(source = "restaurantEntity", target = "restaurantModel")
    RestaurantEmployeeModel toModel(RestaurantEmployeeEntity restaurantEmployeeEntity);

    @Mapping(source = "restaurantModel", target = "restaurantEntity")
    @Mapping(target = "restaurantEmployeeId", expression = "java(new RestaurantEmployeeEntity.RestaurantEmployeeId(" +
            "restaurantEmployeeModel.getEmployeeId(), restaurantEmployeeModel.getRestaurantModel().getId()))")
    RestaurantEmployeeEntity toEntity(RestaurantEmployeeModel restaurantEmployeeModel);

}

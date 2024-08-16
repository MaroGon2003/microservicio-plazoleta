package com.example.microservicio_plazoleta.application.mapper;

import com.example.microservicio_plazoleta.application.dto.request.RestaurantEmployeeRequestDto;
import com.example.microservicio_plazoleta.domain.model.RestaurantEmployeeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEmployeeRequestMapper {

    @Mapping(source = "restaurantId", target = "restaurantModel.id")
    RestaurantEmployeeModel toModel(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto);

}

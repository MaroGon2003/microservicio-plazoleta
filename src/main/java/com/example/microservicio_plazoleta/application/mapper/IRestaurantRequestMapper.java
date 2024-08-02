package com.example.microservicio_plazoleta.application.mapper;

import com.example.microservicio_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.microservicio_plazoleta.domain.model.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantRequestMapper {

    @Mapping(source = "nit", target = "nit")
    RestaurantModel toRestaurantModel(RestaurantRequestDto restaurantRequestDto);

}

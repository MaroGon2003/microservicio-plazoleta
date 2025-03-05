package com.example.microservicio_plazoleta.application.mapper;

import com.example.microservicio_plazoleta.application.dto.response.RestaurantResponseDto;
import com.example.microservicio_plazoleta.domain.model.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {

    List<RestaurantResponseDto> toRestaurantResponseDto(List<RestaurantModel> restaurantModelList);

}

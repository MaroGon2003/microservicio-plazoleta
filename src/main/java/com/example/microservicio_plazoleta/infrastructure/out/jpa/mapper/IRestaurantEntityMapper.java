package com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper;

import com.example.microservicio_plazoleta.domain.model.RestaurantModel;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEntityMapper {

    RestaurantEntity toEntity(RestaurantModel restaurantModel);

    RestaurantModel toModel(RestaurantEntity restaurantEntity);

    List<RestaurantModel> toRestaurantModelList(List<RestaurantEntity> restaurantEntityList);
}

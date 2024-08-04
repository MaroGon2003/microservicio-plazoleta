package com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper;

import com.example.microservicio_plazoleta.domain.model.DishModel;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishEntityMapper {

    @Mapping(target = "restaurantId", source = "idRestaurant.id")
    @Mapping(target = "categoryId", source = "idCategory.id")
    DishModel toDishModel(DishEntity dishEntity);

    @Mapping(target = "idCategory.id", source = "categoryId")
    @Mapping(target = "idRestaurant.id", source = "restaurantId")
    DishEntity toEntity(DishModel dishModel);

}

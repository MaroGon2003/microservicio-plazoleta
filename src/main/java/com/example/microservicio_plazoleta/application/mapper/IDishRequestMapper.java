package com.example.microservicio_plazoleta.application.mapper;

import com.example.microservicio_plazoleta.application.dto.request.DishRequestDto;
import com.example.microservicio_plazoleta.application.dto.request.DishUpdateActiveRequestDto;
import com.example.microservicio_plazoleta.application.dto.request.DishUpdateDto;
import com.example.microservicio_plazoleta.domain.model.DishModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishRequestMapper {

    @Mapping(source = "idRestaurant", target = "restaurantId")
    @Mapping(source = "idCategory", target = "categoryId")
    @Mapping(source = "imageUrl", target = "imageUrl")
    DishModel toDishModel(DishRequestDto dishRequestDto);

    DishModel toDishModelUpdate(DishUpdateDto dishUpdateDto);

    DishModel toDishModelActive(DishUpdateActiveRequestDto dishUpdateActiveRequestDto);

}

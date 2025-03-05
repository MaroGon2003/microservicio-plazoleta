package com.example.microservicio_plazoleta.application.mapper;

import com.example.microservicio_plazoleta.application.dto.request.DishToOrderRequestDto;
import com.example.microservicio_plazoleta.domain.model.DishToOrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishToOrderRequestMapper {

    @Mapping(target = "dishModel.id", source = "idDish")
    DishToOrderModel toDishToOrderModel(DishToOrderRequestDto dishToOrderRequestDto);

    List<DishToOrderModel> toDishToOrderModelList(List<DishToOrderRequestDto> dishToOrderRequestDtoList);

}

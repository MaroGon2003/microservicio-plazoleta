package com.example.microservicio_plazoleta.infrastructure.input.feign.mapper;

import com.example.microservicio_plazoleta.domain.model.TraceabilityModel;
import com.example.microservicio_plazoleta.infrastructure.input.feign.dto.TraceabilityDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITraceabilityDtoMapper {

    TraceabilityDto toDto(TraceabilityModel traceabilityModel);
    List<TraceabilityModel> toModelList(List<TraceabilityDto> traceabilityDtoList);
    List<TraceabilityDto> toDtoList(List<TraceabilityModel> traceabilityModelList);

}

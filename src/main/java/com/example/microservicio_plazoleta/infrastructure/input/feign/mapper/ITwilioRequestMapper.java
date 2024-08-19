package com.example.microservicio_plazoleta.infrastructure.input.feign.mapper;

import com.example.microservicio_plazoleta.domain.model.TwilioModel;
import com.example.microservicio_plazoleta.infrastructure.input.feign.dto.TwilioRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITwilioRequestMapper {

    TwilioRequestDto toTwilioRequestDto(TwilioModel twilioModel);

}

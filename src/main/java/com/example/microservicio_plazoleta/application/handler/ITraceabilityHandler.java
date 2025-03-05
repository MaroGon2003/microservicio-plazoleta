package com.example.microservicio_plazoleta.application.handler;

import com.example.microservicio_plazoleta.infrastructure.input.feign.dto.TraceabilityDto;

import java.util.List;

public interface ITraceabilityHandler {

    List<TraceabilityDto> getHistory();

}

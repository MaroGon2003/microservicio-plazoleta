package com.example.microservicio_plazoleta.application.handler.impl;

import com.example.microservicio_plazoleta.application.handler.ITraceabilityHandler;
import com.example.microservicio_plazoleta.domain.api.ITraceabilityServicePort;
import com.example.microservicio_plazoleta.infrastructure.input.feign.dto.TraceabilityDto;
import com.example.microservicio_plazoleta.infrastructure.input.feign.mapper.ITraceabilityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TraceabilityHandler implements ITraceabilityHandler {

    private final ITraceabilityDtoMapper traceabilityDtoMapper;
    private final ITraceabilityServicePort traceabilityServicePort;

    @Override
    public List<TraceabilityDto> getHistory() {
        return traceabilityDtoMapper.toDtoList(traceabilityServicePort.getHistory());
    }
}

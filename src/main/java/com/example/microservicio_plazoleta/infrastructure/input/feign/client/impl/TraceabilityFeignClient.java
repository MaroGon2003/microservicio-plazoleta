package com.example.microservicio_plazoleta.infrastructure.input.feign.client.impl;

import com.example.microservicio_plazoleta.domain.api.ITraceabilityFeignClientPort;
import com.example.microservicio_plazoleta.domain.model.TraceabilityModel;
import com.example.microservicio_plazoleta.infrastructure.input.feign.client.ITraceabilityFeignClient;
import com.example.microservicio_plazoleta.infrastructure.input.feign.dto.TraceabilityDto;
import com.example.microservicio_plazoleta.infrastructure.input.feign.mapper.ITraceabilityDtoMapper;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

public class TraceabilityFeignClient implements ITraceabilityFeignClientPort {

    private final ITraceabilityFeignClient traceabilityFeignClient;
    private final ITraceabilityDtoMapper traceabilityDtoMapper;

    public TraceabilityFeignClient(ITraceabilityFeignClient traceabilityFeignClient, ITraceabilityDtoMapper traceabilityDtoMapper) {
        this.traceabilityFeignClient = traceabilityFeignClient;
        this.traceabilityDtoMapper = traceabilityDtoMapper;
    }


    @Override
    public void saveTraceability(TraceabilityModel traceabilityModel) {
        traceabilityFeignClient.saveTraceability(traceabilityDtoMapper.toDto(traceabilityModel));
    }

    @Override
    public List<TraceabilityModel> getTraceability(Long customerId) {
        ResponseEntity<List<TraceabilityDto>> response =
                traceabilityFeignClient.getTraceabilityHistory(customerId);
        if (!response.getStatusCode().is2xxSuccessful()) {
            return Collections.emptyList();
        }
        return traceabilityDtoMapper.toModelList(response.getBody());
    }
}

package com.example.microservicio_plazoleta.domain.api;

import com.example.microservicio_plazoleta.domain.model.TraceabilityModel;

import java.util.List;

public interface ITraceabilityFeignClientPort {

    void saveTraceability(TraceabilityModel traceabilityModel);

    List<TraceabilityModel> getTraceability(Long customerId);

}

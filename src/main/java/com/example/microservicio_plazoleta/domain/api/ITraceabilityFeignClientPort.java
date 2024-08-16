package com.example.microservicio_plazoleta.domain.api;

import com.example.microservicio_plazoleta.domain.model.TraceabilityModel;

public interface ITraceabilityFeignClientPort {

    void saveTraceability(TraceabilityModel traceabilityModel);

}

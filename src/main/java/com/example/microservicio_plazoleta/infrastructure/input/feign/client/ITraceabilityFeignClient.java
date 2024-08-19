package com.example.microservicio_plazoleta.infrastructure.input.feign.client;

import com.example.microservicio_plazoleta.infrastructure.input.feign.dto.TraceabilityDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "microservicio-trazabilidad", url = "localhost:8083")
public interface ITraceabilityFeignClient {

    @PostMapping("/traceability/save-traceability")
    ResponseEntity<Void> saveTraceability(@Valid @RequestBody TraceabilityDto traceabilityDto);

    @GetMapping("/traceability/get-traceability-history/{id}")
    ResponseEntity<List<TraceabilityDto>> getTraceabilityHistory(@PathVariable Long id);
}

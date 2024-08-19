package com.example.microservicio_plazoleta.infrastructure.input.rest;

import com.example.microservicio_plazoleta.application.handler.ITraceabilityHandler;
import com.example.microservicio_plazoleta.infrastructure.input.feign.dto.TraceabilityDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/traceability")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class TraceabilityRestController {

    private final ITraceabilityHandler traceabilityHandler;

    @Secured({"CUSTOMER"})
    @GetMapping("/get-traceability")
    public ResponseEntity<List<TraceabilityDto>> getTraceability() {
        return ResponseEntity.ok(traceabilityHandler.getHistory());
    }

}

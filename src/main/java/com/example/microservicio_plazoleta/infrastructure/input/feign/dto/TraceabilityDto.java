package com.example.microservicio_plazoleta.infrastructure.input.feign.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TraceabilityDto {

    private Long orderId;
    private Long customerId;
    private String customerEmail;
    private LocalDateTime date;
    private String previousStatus;
    private String newStatus;
    private Long employeeId;
    private String employeeEmail;

}

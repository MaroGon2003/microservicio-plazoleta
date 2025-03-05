package com.example.microservicio_plazoleta.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTimeResponseDto {

    Long orderId;
    Long customerId;
    String startTime;
    String endTime;
    String period;

}

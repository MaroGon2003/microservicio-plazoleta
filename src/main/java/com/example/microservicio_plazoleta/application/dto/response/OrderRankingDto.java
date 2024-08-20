package com.example.microservicio_plazoleta.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRankingDto {

    private Long employeeId;
    private String avgDuration;

}

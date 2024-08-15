package com.example.microservicio_plazoleta.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

    private String startTime;
    private String endTime;
    private String status;
    private Long idCustomer;
    private Long idEmployee;
    private String restaurantName;
    private List<OrderDishResponseDto> dishes;

}

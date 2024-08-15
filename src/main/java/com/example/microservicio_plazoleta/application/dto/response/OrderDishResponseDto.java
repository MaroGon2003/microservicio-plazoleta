package com.example.microservicio_plazoleta.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDishResponseDto {

    private String dishName;
    private int price;
    private int amount;

}

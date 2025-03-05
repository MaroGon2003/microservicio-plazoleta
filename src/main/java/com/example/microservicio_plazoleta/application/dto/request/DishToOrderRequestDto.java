package com.example.microservicio_plazoleta.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishToOrderRequestDto {

    private Long idDish;
    private int amount;

}

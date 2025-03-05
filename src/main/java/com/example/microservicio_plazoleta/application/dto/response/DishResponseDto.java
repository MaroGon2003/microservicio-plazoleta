package com.example.microservicio_plazoleta.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishResponseDto {

    private String name;
    private String description;
    private int price;
    private String imageUrl;
    private boolean active;
    private String category;

}

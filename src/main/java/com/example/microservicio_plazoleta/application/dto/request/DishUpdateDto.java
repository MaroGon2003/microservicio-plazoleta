package com.example.microservicio_plazoleta.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishUpdateDto {

    @Min(value = 1, message = "The price must be a number greater than 0")
    private int price;

    @NotBlank(message = "The description must not be empty")
    private String description;
}

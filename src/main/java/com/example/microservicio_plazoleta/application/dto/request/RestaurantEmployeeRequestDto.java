package com.example.microservicio_plazoleta.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEmployeeRequestDto {

    @Min(value = 1, message = "The id must be a number greater than 0")
    @NotNull(message = "Employee id must not be empty")
    private Long employeeId;

    @Min(value = 1, message = "The id must be a number greater than 0")
    @NotNull(message = "Restaurant id must not be empty")
    private Long restaurantId;

}

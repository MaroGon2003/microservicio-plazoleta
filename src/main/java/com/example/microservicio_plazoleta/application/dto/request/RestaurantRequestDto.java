package com.example.microservicio_plazoleta.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequestDto {

    @NotBlank(message = "The name must not be empty")
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z\\d]*$",
            message = "The name of the restaurant cannot be just numbers")
    private String name;

    @NotBlank(message = "The address must not be empty")
    private String address;

    @Min(value = 1, message = "The id must be a number greater than 0")
    @NotNull(message = "Owner id must not be empty")
    private Long idOwner;

    @NotNull
    @NotBlank
    @Size(max = 13)
    @Pattern(regexp = "\\+?\\d+", message = "The phone must contain a maximum of 13 characters and can contain the + symbol")
    private String phone;

    @NotBlank(message = "The logo url must not be empty")
    private String logoUrl;

    @NotBlank(message = "The NIT must not be empty")
    @Pattern(regexp = "^\\d{9,13}$",
            message = "The NIT can only contain numbers and must not extend the 13 characters")
    private String nit;

}

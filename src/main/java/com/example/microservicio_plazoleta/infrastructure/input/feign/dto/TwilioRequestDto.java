package com.example.microservicio_plazoleta.infrastructure.input.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwilioRequestDto {

    private String message;
    private String phoneNumber;

}

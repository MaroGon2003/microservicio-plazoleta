package com.example.microservicio_plazoleta.infrastructure.input.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String name;
    private String surName;
    private Integer dni;
    private String phone;
    private LocalDate birdDate;
    private String email;
    private String password;
    private Long rolId;

}

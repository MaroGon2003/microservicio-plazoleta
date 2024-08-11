package com.example.microservicio_plazoleta.application.handler;

import com.example.microservicio_plazoleta.application.dto.request.DishRequestDto;
import com.example.microservicio_plazoleta.application.dto.request.DishUpdateActiveRequestDto;
import com.example.microservicio_plazoleta.application.dto.request.DishUpdateDto;
import com.example.microservicio_plazoleta.application.dto.response.DishResponseDto;

public interface IDishHandler {

    void saveDish(DishRequestDto dishRequestDto);

    DishResponseDto updateDish(Long id, Long ownerId, DishUpdateDto dishUpdateDto);

    void updateActiveDish(Long id, Long ownerId, DishUpdateActiveRequestDto dishUpdateActiveRequestDto);

}

package com.example.microservicio_plazoleta.application.handler;

import com.example.microservicio_plazoleta.application.dto.request.DishRequestDto;

public interface IDishHandler {

    void saveDish(DishRequestDto dishRequestDto);

}

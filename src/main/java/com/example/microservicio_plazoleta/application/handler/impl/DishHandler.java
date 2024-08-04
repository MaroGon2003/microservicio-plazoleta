package com.example.microservicio_plazoleta.application.handler.impl;

import com.example.microservicio_plazoleta.application.dto.request.DishRequestDto;
import com.example.microservicio_plazoleta.application.handler.IDishHandler;
import com.example.microservicio_plazoleta.application.mapper.IDishRequestMapper;
import com.example.microservicio_plazoleta.domain.api.IDishServicePort;
import com.example.microservicio_plazoleta.domain.model.DishModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;

    @Override
    public void saveDish(DishRequestDto dishRequestDto) {
        dishServicePort.saveDish(dishRequestMapper.toDishModel(dishRequestDto));
    }

}

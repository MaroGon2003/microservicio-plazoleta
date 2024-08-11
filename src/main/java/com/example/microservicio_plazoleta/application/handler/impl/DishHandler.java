package com.example.microservicio_plazoleta.application.handler.impl;

import com.example.microservicio_plazoleta.application.dto.request.DishRequestDto;
import com.example.microservicio_plazoleta.application.dto.request.DishUpdateActiveRequestDto;
import com.example.microservicio_plazoleta.application.dto.request.DishUpdateDto;
import com.example.microservicio_plazoleta.application.dto.response.DishResponseDto;
import com.example.microservicio_plazoleta.application.handler.IDishHandler;
import com.example.microservicio_plazoleta.application.mapper.IDishRequestMapper;
import com.example.microservicio_plazoleta.application.mapper.IDishResponseMapper;
import com.example.microservicio_plazoleta.domain.api.IDishServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;

    @Override
    public void saveDish(DishRequestDto dishRequestDto) {
        dishServicePort.saveDish(dishRequestMapper.toDishModel(dishRequestDto));
    }

    @Override
    public DishResponseDto updateDish(Long id, Long ownerId, DishUpdateDto dishUpdateDto) {
        return dishResponseMapper.toDishResponseDto(dishServicePort.updateDish(id, ownerId, dishRequestMapper.toDishModelUpdate(dishUpdateDto)));
    }

    @Override
    public void updateActiveDish(Long id,Long ownerId ,DishUpdateActiveRequestDto dishUpdateActiveRequestDto) {
        dishServicePort.updateActiveDish(id, ownerId, dishRequestMapper.toDishModelActive(dishUpdateActiveRequestDto));

    }

}

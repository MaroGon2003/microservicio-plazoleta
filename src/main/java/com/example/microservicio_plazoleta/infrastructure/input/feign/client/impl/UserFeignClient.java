package com.example.microservicio_plazoleta.infrastructure.input.feign.client.impl;

import com.example.microservicio_plazoleta.domain.api.IUserFeignServicePort;
import com.example.microservicio_plazoleta.domain.model.UserModel;
import com.example.microservicio_plazoleta.infrastructure.exception.OwnerNotFoundException;
import com.example.microservicio_plazoleta.infrastructure.exception.UserNotFoundException;
import com.example.microservicio_plazoleta.infrastructure.input.feign.client.IUserFeignClient;
import com.example.microservicio_plazoleta.infrastructure.input.feign.dto.UserResponseDto;
import com.example.microservicio_plazoleta.infrastructure.input.feign.mapper.IUserResponseMapper;
import com.example.microservicio_plazoleta.infrastructure.utils.Constans;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class UserFeignClient implements IUserFeignServicePort {

    private final IUserFeignClient feignClient;
    private final IUserResponseMapper userResponseMapper;

    public UserFeignClient(IUserFeignClient userFeignClient, IUserResponseMapper userResponseMapper) {
        this.feignClient = userFeignClient;
        this.userResponseMapper = userResponseMapper;
    }

    @Override
    public boolean validateOwner(Long userId) {
        ResponseEntity<Map<String, Object>> response = feignClient.validateOwner(userId);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new OwnerNotFoundException(Constans.OWNER_NOT_FOUND);
        }
        return (boolean) response.getBody().get("owner");
    }

    @Override
    public UserModel getUserById(Long userId) {
        ResponseEntity<UserResponseDto> responseEntity = feignClient.getUserById(userId);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new UserNotFoundException(Constans.USER_NOT_FOUND);
        }
        UserResponseDto userResponseDto = responseEntity.getBody();

        return userResponseMapper.toUserModel(userResponseDto);

    }
}

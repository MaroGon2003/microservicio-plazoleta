package com.example.microservicio_plazoleta.infrastructure.input.feign.impl;

import com.example.microservicio_plazoleta.domain.api.IUserFeignServicePort;
import com.example.microservicio_plazoleta.infrastructure.exception.OwnerNotFoundException;
import com.example.microservicio_plazoleta.infrastructure.input.feign.IUserFeignClient;
import com.example.microservicio_plazoleta.infrastructure.utils.Constans;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class UserFeignClient implements IUserFeignServicePort {

    private final IUserFeignClient feignClient;

    public UserFeignClient(IUserFeignClient userFeignClient) {
        this.feignClient = userFeignClient;
    }

    @Override
    public boolean validateOwner(Long userId) {
        ResponseEntity<Map<String, Object>> response = feignClient.validateOwner(userId);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new OwnerNotFoundException(Constans.OWNER_NOT_FOUND);
        }
        return (boolean) response.getBody().get("owner");
    }
}

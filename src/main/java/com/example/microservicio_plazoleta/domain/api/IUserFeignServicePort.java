package com.example.microservicio_plazoleta.domain.api;

import com.example.microservicio_plazoleta.domain.model.UserModel;

public interface IUserFeignServicePort {

    boolean validateOwner(Long userId);

    UserModel getUserById(Long userId);

}

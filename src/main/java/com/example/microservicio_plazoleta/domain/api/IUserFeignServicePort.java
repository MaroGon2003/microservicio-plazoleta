package com.example.microservicio_plazoleta.domain.api;

public interface IUserFeignServicePort {

    boolean validateOwner(Long userId);

}

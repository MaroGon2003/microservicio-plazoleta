package com.example.microservicio_plazoleta.infrastructure.input.feign.client;

import com.example.microservicio_plazoleta.infrastructure.input.feign.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "microservicio-usuarios", url = "localhost:8081")
public interface IUserFeignClient {

    @GetMapping("/user/validate-owner/{id}")
    ResponseEntity<Map<String,Object>> validateOwner(@PathVariable Long id);

    @GetMapping("/user/get-user/{id}")
    ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id);

}

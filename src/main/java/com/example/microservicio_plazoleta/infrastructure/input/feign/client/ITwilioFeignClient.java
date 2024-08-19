package com.example.microservicio_plazoleta.infrastructure.input.feign.client;

import com.example.microservicio_plazoleta.infrastructure.input.feign.dto.TwilioRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservicio-mensajeria", url = "localhost:8084")
public interface ITwilioFeignClient {

    @PostMapping("/twilio/send-message")
    ResponseEntity<String> sendMessage(@RequestBody TwilioRequestDto twilioRequestDto);

}

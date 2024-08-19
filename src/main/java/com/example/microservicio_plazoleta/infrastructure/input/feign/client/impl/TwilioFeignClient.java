package com.example.microservicio_plazoleta.infrastructure.input.feign.client.impl;

import com.example.microservicio_plazoleta.domain.api.ITwilioFeignClientPort;
import com.example.microservicio_plazoleta.domain.model.TwilioModel;
import com.example.microservicio_plazoleta.infrastructure.input.feign.client.ITwilioFeignClient;
import com.example.microservicio_plazoleta.infrastructure.input.feign.mapper.ITwilioRequestMapper;

public class TwilioFeignClient implements ITwilioFeignClientPort {

    private final ITwilioFeignClient twilioFeignClient;
    private final ITwilioRequestMapper twilioRequestMapper;

    public TwilioFeignClient(ITwilioFeignClient twilioFeignClient, ITwilioRequestMapper twilioRequestMapper) {
        this.twilioFeignClient = twilioFeignClient;
        this.twilioRequestMapper = twilioRequestMapper;
    }

    @Override
    public void sendMessage(TwilioModel twilioModel) {

        twilioFeignClient.sendMessage(twilioRequestMapper.toTwilioRequestDto(twilioModel));

    }
}

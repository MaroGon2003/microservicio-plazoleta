package com.example.microservicio_plazoleta.domain.api;

import com.example.microservicio_plazoleta.domain.model.TwilioModel;

public interface ITwilioFeignClientPort {

    void sendMessage(TwilioModel twilioModel);

}

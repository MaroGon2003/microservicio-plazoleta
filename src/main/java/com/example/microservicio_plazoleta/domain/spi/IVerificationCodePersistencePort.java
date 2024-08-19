package com.example.microservicio_plazoleta.domain.spi;

import com.example.microservicio_plazoleta.domain.model.VerificationCodeModel;

public interface IVerificationCodePersistencePort {

    void saveVerificationCode(VerificationCodeModel verificationCode);

    VerificationCodeModel getVerificationCode(Long idOrder);

}

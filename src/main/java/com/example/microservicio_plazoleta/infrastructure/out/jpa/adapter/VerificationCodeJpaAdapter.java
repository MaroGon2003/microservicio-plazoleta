package com.example.microservicio_plazoleta.infrastructure.out.jpa.adapter;

import com.example.microservicio_plazoleta.domain.model.VerificationCodeModel;
import com.example.microservicio_plazoleta.domain.spi.IVerificationCodePersistencePort;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper.IVerificationCodeEntityMapper;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.IVerificationCodeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class VerificationCodeJpaAdapter implements IVerificationCodePersistencePort {

    private final IVerificationCodeRepository verificationCodeRepository;
    private final IVerificationCodeEntityMapper verificationCodeEntityMapper;


    @Override
    public void saveVerificationCode(VerificationCodeModel verificationCode) {
        verificationCodeRepository.save(verificationCodeEntityMapper.toEntity(verificationCode));
    }

    @Override
    public VerificationCodeModel getVerificationCode(Long idOrder) {
        return verificationCodeEntityMapper.toModel(verificationCodeRepository.findByorderEntityId(idOrder));
    }
}

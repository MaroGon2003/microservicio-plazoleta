package com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper;

import com.example.microservicio_plazoleta.domain.model.VerificationCodeModel;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.VerificationCodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IVerificationCodeEntityMapper {

        @Mapping(source = "idOrder", target = "orderEntity.id")
        VerificationCodeEntity toEntity(VerificationCodeModel verificationCodeModel);

        @Mapping(source = "orderEntity.id", target = "idOrder")
        VerificationCodeModel toModel(VerificationCodeEntity verificationCodeEntity);
}

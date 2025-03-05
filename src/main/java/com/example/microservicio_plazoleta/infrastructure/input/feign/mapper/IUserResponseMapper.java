package com.example.microservicio_plazoleta.infrastructure.input.feign.mapper;

import com.example.microservicio_plazoleta.domain.model.UserModel;
import com.example.microservicio_plazoleta.infrastructure.input.feign.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {

    UserModel toUserModel(UserResponseDto userResponseDto);

}

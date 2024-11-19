package com.admiral.hackathon.customer_data.api.mapper;

import com.admiral.hackathon.customer_data.api.dto.MotorDto;
import com.admiral.hackathon.customer_data.entity.Account;
import com.admiral.hackathon.customer_data.entity.Policy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MotorMapper {

    MotorDto toDto(Policy entity);

    @Mapping(source = "policyNumber", target = "policyNumber")
    @Mapping(source = "registrationNumber", target = "registrationNumber")
    @Mapping(source = "make", target = "make")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "engineSize", target = "engineSize")
    @Mapping(source = "fuelType", target = "fuelType")
    @Mapping(source = "colour", target = "colour")
    @Mapping(target = "policyType", constant = "motor")
    Policy toEntity(MotorDto dto);

    default Account toAccount(MotorDto dto) {
        return Mappers.getMapper(AccountMapper.class).toAccount(dto);
    }
}

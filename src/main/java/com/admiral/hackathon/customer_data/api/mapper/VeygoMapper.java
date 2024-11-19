package com.admiral.hackathon.customer_data.api.mapper;

import com.admiral.hackathon.customer_data.api.dto.PetDto;
import com.admiral.hackathon.customer_data.api.dto.VeygoDto;
import com.admiral.hackathon.customer_data.entity.Account;
import com.admiral.hackathon.customer_data.entity.Policy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VeygoMapper {

    @Mapping(source = "policyNumber", target = "policyNumber")
    @Mapping(source = "registrationNumber", target = "registrationNumber")
    @Mapping(source = "make", target = "make")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "engineSize", target = "engineSize")
    @Mapping(source = "fuelType", target = "fuelType")
    @Mapping(source = "colour", target = "colour")
    VeygoDto toDto(Policy entity);

    @Mapping(target = "policyType", constant = "veygo")
    Policy toEntity(VeygoDto dto);

    default Account toAccount(VeygoDto dto) {
        return Mappers.getMapper(AccountMapper.class).toAccount(dto);
    }
}

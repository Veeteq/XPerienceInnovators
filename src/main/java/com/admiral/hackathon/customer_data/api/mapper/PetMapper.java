package com.admiral.hackathon.customer_data.api.mapper;

import com.admiral.hackathon.customer_data.api.dto.MotorDto;
import com.admiral.hackathon.customer_data.api.dto.PetDto;
import com.admiral.hackathon.customer_data.entity.Account;
import com.admiral.hackathon.customer_data.entity.Policy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PetMapper {

    @Mapping(target = "petType", source = "petType")
    @Mapping(target = "petName", source = "petName")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "breed", source = "breed")
    @Mapping(target = "petAgeYears", source = "petYears")
    @Mapping(target = "petAgeMonths", source = "petMonths")
    @Mapping(target = "pedigree", source = "pedigree")
    @Mapping(target = "hasMedicalConditions", source = "medicalConditions")
    PetDto toDto(Policy entity);

    @Mapping(source = "petType", target = "petType")
    @Mapping(source = "petName", target = "petName")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "breed", target = "breed")
    @Mapping(source = "petAgeYears", target = "petYears")
    @Mapping(source = "petAgeMonths", target = "petMonths")
    @Mapping(source = "pedigree", target = "pedigree")
    @Mapping(source = "hasMedicalConditions", target = "medicalConditions")
    @Mapping(target = "policyType", constant = "pet")
    Policy toEntity(PetDto dto);

    default Account toAccount(PetDto dto) {
        return Mappers.getMapper(AccountMapper.class).toAccount(dto);
    }
}

package com.admiral.hackathon.customer_data.api.mapper;

import com.admiral.hackathon.customer_data.api.dto.data.PolicyDto;
import com.admiral.hackathon.customer_data.entity.Policy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PolicyMapper {

    @Mapping(target = "policyNumber", source = "policyNumber")
    @Mapping(target = "registrationNumber", source = "registrationNumber")
    @Mapping(target = "make", source = "make")
    @Mapping(target = "model", source = "model")
    @Mapping(target = "engineSize", source = "engineSize")
    @Mapping(target = "fuelType", source = "fuelType")
    @Mapping(target = "colour", source = "colour")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "yearOfManufacture", source = "yearOfManufacture")
    @Mapping(target = "petType", source = "petType")
    @Mapping(target = "petName", source = "petName")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "breed", source = "breed")
    @Mapping(target = "petAgeYears", source = "petYears")
    @Mapping(target = "petAgeMonths", source = "petMonths")
    @Mapping(target = "pedigree", source = "pedigree")
    @Mapping(target = "hasMedicalConditions", source = "medicalConditions")
    @Mapping(target = "levelOfCover", source = "levelOfCover")
    PolicyDto toDto(Policy policy);
}

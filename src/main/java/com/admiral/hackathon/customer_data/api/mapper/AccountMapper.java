package com.admiral.hackathon.customer_data.api.mapper;

import com.admiral.hackathon.customer_data.api.dto.MotorDto;
import com.admiral.hackathon.customer_data.api.dto.PetDto;
import com.admiral.hackathon.customer_data.api.dto.VeygoDto;
import com.admiral.hackathon.customer_data.api.dto.data.AccountDto;
import com.admiral.hackathon.customer_data.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "name", source = "holderName")
    @Mapping(target = "address", source = "holderAddress")
    @Mapping(target = "phoneNumber", source = "holderPhoneNumber")
    @Mapping(target = "email", source = "holderEmail")
    Account toAccount(MotorDto dto);

    @Mapping(target = "name", source = "accountHolderName")
    @Mapping(target = "address", source = "accountHolderAddress")
    @Mapping(target = "phoneNumber", source = "accountHolderPhoneNumber")
    @Mapping(target = "email", source = "accountHolderEmail")
    Account toAccount(PetDto dto);

    @Mapping(target = "name", source = "holderName")
    @Mapping(target = "address", source = "holderAddress")
    @Mapping(target = "phoneNumber", source = "holderPhoneNumber")
    @Mapping(target = "email", source = "holderEmail")
    Account toAccount(VeygoDto dto);

    @Mapping(source = "name", target = "userName")
    @Mapping(source = "address", target = "userAddress")
    @Mapping(source = "phoneNumber", target = "userPhoneNumber")
    @Mapping(source = "email", target = "userEmail")
    AccountDto toDto(Account account);
}

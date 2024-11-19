package com.admiral.hackathon.customer_data.service;

import com.admiral.hackathon.customer_data.api.dto.PetDto;
import com.admiral.hackathon.customer_data.api.mapper.PetMapper;
import com.admiral.hackathon.customer_data.repository.AccountRepository;
import com.admiral.hackathon.customer_data.repository.PolicyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier(value = "pet")
public class PetService implements PolicyService<PetDto> {

    private final AccountRepository accountRepository;
    private final PolicyRepository policyRepository;
    private final PetMapper petMapper;

    public PetService(AccountRepository accountRepository, PolicyRepository policyRepository, PetMapper petMapper) {
        this.accountRepository = accountRepository;
        this.policyRepository = policyRepository;
        this.petMapper = petMapper;
    }

    @Override
    @Transactional
    public void savePolicies(List<PetDto> policies) {
        policies.stream().map(dto -> {
                    var account = accountRepository.findByEmail(dto.accountHolderEmail).orElseGet(() -> petMapper.toAccount(dto));
                    var policy = petMapper.toEntity(dto);
                    policy.setAccount(account);
                    return policy;
                })
                .forEach(policyRepository::save);
    }
}

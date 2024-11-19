package com.admiral.hackathon.customer_data.service;

import com.admiral.hackathon.customer_data.api.dto.MotorDto;
import com.admiral.hackathon.customer_data.api.mapper.MotorMapper;
import com.admiral.hackathon.customer_data.repository.AccountRepository;
import com.admiral.hackathon.customer_data.repository.PolicyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier(value = "motor")
public class MotorService implements PolicyService<MotorDto> {

    private final AccountRepository accountRepository;
    private final PolicyRepository policyRepository;
    private final MotorMapper motorMapper;

    public MotorService(AccountRepository accountRepository, PolicyRepository policyRepository, MotorMapper motorMapper) {
        this.accountRepository = accountRepository;
        this.policyRepository = policyRepository;
        this.motorMapper = motorMapper;
    }

    @Override
    @Transactional
    public void savePolicies(List<MotorDto> policies) {
        policies.stream().map(dto -> {
                    var account = accountRepository.findByEmail(dto.holderEmail).orElseGet(() -> motorMapper.toAccount(dto));
                    var policy = motorMapper.toEntity(dto);
                    policy.setAccount(account);
                    return policy;
                })
                .forEach(policyRepository::save);
    }
}

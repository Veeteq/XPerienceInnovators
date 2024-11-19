package com.admiral.hackathon.customer_data.service;

import com.admiral.hackathon.customer_data.api.dto.VeygoDto;
import com.admiral.hackathon.customer_data.api.mapper.VeygoMapper;
import com.admiral.hackathon.customer_data.repository.AccountRepository;
import com.admiral.hackathon.customer_data.repository.PolicyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier(value = "veygo")
public class VeygoService implements PolicyService<VeygoDto> {

    private final AccountRepository accountRepository;
    private final PolicyRepository policyRepository;
    private final VeygoMapper veygoMapper;

    @Autowired
    public VeygoService(AccountRepository accountRepository, PolicyRepository policyRepository, VeygoMapper veygoMapper) {
        this.accountRepository = accountRepository;
        this.policyRepository = policyRepository;
        this.veygoMapper = veygoMapper;
    }

    @Override
    @Transactional
    public void savePolicies(List<VeygoDto> policies) {
        policies.stream().map(dto -> {
                    var account = accountRepository.findByEmail(dto.holderEmail).orElseGet(() -> veygoMapper.toAccount(dto));
                    var policy = veygoMapper.toEntity(dto);
                    policy.setAccount(account);
                    return policy;
                })
                .forEach(policyRepository::save);
    }
}

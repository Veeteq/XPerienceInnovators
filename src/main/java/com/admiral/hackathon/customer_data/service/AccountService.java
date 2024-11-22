package com.admiral.hackathon.customer_data.service;

import com.admiral.hackathon.customer_data.api.dto.data.AccountDto;
import com.admiral.hackathon.customer_data.api.mapper.AccountMapper;
import com.admiral.hackathon.customer_data.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountMapper accountMapper,
                          AccountRepository accountRepository) {
        this.accountMapper = accountMapper;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountDto findAccountByEmail(String email) {
        var account = accountRepository.findByEmail(email).get();
        return accountMapper.toDto(account);
    }
}

package com.admiral.hackathon.customer_data.service;

import com.admiral.hackathon.customer_data.api.dto.data.AccountDto;
import com.admiral.hackathon.customer_data.api.mapper.AccountMapper;
import com.admiral.hackathon.customer_data.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Value("${azure.openai.abcde}")
    private String abcde;
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
        account.setName(abcde);
        return accountMapper.toDto(account);
    }
}

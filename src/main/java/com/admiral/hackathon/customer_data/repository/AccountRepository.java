package com.admiral.hackathon.customer_data.repository;

import com.admiral.hackathon.customer_data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);
}

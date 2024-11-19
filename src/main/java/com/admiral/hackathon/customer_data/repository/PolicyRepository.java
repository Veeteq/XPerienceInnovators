package com.admiral.hackathon.customer_data.repository;

import com.admiral.hackathon.customer_data.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
}

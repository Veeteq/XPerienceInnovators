package com.admiral.hackathon.customer_data.entity;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    @SequenceGenerator(name = "account_sequence", sequenceName = "accounts_seq", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "holder_name")
    private String name;

    @Column(name = "holder_address")
    private String address;

    @Column(name = "holder_phone_number")
    private String phoneNumber;

    @Column(name = "holder_email")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Policy> policies = new LinkedList<>();

    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Account setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Account setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Account setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Account setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public Account addPolicy(Policy policy) {
        this.policies.add(policy);
        return this;
    }
}

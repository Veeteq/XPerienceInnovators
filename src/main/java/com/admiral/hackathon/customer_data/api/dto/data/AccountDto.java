package com.admiral.hackathon.customer_data.api.dto.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {

    @JsonProperty(value = "userName")
    private String userName;

    @JsonProperty(value = "userAddress")
    private String userAddress;

    @JsonProperty(value = "userPhoneNumber")
    private String userPhoneNumber;

    @JsonProperty(value = "userEmail")
    private String userEmail;

    @JsonProperty(value = "policies")
    private List<PolicyDto> policies = new LinkedList<>();

    public String getUserName() {
        return userName;
    }

    public AccountDto setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public AccountDto setUserAddress(String userAddress) {
        this.userAddress = userAddress;
        return this;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public AccountDto setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public AccountDto setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public List<PolicyDto> getPolicies() {
        return policies;
    }

    public AccountDto setPolicies(List<PolicyDto> policies) {
        this.policies = policies;
        return this;
    }
}

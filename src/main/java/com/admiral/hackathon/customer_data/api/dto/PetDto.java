package com.admiral.hackathon.customer_data.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PetDto {
    @JsonProperty("PetName")
    public String petName;

    @JsonProperty("PetType")
    public String petType;

    @JsonProperty("Gender")
    public String gender;

    @JsonProperty("Breed")
    public String breed;

    @JsonProperty("PetAgeYears")
    public int petAgeYears;

    @JsonProperty("PetAgeMonths")
    public int petAgeMonths;

    @JsonProperty("Pedigree")
    public String pedigree;

    @JsonProperty("HasMedicalConditions")
    public String hasMedicalConditions;

    @JsonProperty("AccountHolderName")
    public String accountHolderName;

    @JsonProperty("AccountHolderAddress")
    public String accountHolderAddress;

    @JsonProperty("AccountHolderPhoneNumber")
    public String accountHolderPhoneNumber;

    @JsonProperty("AccountHolderEmail")
    public String accountHolderEmail;
}


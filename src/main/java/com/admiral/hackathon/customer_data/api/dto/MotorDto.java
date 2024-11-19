package com.admiral.hackathon.customer_data.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MotorDto {

    @JsonProperty(value = "PolicyNumber")
    public String policyNumber;

    @JsonProperty(value = "RegistrationNumber")
    public String registrationNumber;

    @JsonProperty(value = "Make")
    public String make;

    @JsonProperty(value = "YearOfManufacture")
    public int yearOfManufacture;

    @JsonProperty(value = "Model")
    public String model;

    @JsonProperty(value = "Type")
    public String type;

    @JsonProperty(value = "EngineSize")
    public String engineSize;

    @JsonProperty(value = "FuelType")
    public String fuelType;

    @JsonProperty(value = "Colour")
    public String colour;

    @JsonProperty(value = "HolderName")
    public String holderName;

    @JsonProperty(value = "HolderAddress")
    public String holderAddress;

    @JsonProperty(value = "HolderPhoneNumber")
    public String holderPhoneNumber;

    @JsonProperty(value = "HolderEmail")
    public String holderEmail;
}


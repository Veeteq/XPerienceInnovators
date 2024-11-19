package com.admiral.hackathon.customer_data.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VeygoDto {

    @JsonProperty("PolicyNumber")
    public String policyNumber;

    @JsonProperty("RegistrationNumber")
    public String registrationNumber;

    @JsonProperty("Make")
    public String make;

    @JsonProperty("YearOfManufacture")
    public int yearOfManufacture;

    @JsonProperty("Model")
    public String model;

    @JsonProperty("Type")
    public String type;

    @JsonProperty("EngineSize")
    public String engineSize;

    @JsonProperty("FuelType")
    public String fuelType;

    @JsonProperty("Colour")
    public String colour;

    @JsonProperty("AccHolderName")
    public String holderName;

    @JsonProperty("AccHolderAddress")
    public String holderAddress;

    @JsonProperty("AccHolderPhoneNumber")
    public String holderPhoneNumber;

    @JsonProperty("AccHolderEmail")
    public String holderEmail;

}



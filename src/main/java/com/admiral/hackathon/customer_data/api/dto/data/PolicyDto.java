package com.admiral.hackathon.customer_data.api.dto.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PolicyDto {

    @JsonProperty(value = "PolicyNumber")
    String policyNumber;

    @JsonProperty(value = "RegistrationNumber")
    private String registrationNumber;

    @JsonProperty(value = "Make")
    private String make;

    @JsonProperty(value = "YearOfManufacture")
    private int yearOfManufacture;

    @JsonProperty(value = "Model")
    private String model;

    @JsonProperty(value = "Type")
    private String type;

    @JsonProperty(value = "EngineSize")
    private String engineSize;

    @JsonProperty(value = "FuelType")
    private String fuelType;

    @JsonProperty(value = "Colour")
    private String colour;

    @JsonProperty("PetName")
    private String petName;

    @JsonProperty("PetType")
    private String petType;

    @JsonProperty("Gender")
    private String gender;

    @JsonProperty("Breed")
    private String breed;

    @JsonProperty("PetAgeYears")
    private int petAgeYears;

    @JsonProperty("PetAgeMonths")
    private int petAgeMonths;

    @JsonProperty("Pedigree")
    private String pedigree;

    @JsonProperty("HasMedicalConditions")
    private String hasMedicalConditions;

    @JsonProperty("LevelOfCoverage")
    private String levelOfCover;

    public String getPolicyNumber() {
        return policyNumber;
    }

    public PolicyDto setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
        return this;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public PolicyDto setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    public String getMake() {
        return make;
    }

    public PolicyDto setMake(String make) {
        this.make = make;
        return this;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public PolicyDto setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
        return this;
    }

    public String getModel() {
        return model;
    }

    public PolicyDto setModel(String model) {
        this.model = model;
        return this;
    }

    public String getType() {
        return type;
    }

    public PolicyDto setType(String type) {
        this.type = type;
        return this;
    }

    public String getEngineSize() {
        return engineSize;
    }

    public PolicyDto setEngineSize(String engineSize) {
        this.engineSize = engineSize;
        return this;
    }

    public String getFuelType() {
        return fuelType;
    }

    public PolicyDto setFuelType(String fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public String getColour() {
        return colour;
    }

    public PolicyDto setColour(String colour) {
        this.colour = colour;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public PolicyDto setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public String getPetType() {
        return petType;
    }

    public PolicyDto setPetType(String petType) {
        this.petType = petType;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public PolicyDto setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public PolicyDto setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public int getPetAgeYears() {
        return petAgeYears;
    }

    public PolicyDto setPetAgeYears(int petAgeYears) {
        this.petAgeYears = petAgeYears;
        return this;
    }

    public int getPetAgeMonths() {
        return petAgeMonths;
    }

    public PolicyDto setPetAgeMonths(int petAgeMonths) {
        this.petAgeMonths = petAgeMonths;
        return this;
    }

    public String getPedigree() {
        return pedigree;
    }

    public PolicyDto setPedigree(String pedigree) {
        this.pedigree = pedigree;
        return this;
    }

    public String getHasMedicalConditions() {
        return hasMedicalConditions;
    }

    public PolicyDto setHasMedicalConditions(String hasMedicalConditions) {
        this.hasMedicalConditions = hasMedicalConditions;
        return this;
    }

    public String getLevelOfCover() {
        return levelOfCover;
    }

    public PolicyDto setLevelOfCover(String levelOfCover) {
        this.levelOfCover = levelOfCover;
        return this;
    }
}

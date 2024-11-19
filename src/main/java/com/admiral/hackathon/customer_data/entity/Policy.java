package com.admiral.hackathon.customer_data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "policies")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_sequence")
    @SequenceGenerator(name = "policy_sequence", sequenceName = "policies_seq", allocationSize = 1, initialValue = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @Column(name = "policy_type")
    private String policyType;

    @Column(name = "policy_number")
    private String policyNumber;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "make")
    private String make;

    @Column(name = "year_of_manufacture")
    private int yearOfManufacture;

    @Column(name = "model")
    private String model;

    @Column(name = "motor_type")
    private String type;

    @Column(name = "engine_size")
    private String engineSize;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "colour")
    private String colour;
    
    @Column(name = "pet_name")
    private String petName;

    @Column(name = "pet_type")
    private String petType;

    @Column(name = "gender")
    private String gender;

    @Column(name = "breed")
    private String breed;

    @Column(name = "pet_age_years")
    private int petYears;

    @Column(name = "pet_age_months")
    private int petMonths;

    @Column(name = "pedigree")
    private String pedigree;

    @Column(name = "medical_conditions")
    private String medicalConditions;

    public Long getId() {
        return id;
    }

    public Policy setId(Long id) {
        this.id = id;
        return this;
    }

    public Account getAccount() {
        return account;
    }

    public Policy setAccount(Account account) {
        this.account = account;
        return this;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public Policy setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
        return this;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Policy setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    public String getMake() {
        return make;
    }

    public Policy setMake(String make) {
        this.make = make;
        return this;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public Policy setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Policy setModel(String model) {
        this.model = model;
        return this;
    }

    public String getType() {
        return type;
    }

    public Policy setType(String type) {
        this.type = type;
        return this;
    }

    public String getEngineSize() {
        return engineSize;
    }

    public Policy setEngineSize(String engineSize) {
        this.engineSize = engineSize;
        return this;
    }

    public String getFuelType() {
        return fuelType;
    }

    public Policy setFuelType(String fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public String getColour() {
        return colour;
    }

    public Policy setColour(String colour) {
        this.colour = colour;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public Policy setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public String getPetType() {
        return petType;
    }

    public Policy setPetType(String petType) {
        this.petType = petType;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Policy setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public Policy setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public int getPetYears() {
        return petYears;
    }

    public Policy setPetYears(int petYears) {
        this.petYears = petYears;
        return this;
    }

    public int getPetMonths() {
        return petMonths;
    }

    public Policy setPetMonths(int petMonths) {
        this.petMonths = petMonths;
        return this;
    }

    public String getPedigree() {
        return pedigree;
    }

    public Policy setPedigree(String pedigree) {
        this.pedigree = pedigree;
        return this;
    }

    public String getMedicalConditions() {
        return medicalConditions;
    }

    public Policy setMedicalConditions(String medicalConditions) {
        this.medicalConditions = medicalConditions;
        return this;
    }

    public String getPolicyType() {
        return policyType;
    }

    public Policy setPolicyType(String policyType) {
        this.policyType = policyType;
        return this;
    }
}

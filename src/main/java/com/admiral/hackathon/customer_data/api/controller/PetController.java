package com.admiral.hackathon.customer_data.api.controller;

import com.admiral.hackathon.customer_data.api.dto.MotorDto;
import com.admiral.hackathon.customer_data.api.dto.PetDto;
import com.admiral.hackathon.customer_data.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/pet")
public class PetController {

    private final PolicyService policyService;

    @Autowired
    public PetController(@Qualifier(value = "pet") PolicyService policyService) {
        this.policyService = policyService;
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<?> savePolicies(@RequestBody List<PetDto> policies) {
        policyService.savePolicies(policies);

        return ResponseEntity.ok().build();
    }
}

package com.admiral.hackathon.customer_data.api.controller;

import com.admiral.hackathon.customer_data.api.dto.MotorDto;
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
@RequestMapping(path = "/api/motor")
public class MotorController {

    private final PolicyService policyService;

    @Autowired
    public MotorController(@Qualifier(value = "motor") PolicyService policyService) {
        this.policyService = policyService;
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<?> savePolicies(@RequestBody List<MotorDto> policies) {
        policyService.savePolicies(policies);

        return ResponseEntity.ok().build();
    }
}

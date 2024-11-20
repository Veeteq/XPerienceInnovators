package com.admiral.hackathon.customer_data.api.controller;

import com.admiral.hackathon.customer_data.api.dto.data.AccountDto;
import com.admiral.hackathon.customer_data.service.AccountService;
import com.admiral.hackathon.customer_data.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/customer")
public class CustomerController {

    private CustomerDataService customerDataService;

    @Autowired
    public CustomerController(CustomerDataService customerDataService) {
        this.customerDataService = customerDataService;
    }

    @GetMapping(path = {"", "/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCustomerData(@RequestParam(name = "email", required = true) String email) {
        var result = customerDataService.getCustomerData(email);
        return ResponseEntity.ok(result);
    }
}
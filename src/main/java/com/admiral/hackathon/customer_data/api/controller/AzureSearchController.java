package com.admiral.hackathon.customer_data.api.controller;

import com.admiral.hackathon.customer_data.api.dto.ChatRequestDto;
import com.admiral.hackathon.customer_data.service.AzureSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping(path = "/api/chat")
public class AzureSearchController {

    private final AzureSearchService azureSearchService;

    @Autowired
    public AzureSearchController(AzureSearchService azureSearchService) {
        this.azureSearchService = azureSearchService;
    }

    @PostMapping
    public ResponseEntity<String> sendChatPrompt(@RequestBody ChatRequestDto request) {
        var response = azureSearchService.sendChatPrompt(request);
        //String response = "this was your prompt: " + prompt;
        return ResponseEntity.ok(response.choices.get(0).message.content);
    }
}

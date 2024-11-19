package com.admiral.hackathon.customer_data.api.controller;

import com.admiral.hackathon.customer_data.api.dto.ChatRequestDto;
import com.admiral.hackathon.customer_data.api.dto.chat.response.Message;
import com.admiral.hackathon.customer_data.service.AzureSearchService;
import com.azure.core.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/chat")
public class ChatController {

    private final AzureSearchService service;

    @Autowired
    public ChatController(AzureSearchService service) {
        this.service = service;
    }

    @PostMapping(path = {"", "/"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> sendPrompt(@RequestBody ChatRequestDto request) {
        var response = service.sendChatPrompt(request);
        return ResponseEntity.ok(response.choices.get(0).message);
    }
}

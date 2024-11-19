package com.admiral.hackathon.customer_data.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatRequestDto {

    @JsonProperty(value = "user")
    private String user;

    @JsonProperty(value = "prompt")
    private String prompt;

    public String getUser() {
        return user;
    }

    public ChatRequestDto setUser(String user) {
        this.user = user;
        return this;
    }

    public String getPrompt() {
        return prompt;
    }

    public ChatRequestDto setPrompt(String prompt) {
        this.prompt = prompt;
        return this;
    }
}

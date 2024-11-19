package com.admiral.hackathon.customer_data.api.dto.chat.response;

import java.util.List;

public class ChatResponse {
    public List<Choice> choices;
    public int created;
    public String id;
    public String model;
    public String object;
    public List<PromptFilterResult> prompt_filter_results;
    public String system_fingerprint;
    public Usage usage;
}


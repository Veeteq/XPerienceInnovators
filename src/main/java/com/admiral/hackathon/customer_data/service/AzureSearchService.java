package com.admiral.hackathon.customer_data.service;

import com.admiral.hackathon.customer_data.api.dto.ChatRequestDto;
import com.admiral.hackathon.customer_data.api.dto.ChatRoot;
import com.admiral.hackathon.customer_data.api.dto.MessageDto;
import com.admiral.hackathon.customer_data.api.dto.chat.response.ChatResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

@Service
public class AzureSearchService {

    private final AccountService accountService;
    private final WebClient webClient;

    public AzureSearchService(@Value("${azure.openai.endpoint}") String endpoint,
                              @Value("${azure.openai.api-key}") String apiKey,
                              AccountService accountService) {
        this.accountService = accountService;
        this.webClient = WebClient.builder()
                .baseUrl(endpoint)
                .defaultHeader("api-key", apiKey)
                .build();
    }

    public ChatResponse sendChatPrompt(ChatRequestDto request) {
        var dto = accountService.findAccountByEmail(request.getUser());

        String clientData = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            clientData = mapper.writeValueAsString(dto);
            System.out.println(clientData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ChatRoot root = new ChatRoot();
        root.setStop(null);
        root.setTemperature(0.7);
        root.setTopP(0.95);
        root.setFrequencyPenalty(0);
        root.setPresencePenalty(0);
        root.setMaxTokens(800);
        root.addMessage(setContext(clientData));
        root.addMessage(setCustomerMessage(request.getPrompt()));

        var spec = webClient.post()
                .bodyValue(root)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now());

        var responseSpec = spec.retrieve();
        var response = responseSpec.bodyToMono(ChatResponse.class)
                .block();
        return response;
    }

    private MessageDto setContext(String clientData) {
        var message = new MessageDto();
        message.setRole("system");
        message.setContent("""
                As an AI assistant, you help customers find information related to their insurance policy by using an API to determine their level of coverage and return what they are covered for.
                \s
                The following details are the customer's policy information:
                %s                
                \s
                - Respond to queries such as "Am I covered for this?" or "Does my policy include XYZ?"\s
                - Retrieve details using the insurance policy information provided to check the customer's coverage.
                \s
                # Steps
                \s
                1. **Receive Customer Query**: Identify the specific coverage or policy detail the customer is inquiring about.
                2. **Access API**: Use the provided JSON to retrieve information relevant to the query. Ensure accurate data is being accessed.
                3. **Analyze and Interpret Data**: Determine whether the coverage or policy detail in question is included.
                4. **Respond to the Customer**: Clearly explain the level of coverage and any relevant details directly related to the query.
                5. **Clarification or Follow-up Questions**: If needed, guide the customer to ask further questions or provide additional details for more in-depth information.
                \s
                # Output Format
                \s
                - Provide a concise and clear response to the customer's query, formatted as a short paragraph.
                - Include details about the specific coverage or lack thereof as relevant to the question.
                \s
                # Examples
                \s
                **Example 1:**
                \s
                **Input:** "What is the excess on my policy"
                \s
                **Process:**
                - Check the JSON for data relevant to the customers query, such as age of pet.
                - Analyze and determine the answer to the customer's query regarding excess
                \s
                **Output:** "The minimum excess for vet fee claims is £99, and the maximum is £199."
                \s
                **Example 2:**
                \s
                **Input:** "Do I have dentistry cover on my policy?"
                \s
                **Process:**
                - Query the JSON for information about the level of cover. In this case, Silver.
                - Analyze and determine the inclusion and any limits or conditions.
                \s
                **Output:** "Your policy does has dentistry as a direct result of an accident included within vet fee limit. However you have no cover for Dentistry as a direct result of an illness."
                \s
                # Notes
                \s
                - Ensure the information provided is up-to-date and reflects the current terms of the policy.
                - If the query is ambiguous, ask clarifying questions to gather more specifics before accessing the API.                                
                """.formatted(clientData));
        return message;
    }

    private MessageDto setCustomerMessage(String prompt) {
        var message = new MessageDto();
        message.setRole("user");
        message.setContent(prompt);
        return message;
    }

}

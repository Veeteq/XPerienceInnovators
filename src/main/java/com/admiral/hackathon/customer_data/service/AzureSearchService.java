package com.admiral.hackathon.customer_data.service;

import com.admiral.hackathon.customer_data.api.dto.*;
import com.admiral.hackathon.customer_data.api.dto.chat.request.*;
import com.admiral.hackathon.customer_data.api.dto.chat.response.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

@Service
public class AzureSearchService {

    @Value(value = "${azure.search.index-name}")
    private String indexName;

    private final CustomerDataService customerService;
    private final ResourceService resourceService;
    private final WebClient webClient;

    public AzureSearchService(@Value("${azure.openai.endpoint}") String endpoint,
                              @Value("${azure.openai.api-key}") String apiKey,
                              CustomerDataService customerService,
                              ResourceService resourceService) {
        this.customerService = customerService;
        this.resourceService = resourceService;
        this.webClient = WebClient.builder()
                .baseUrl(endpoint)
                .defaultHeader("api-key", apiKey)
                .build();
    }

    public ChatResponse sendChatPrompt(ChatRequestDto request) {
        String clientData = customerService.getCustomerData(request.getUser());

        System.out.println(clientData);

        ChatRootDto root = new ChatRootDto();
        root.setStop(null);
        root.setTemperature(0.7);
        root.setTopP(0.95);
        root.setFrequencyPenalty(0);
        root.setPresencePenalty(0);
        root.setMaxTokens(800);
        root.addMessage(setContext(clientData));
        root.addMessage(setCustomerMessage(request.getPrompt()));
        root.addDataSource(createDataSource(setContext(clientData).getContent()));

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

        System.out.println(response);
        return response;
    }

    private MessageDto setContext(String clientData) {
        var basePrompt = resourceService.getBasePrompt();

        var message = new MessageDto();
        message.setRole("system");
        message.setContent("""
                %1$s                
                %2$s                                            
                """.formatted(basePrompt, clientData));
        System.out.println(message.getContent());
        return message;
    }

    private MessageDto setCustomerMessage(String prompt) {
        var message = new MessageDto();
        message.setRole("user");
        message.setContent(prompt);
        return message;
    }

    private DataSourceDto createDataSource(String roleInformation) {
        var authentication = new AuthenticationDto();
        authentication.type = "api_key";
        authentication.key = "OlD4W2KoonBrWDYu6aqL6zSmWwd8Yg0JUeXIu3hfDtAzSeA1wbzt";

        var parameters = new ParametersDto();
        parameters.endpoint = "https://elephant-ai-search.search.windows.net";
        parameters.index_name = indexName;
        parameters.semantic_configuration = "default";
        parameters.query_type = "semantic";
//        parameters.fields_mapping = new FieldsMappingDto(){};
        parameters.in_scope = true;
        parameters.filter = null;
        parameters.strictness = 3;
        parameters.top_n_documents = 5;
        parameters.authentication = authentication;
        parameters.role_information = roleInformation;

        var dataSource = new DataSourceDto();
        dataSource.type = "azure_search";
        dataSource.parameters = parameters;
        return dataSource;
    }

}

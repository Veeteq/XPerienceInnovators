package com.admiral.hackathon.customer_data.service;

import com.admiral.hackathon.customer_data.api.controller.CustomerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerDataService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerDataService.class);
    private final Map<String, String> settings = new HashMap<>() {{
        put("fiona.johnson@gmail.com",    "https://expinnovators.azure-api.net/clone-673cb/clone-673cb/clone-673cb/retrievePolicyDetails");
        put("laura.smith@gmail.com",      "https://expinnovators.azure-api.net/clone-673cb/clone-673cb/retrievePolicyDetails");
        put("michael.brown@gmail.com",    "https://expinnovators.azure-api.net/retrievePolicyDetails");
        put("sam.silverthorne@gmail.com", "https://expinnovators.azure-api.net/clone-673cb/retrievePolicyDetails");
    }};
    private final WebClient webClient;

    public CustomerDataService() {
        this.webClient = WebClient.builder()
                //.baseUrl(endpoint)
                .defaultHeader("Ocp-Apim-Subscription-Key", "a8e40190ac41484ea25a1bc3b652245a")
                .defaultHeader("content-type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String getCustomerData(String email) {
        LOGGER.info("Processing request of retrieving details for customer: {}", email);

        email = email.toLowerCase();
        var url = settings.get(email);

        if (url == null) {
            var msg = MessageFormat.format("Customer not found with email: {}", email);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }

        var spec = webClient.get()
                .uri(URI.create(url))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now());

        var responseSpec = spec.retrieve();
        var response = responseSpec.bodyToMono(String.class)
                .block();

        System.out.println(response);
        return response;
    }
}

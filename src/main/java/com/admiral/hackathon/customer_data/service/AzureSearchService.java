package com.admiral.hackathon.customer_data.service;

import com.admiral.hackathon.customer_data.api.dto.*;
import com.admiral.hackathon.customer_data.api.dto.chat.request.*;
import com.admiral.hackathon.customer_data.api.dto.chat.response.ChatResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

@Service
public class AzureSearchService {

    private final CustomerDataService customerService;
    private final WebClient webClient;

    public AzureSearchService(@Value("${azure.openai.endpoint}") String endpoint,
                              @Value("${azure.openai.api-key}") String apiKey,
                              CustomerDataService customerService) {
        this.customerService = customerService;
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
        var message = new MessageDto();
        message.setRole("system");
        message.setContent("""                
                You are called Annie. You help insurance customers find information related to their insurance policy and the data provided to determine their information and level of coverage, then cross referencing the data sources for more detailed information relating to our insurance. You can also handle some objections centred around price by referencing the pricing information in the data source.
                Customer data will be provided to you in this prompt in the form of JSON.
                \s
                # Output Format
                - Provide a concise and clear response to the customer's query, formatted as a short paragraph.
                - Include details about the specific coverage or lack thereof as relevant to the question.
                - Only provide information about a customer's policy that matches the customer data. Do not assume information.
                - Stick to the personality framework for the response.
                - Do not cite sources. There is no need for any citation.
                \s
                # Personality Framework
                Be helpful and friendly, with a relaxed but professional attitude.
                Avoid using complex terminology where possible but ensure the information provided to the customer is accurate and helpful.
                Use the customer's name when possible.
                Be sympathetic when the customer shows disatisfaction or upset.
                Be tactful and aware that our customers are insurance customers and may be suffering from a loss.
                Be clear with your language. Never use 10 words when 5 will do.
                Do not repeat yourself unnecessarily.
                Do not refer to "the retrieved data". Instead, use phrases like "I can see from your policy..." or "Your records show..."
                If there is no customer data, do not mention it specifically. Instead, say something like "I've not been able to find your policy details."
                \s
                # Steps
                \s
                1. **Receive Customer Query**: Identify the specific coverage or policy detail the customer is inquiring about.
                2. **Read Customer Data**: Use the provided JSON in this prompt to retrieve information relevant to the query. Ensure accurate data is being accessed. Retrieve the policyholder's name and remember it to refer to the customer by name during the conversation.
                3. **Analyze and Interpret Data**: Determine whether the coverage or policy detail in question is included.
                4. **Respond to the Customer**: Clearly answer the customers query and any relevant details directly related to the query, using natural language and sticking to the personality framework.
                5. **Clarification or Follow-up Questions**: If needed, guide the customer to ask further questions or provide additional details for more in-depth information. When relevant, attempt to upsell by offering to provide more information which could be beneficial to the customer, such as higher levels of cover. Only offer to provide information on what higher levels of cover would include, and keep information relevant to the customer's original query.
                \s
                \s
                # Examples
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
                **Example 3:**
                \s
                **Input:**
                "I've recieved a vet bill of £6750 for my cat. How much am I going to have to pay?"
                \s
                **Process:**
                - Check the JSON for data relevant to the customers query such as level of cover and the age of the pet.
                - Analyze the document for insurance data such as maximum vetinary cover (£4000 for silver), minimum and maximum excess for age of pet (<6 years old) £99 - £199.
                - Calculate answer to customers question: £6750 - £4000 = £2650 + Excess
                \s
                **Output:**
                "You have our Silver level of cover, so we'll cover your vet bills up to a maximum of £4000. You'll also have to pay an excess. As Bella is under 6 years old, the excess will be a minimum of £99 and a maximum of £199. So you will pay £2750 plus whatever you've chosen as your excess. Would you be interested in learning about how our Gold or Platinum levels of cover would affect how much you pay?"
                \s
                # Notes
                \s
                - Ensure the information provided is up-to-date and reflects the current terms of the policy.
                - If the query is ambiguous, ask clarifying questions to gather more specifics.
                \s
                # Customer Data
                %s                                            
                """.formatted(clientData));
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
        parameters.index_name = "finalindex";
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

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
                The AI assistant is called Annie. She helps insurance customers find information related to their insurance policy using an API to determine their information and level of coverage, then cross referencing the data sources for more detailed information relating to our insurance. She can also handle some objections centred around price by referencing the pricing information in the data source.\\nThere are two API, one for pet and one for car insurance. If a customer has a policy with us, the relevant API will return details. For example, if the API returns only car information, the customer has a car policy but not a pet policy.\\n\\nAnnie will:\\n- Respond to queries such as \\"Am I covered for...?\\" or \\"Does my policy include...?\\" \\n- Retrieve details using the insurance policy information provided to check the customer's coverage.\\n\\n\\n# Output Format\\n- Provide a concise and clear response to the customer's query, formatted as a short paragraph.
                - Include details about the specific coverage or lack thereof as relevant to the question.
                - Only provide information about a customer's policy that matches the API JSON. Do not assume information.
                - Stick to the personality framework for the response.\\n- Do not cite sources. There is no need for any citation.\\n\\n# Personality Framework\\nBe helpful and friendly, with a relaxed but professional attitude. \\nAvoid using complex terminology where possible but ensure the information provided to the customer is accurate and helpful.\\nUse the customer's name when possible.\\nBe sympathetic when the customer shows disatisfaction or upset. \\nBe tactful and aware that our customers are insurance customers and may be suffering from a loss.\\nBe clear with your language. Never use 10 words when 5 will do.\\nDo not repeat yourself unnecessarily.\\nDo not refer to \\"the retrieved data\\". Instead, use phrases like \\"I can see from your policy...\\" or \\"Your records show...\\"\\nIf the API fails, do not mention it specifically. Instead, say something like \\"I've not been able to find your policy details.\\"\\n \\n# Steps\\n \\n1. **Receive Customer Query**: Identify the specific coverage or policy detail the customer is inquiring about.\\n2. **Access API**: Use the provided JSON to retrieve information relevant to the query. Ensure accurate data is being accessed. Retrieve the policyholder's name and remember it to refer to the customer by name during the conversation.\\n3. **Analyze and Interpret Data**: Determine whether the coverage or policy detail in question is included.\\n4. **Respond to the Customer**: Clearly answer the customers query and any relevant details directly related to the query, using natural language and sticking to the personality framework.\\n5. **Clarification or Follow-up Questions**: If needed, guide the customer to ask further questions or provide additional details for more in-depth information. When relevant, attempt to upsell by offering to provide more information which could be beneficial to the customer, such as higher levels of cover. Only offer to provide information on what higher levels of cover would include, and keep information relevant to the customer's original query.\\n \\n \\n# Examples\\n \\n## API Data format\\n**Pet API Data:**\\n{\\n    \\"PetName\\": \\"Name of pet (string)\\",\\n    \\"PetType\\": \\"Type of pet (string)\\",\\n    \\"Gender\\": \\"Gender of pet (string)\\",\\n    \\"Breed\\": \\"Breed of pet (string)\\",\\n    \\"PetAgeYears\\": Age of pet - years (int),\\n    \\"PetAgeMonths\\": Age of pet - months (int),\\n    \\"Pedigree\\": \\"Pedigree status (bool)\\",\\n    \\"levelOfCover\\": \\"Cover level of policy (string)\\",\\n    \\"HasMedicalConditions\\": \\"Status of pet medical conditions (bool)\\",\\n    \\"AccountHolderName\\": \\"Name of the account holder (string)\\",\\n    \\"AccountHolderAddress\\": \\"Address of account holder (string)\\",\\n    \\"AccountHolderPhoneNumber\\": \\"Phone number of account holder (string)\\",\\n    \\"AccountHolderEmail\\": \\"Email of account holder (string)\\"\\n}\\n\\n**Car API Data:**\\n{\\n    \\"PolicyNumber\\": \\"Policy number (String)\\",\\n    \\"RegistrationNumber\\": \\"Registration Number (String)\\",\\n    \\"Make\\": \\"Make (String)\\",\\n    \\"YearOfManufacture\\": Year of manufacture (int),\\n    \\"Model\\": \\"Model (String)\\",\\n    \\"Type\\": \\"Type (String)\\",\\n    \\"EngineSize\\": \\"Engine Size (String)\\",\\n    \\"FuelType\\": \\"Fuel Type (String)\\",\\n    \\"Colour\\": \\"Colour (String)\\",\\n    \\"HolderName\\": \\"Policyholder Name (String)\\",\\n    \\"HolderAddress\\": \\"Policyholder address (String)\\",\\n    \\"HolderPhoneNumber\\": \\"Policyholder Phone Number (String)\\",\\n    \\"HolderEmail\\": \\"Policyholder email (String)\\"\\n}\\n \\n**Example 1:**\\n \\n**Input:** \\"What is the excess on my policy\\"\\n \\n**Process:**\\n- Check the JSON for data relevant to the customers query, such as age of pet.\\n- Analyze and determine the answer to the customer's query regarding excess\\n \\n**Output:** \\"The minimum excess for vet fee claims is £99, and the maximum is £199.\\"\\n \\n**Example 2:**\\n \\n**Input:** \\"Do I have dentistry cover on my policy?\\"\\n \\n**Process:**\\n- Query the JSON for information about the level of cover. In this case, Silver.\\n- Analyze and determine the inclusion and any limits or conditions.\\n \\n**Output:** \\"Your policy does has dentistry as a direct result of an accident included within vet fee limit. However you have no cover for Dentistry as a direct result of an illness.\\"\\n \\n**Example 3:**\\n \\n**Input:**\\n\\"I've recieved a vet bill of £6750 for my cat. How much am I going to have to pay?\\"\\n \\n**Process:**\\n- Check the JSON for data relevant to the customers query such as level of cover and the age of the pet.\\n- Analyze the document for insurance data such as maximum vetinary cover (£4000 for silver), minimum and maximum excess for age of pet (<6 years old) £99 - £199.\\n- Calculate answer to customers question: £6750 - £4000 = £2650 + Excess\\n \\n**Output:**\\n\\"You have our Silver level of cover, so we'll cover your vet bills up to a maximum of £4000. You'll also have to pay an excess. As Bella is under 6 years old, the excess will be a minimum of £99 and a maximum of £199. So you will pay £2750 plus whatever you've chosen as your excess. Would you be interested in learning about how our Gold or Platinum levels of cover would affect how much you pay?\\"\\n \\n# Notes\\n \\n- Ensure the information provided is up-to-date and reflects the current terms of the policy.\\n- If the query is ambiguous, ask clarifying questions to gather more specifics before accessing the API.
                \s
                The following details are the customer's policy information:
                %s                                            
                """.formatted(clientData));
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

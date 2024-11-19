package com.admiral.hackathon.customer_data;

public class ChatTest {
    public static void main(String[] args) {
        String endpoint = System.getenv("https://elephantazureopenai.openai.azure.com/");
        String apiKey = System.getenv("1Su30LVlA9WsWmhlh1DaMrAYSnpjeknIfnkyzCOxg6MBxdcbPoe6JQQJ99AKACmepeSXJ3w3AAABACOGFzNP");
        String deploymentName = System.getenv("gpt-4o");
        String searchEndpoint = System.getenv("https://elephantazureopenai.openai.azure.com/");
        String searchApiKey = System.getenv("OlD4W2KoonBrWDYu6aqL6zSmWwd8Yg0JUeXIu3hfDtAzSeA1wbzt");
        String searchIndex = System.getenv("petpolcy");
/*
        if (endpoint == null || apiKey == null || deploymentName == null || searchEndpoint == null || searchApiKey == null || searchIndex == null) {
            System.err.println("Please set the required environment variables.");
            return;
        }

        OpenAIClient client = new OpenAIClientBuilder()
                .endpoint(endpoint)
                .credential(new AzureKeyCredential(apiKey))
                .buildClient();

        List<ChatRequestMessage> prompts = new ArrayList<>();
        prompts.add(new ChatRequestUserMessage("What are my available health plans?"));

        ChatCompletionsOptions options = new ChatCompletionsOptions(prompts)
                //.setPastMessages(10)
                .setMaxTokens(800)
                .setTemperature(0.7)
                .setTopP(0.95)
                .setFrequencyPenalty(0.0)
                .setPresencePenalty(0.0)
                .setAzureExtensionsOptions(List.of(
                        new AzureSearchChatExtensionConfiguration()
                                .setEndpoint(searchEndpoint)
                                .setIndexName(searchIndex)
                                .setAuthentication(new OnYourDataAPIKeyAuthenticationOptions().setKey(searchApiKey))
                ));

        ChatCompletions chatCompletions = client.getChatCompletions(deploymentName, options);

        for (ChatChoice choice : chatCompletions.getChoices()) {
            ChatResponseMessage message = choice.getMessage();
            System.out.printf("Index: %d, Chat Role: %s.%n", choice.getIndex(), message.getRole());
            System.out.println("Message:");
            System.out.println(message.getContent());
        }

 */
    }
}
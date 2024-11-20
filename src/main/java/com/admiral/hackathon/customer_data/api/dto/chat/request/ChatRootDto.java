package com.admiral.hackathon.customer_data.api.dto.chat.request;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "messages",
            "temperature",
            "top_p",
            "frequency_penalty",
            "presence_penalty",
            "max_tokens",
            "stop"
    })
    public class ChatRootDto {

        @JsonProperty("messages")
        private List<MessageDto> messages = new LinkedList<>();

        @JsonProperty("temperature")
        private Double temperature;

        @JsonProperty("top_p")
        private Double topP;

        @JsonProperty("frequency_penalty")
        private Integer frequencyPenalty;

        @JsonProperty("presence_penalty")
        private Integer presencePenalty;

        @JsonProperty("max_tokens")
        private Integer maxTokens;

        @JsonProperty("stop")
        private Object stop;

        @JsonIgnore
        private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

        @JsonProperty("data_sources")
        private List<DataSourceDto> dataSources = new LinkedList<>();

        @JsonProperty("messages")
        public List<MessageDto> getMessages() {
            return messages;
        }

        @JsonProperty("messages")
        public void addMessage(MessageDto message) {
            this.messages.add(message);
        }

        @JsonProperty("temperature")
        public Double getTemperature() {
            return temperature;
        }

        @JsonProperty("temperature")
        public void setTemperature(Double temperature) {
            this.temperature = temperature;
        }

        @JsonProperty("top_p")
        public Double getTopP() {
            return topP;
        }

        @JsonProperty("top_p")
        public void setTopP(Double topP) {
            this.topP = topP;
        }

        @JsonProperty("frequency_penalty")
        public Integer getFrequencyPenalty() {
            return frequencyPenalty;
        }

        @JsonProperty("frequency_penalty")
        public void setFrequencyPenalty(Integer frequencyPenalty) {
            this.frequencyPenalty = frequencyPenalty;
        }

        @JsonProperty("presence_penalty")
        public Integer getPresencePenalty() {
            return presencePenalty;
        }

        @JsonProperty("presence_penalty")
        public void setPresencePenalty(Integer presencePenalty) {
            this.presencePenalty = presencePenalty;
        }

        @JsonProperty("max_tokens")
        public Integer getMaxTokens() {
            return maxTokens;
        }

        @JsonProperty("max_tokens")
        public void setMaxTokens(Integer maxTokens) {
            this.maxTokens = maxTokens;
        }

        @JsonProperty("stop")
        public Object getStop() {
            return stop;
        }

        @JsonProperty("stop")
        public void setStop(Object stop) {
            this.stop = stop;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        public List<DataSourceDto> getDataSources() {
            return dataSources;
        }

        public ChatRootDto addDataSource(DataSourceDto dataSources) {
            this.dataSources.add(dataSources);
            return this;
        }
    }

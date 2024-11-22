package com.admiral.hackathon.customer_data.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ResourceService {

    @Value(value = "${azure.search.input-file}")
    private String resourceFile;

    public String getBasePrompt() {

        try {
            Resource resource = new ClassPathResource(resourceFile);
            var a = resource.getContentAsByteArray();
            var basePrompt = new String(a);
            return basePrompt;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

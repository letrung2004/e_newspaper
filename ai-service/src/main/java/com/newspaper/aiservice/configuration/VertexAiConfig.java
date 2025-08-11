package com.newspaper.aiservice.configuration;

import org.springframework.ai.vertexai.embedding.VertexAiEmbeddingConnectionDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VertexAiConfig {
    @Value("${spring.ai.vertex.ai.embedding.project-id}")
    private String projectId;

    @Value("${spring.ai.vertex.ai.embedding.location}")
    private String location;

    @Value("${spring.ai.vertex.ai.embedding.credentials-uri}")
    private String credentialsLocation;

    @Bean
    public VertexAiEmbeddingConnectionDetails vertexAiEmbeddingConnectionDetails() {
        return VertexAiEmbeddingConnectionDetails.builder()
                .projectId(projectId)
                .location(location)
                .build();
    }
}

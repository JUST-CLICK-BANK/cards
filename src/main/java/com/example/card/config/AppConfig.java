package com.example.card.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

//    @Bean
//    public ObjectMapper endpointObjectMapper() {
//        return new ObjectMapper();
//    }

}
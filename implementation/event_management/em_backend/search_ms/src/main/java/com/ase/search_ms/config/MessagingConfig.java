package com.ase.search_ms.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MessagingConfig {
    public static final String searchQueryQueue = "search_query_queue";

    @Bean
    Queue createSearchQueryQueue() {
        return new Queue(searchQueryQueue, true);
    }
}
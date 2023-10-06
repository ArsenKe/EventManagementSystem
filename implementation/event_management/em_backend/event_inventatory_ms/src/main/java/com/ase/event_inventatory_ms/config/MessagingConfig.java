package com.ase.event_inventatory_ms.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@EnableRabbit
@Configuration
public class MessagingConfig {
    public static final String searchQueryQueue = "search_query_queue";
    public static final String eventsByIdsQueue = "events_by_ids_queue";
    public static final String updateEventQueue = "update_event_queue";

    public static final String eventsByTagsIdQueue = "events_by_tags_id_queue";


    @Bean
    Queue createSearchQueryQueue() {
        return new Queue(searchQueryQueue, true);
    }
    @Bean
    Queue eventsByIdsQueue() {
        return new Queue(eventsByIdsQueue, true);
    }
    @Bean
    Queue updateEventQueue() {
        return new Queue(updateEventQueue, true);
    }

    @Bean
    Queue eventsByTagsIdQueue() {
        return new Queue(eventsByTagsIdQueue, true);
    }
}
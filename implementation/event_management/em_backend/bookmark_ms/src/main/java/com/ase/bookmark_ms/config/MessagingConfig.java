package com.ase.bookmark_ms.config;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@EnableRabbit
@Configuration
public class MessagingConfig {

    public static final String maintenanceStatusQueue = "maintenance_status_queue";
    public static final String eventsByIdsQueue = "events_by_ids_queue";
    public static final String updateEventQueue = "update_event_queue";
    public static final String bookmarkQueue = "bookmark_queue";

    @Bean
    Queue maintenanceStatusQueue() {
        return new Queue(maintenanceStatusQueue, true);
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
    Queue bookmarkQueue() {
        return new Queue(bookmarkQueue, true);
    }
}
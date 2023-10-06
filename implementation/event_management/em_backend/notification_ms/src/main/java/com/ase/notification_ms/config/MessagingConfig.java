package com.ase.notification_ms.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableRabbit
@Configuration
public class MessagingConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
    public static final String notificationToAttendanceQueryQueue = "notification_to_attendance_query_queue";

    @Bean
    Queue createNotificationToAttendanceQueryQueue() {
        return new Queue(notificationToAttendanceQueryQueue, true);
    }
}
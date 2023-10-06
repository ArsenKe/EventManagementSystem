package com.ase.attendance_ms.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableRabbit
@Configuration
public class MessagingConfig implements WebMvcConfigurer {
    public static final String notificationToAttendanceQueryQueue = "notification_to_attendance_query_queue";

    @Bean
    Queue createNotificationToAttendanceQueryQueue() {
        return new Queue(notificationToAttendanceQueryQueue, true);
    }
}
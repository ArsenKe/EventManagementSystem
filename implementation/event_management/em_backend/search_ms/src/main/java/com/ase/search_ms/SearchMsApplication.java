package com.ase.search_ms;

import com.ase.search_ms.config.MessagingConfig;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SearchMsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SearchMsApplication.class, args);
	}


}

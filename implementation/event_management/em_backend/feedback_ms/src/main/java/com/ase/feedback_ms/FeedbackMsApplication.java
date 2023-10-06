package com.ase.feedback_ms;

import com.ase.feedback_ms.entity.Feedback;
import com.ase.feedback_ms.repository.FeedbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class FeedbackMsApplication {

	private static final Logger log = LoggerFactory.getLogger(FeedbackMsApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(FeedbackMsApplication.class, args);
	}


	@Bean
	ApplicationRunner init(FeedbackRepository repository) {
		return (ApplicationArguments args) -> dataSetup(repository);
	}

	public void dataSetup(FeedbackRepository repository) {

		Feedback feedback1 = new Feedback( 3L, "i am really impressed with the speaker JOHANNES ", 4, 3, 4);
		Feedback feedback2 = new Feedback( 3L, "the event was okay ", 2, 2, 3);
		Feedback feedback3 = new Feedback( 3L, "exacly as expected, well organized event", 3, 5, 5);
		Feedback feedback4 = new Feedback( 3L, "I LIKED THE EVENT", 1, 1, 5);

		repository.save(feedback1);
		repository.save(feedback2);
		repository.save(feedback3);
		repository.save(feedback4);

		log.info("Feedbacks found with findAll():");
		log.info("-------------------------------");
		for (Feedback feedback : repository.findAll()) {
			System.out.println(feedback);
		}

//		log.info("");

//		System.out.println(repository.findByEventId(5L));

//		System.out.println(repository.findByFeedbackId(52L));


	}
}

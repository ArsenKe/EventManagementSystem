package com.ase.attendance_ms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AttendanceMsApplication {

	private static final Logger logger = LoggerFactory.getLogger(AttendanceMsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AttendanceMsApplication.class, args);
	}
}

package com.quantcast.cookie.processor.cookie.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application to process cookies & find most active cookie from logs file.
 *  author: Harish Deore
 */
@SpringBootApplication
public class CookieProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookieProcessorApplication.class, args);
	}

}

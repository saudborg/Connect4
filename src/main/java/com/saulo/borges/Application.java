package com.saulo.borges;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * This class represent the main class in Spring Boot.
 * 
 * Run this class to execute the application
 * @author sauloborges
 *
 */
@SpringBootApplication
@EnableAsync
public class Application  {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
	// criar um web service para:
	//comecar um jogo
	// input de name
	// input de onde colocar

}

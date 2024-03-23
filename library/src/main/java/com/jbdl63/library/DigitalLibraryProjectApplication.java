package com.jbdl63.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DigitalLibraryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalLibraryProjectApplication.class, args);
	}

}

package com.example.storypath_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StorypathBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(StorypathBackendApplication.class, args);
	}

}

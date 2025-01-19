package com.oficina.presence_hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PresenceHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(PresenceHubApplication.class, args);
	}

}

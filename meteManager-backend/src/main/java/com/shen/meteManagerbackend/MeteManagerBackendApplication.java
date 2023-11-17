package com.shen.meteManagerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MeteManagerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeteManagerBackendApplication.class, args);
	}

}

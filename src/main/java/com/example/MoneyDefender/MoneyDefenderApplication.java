package com.example.MoneyDefender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MoneyDefenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyDefenderApplication.class, args);
	}

}

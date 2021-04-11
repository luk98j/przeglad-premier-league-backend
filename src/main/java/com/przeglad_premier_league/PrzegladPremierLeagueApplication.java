package com.przeglad_premier_league;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PrzegladPremierLeagueApplication {
	public static void main(String[] args) {
		SpringApplication.run(PrzegladPremierLeagueApplication.class, args);
	}

}

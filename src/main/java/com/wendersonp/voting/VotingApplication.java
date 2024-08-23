package com.wendersonp.voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class VotingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingApplication.class, args);
	}

}

package com.sanbosillok.sanbosillokserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SanbosillokserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanbosillokserverApplication.class, args);
	}

}

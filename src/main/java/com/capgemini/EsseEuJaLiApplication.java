package com.capgemini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.capgemini.repositories")
@EntityScan(basePackages = "com.capgemini")
public class EsseEuJaLiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsseEuJaLiApplication.class, args);
	}

}

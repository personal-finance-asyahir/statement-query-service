package com.asyahir.statement_query_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class StatementQueryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatementQueryServiceApplication.class, args);
	}

}

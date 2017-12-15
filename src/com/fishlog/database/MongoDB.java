package com.fishlog.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;


@Configuration
public class MongoDB extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "Fishlog";
	}

	@Override
	@Bean
	public MongoClient mongo() throws Exception {
		return new MongoClient("127.0.0.1", 27017);
	}

}
package com.presente.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.presente.backend.services.DBService;

@Profile("dev")
@Configuration
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public void initDatabase() {
		if (strategy.equals("create")) {
			this.dbService.initDbDev();
		}
	}
}

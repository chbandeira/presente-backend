package com.presente.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.presente.services.DBService;
import com.presente.services.EmailService;
import com.presente.services.MockEmailService;

@Profile("dev")
@Configuration
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	@Value("${init.db.dev}")
	private String strategy;

	/**
	 * Inicializa com alguns dados para teste no desenvolvimento
	 */
	@Bean
	public void initDatabase() {
		if (strategy.equals("true")) {
			this.dbService.initDbDev();
		}
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}

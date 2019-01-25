package com.presente.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.presente.backend.domains.LogEnvioEmail;
import com.presente.backend.domains.Registro;
import com.presente.backend.repositories.LogEnvioEmailRepository;


/**
 * @author Charlles Bandeira
 *
 */
@Service
public class LogEnvioEmailService {
	
	@Autowired
	private LogEnvioEmailRepository logEnvioEmailRepository;

	public void save(Registro registro, String message) {
		String error = (message != null && message.length() > 250) ? message.substring(0, 250) : message;
		this.logEnvioEmailRepository.save(new LogEnvioEmail(registro, error));
	}

}

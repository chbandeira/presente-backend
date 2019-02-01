package com.presente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.presente.domains.LogEnvioEmail;
import com.presente.domains.Registro;
import com.presente.repositories.LogEnvioEmailRepository;


/**
 * @author Charlles Bandeira
 *
 */
@Service
public class LogEnvioEmailService {
	
	@Autowired
	private LogEnvioEmailRepository logEnvioEmailRepository;

	@Transactional
	public void save(Registro registro, String message) {
		String error = (message != null && message.length() > 250) ? message.substring(0, 250) : message;
		this.logEnvioEmailRepository.save(new LogEnvioEmail(registro, error));
	}

}

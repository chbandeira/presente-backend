package com.presente.backend.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.presente.backend.domains.Registro;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Registro registro);
	
	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Registro registro);
	
	void sendHtmlEmail(MimeMessage msg);
}

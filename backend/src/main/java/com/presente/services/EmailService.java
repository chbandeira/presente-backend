package com.presente.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.presente.domains.Registro;
import com.presente.domains.Responsavel;


/**
 * @author Charlles Bandeira
 *
 */
public interface EmailService {
	
	void sendOrderConfirmationEmail(Registro registro);
	
	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Registro registro);
	
	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Responsavel responsavel, String newPass);
}

package com.presente.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.presente.exceptions.UnexpectedErrorException;


/**
 * @author Charlles Bandeira
 *
 */
@Service
public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender javaMailSender;

	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		try {
			LOG.info("Enviando email...");
			LOG.info(msg.toString());
			this.mailSender.send(msg);
			LOG.info("Email enviado.");
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new UnexpectedErrorException("Erro ao enviar email");
		}
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		try {
			LOG.info("Enviando email HTML...");
			LOG.info(msg.toString());
			this.javaMailSender.send(msg);
			LOG.info("Email enviado.");
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new UnexpectedErrorException("Erro ao enviar email");
		}
	}

}

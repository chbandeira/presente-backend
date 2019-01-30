package com.presente.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.presente.domains.Registro;
import com.presente.domains.Responsavel;


/**
 * @author Charlles Bandeira
 *
 */
public abstract class AbstractEmailService implements EmailService {

	@Value("${spring.mail.username}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendOrderConfirmationEmail(Registro registro) {
		SimpleMailMessage sm = this.prepareSimpleMailMessageFromRegistro(registro);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromRegistro(Registro registro) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(registro.getLogAlteracaoAluno().getEmailResponsavel());
		if (registro.getLogAlteracaoAluno().getEmailResponsavel2() != null) {
			sm.setCc(registro.getLogAlteracaoAluno().getEmailResponsavel2());
		}
		sm.setFrom(sender);
		sm.setSubject("Registro de " + registro.getTipoRegistro().getDescricao() + " de aluno(a).");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(registro.toString());
		return sm;
	}

	protected String htmlFromTemplateRegistro(Registro registro) {
		Context context = new Context();
		context.setVariable("registro", registro);
		return templateEngine.process("email/confirmacaoRegistro", context);
	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Registro registro) {
		try {
			MimeMessage mm = prepareMimeMessageFromRegistro(registro);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			this.sendOrderConfirmationEmail(registro);
		}
	}

	protected MimeMessage prepareMimeMessageFromRegistro(Registro registro) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(registro.getLogAlteracaoAluno().getEmailResponsavel());
		if (registro.getLogAlteracaoAluno().getEmailResponsavel2() != null) {
			mmh.setCc(registro.getLogAlteracaoAluno().getEmailResponsavel2());
		}
		mmh.setFrom(sender);
		mmh.setSubject("Registro de " + registro.getTipoRegistro().getDescricao() + " de aluno(a).");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(this.htmlFromTemplateRegistro(registro), true);
		return mimeMessage;
	}
	
	@Override
	public void sendNewPasswordEmail(Responsavel responsavel, String newPass) {
		SimpleMailMessage sm = this.prepareSimpleMailMessageFromResponsavel(responsavel, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromResponsavel(Responsavel responsavel, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(responsavel.getEmail());
		sm.setFrom(this.sender);
		sm.setSubject("Solicitaçção de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}

}

package br.com.w2c.util.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe de envio de mensagens.
 * 
 * @author charlles
 * 
 */
public class EmailUtil {

	private static final Logger log = LogManager.getLogger();
	
	/**
	 * Envia email.
	 * 
	 * @param smtpServer
	 *            : Servidor de email. Exemplo: <b>smtp.gmail.com</b>
	 * @param assunto
	 *            : Assunto do email.
	 * @param destinatario
	 *            : Email de destino.
	 * @param remetente
	 *            : Email do emissor.
	 * @param nomeRemetente
	 *            : Nome do emissor.
	 * @param mensagem
	 *            : Mensagem a ser enviada.
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static void enviar(String smtpServer, String assunto,
			String destinatario, String remetente, String senhaRemetente,
			String nomeRemetente, String mensagem) throws UnsupportedEncodingException, MessagingException {

		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.mime.charset", "UTF-8");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getDefaultInstance(props);

		try {
			Message msg = new MimeMessage(session);

			InternetAddress enderecoDestinatario = new InternetAddress(destinatario);
			InternetAddress enderecoRemetente = new InternetAddress(remetente, nomeRemetente);

			msg.setSentDate(new Date());
			msg.setFrom(enderecoRemetente);
			msg.setRecipient(Message.RecipientType.TO, enderecoDestinatario);
			msg.setSubject(assunto);
			msg.setContent(mensagem.toString(), "text/HTML");

			Transport transport = session.getTransport("smtp");
			transport.connect(smtpServer, remetente, senhaRemetente);
			msg.saveChanges();
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();

		} catch (AddressException e) {
			log.error("Email inválido: "+destinatario, e);
			throw e;

		} catch (MessagingException e) {
			log.error("Falha no envio do email.", e);
			throw e;

		} catch (UnsupportedEncodingException e) {
			log.error("Nome do emitente do email inválido: "+remetente, e);
			throw e;
		}

	}

}

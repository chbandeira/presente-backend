package br.com.w2c.util.email;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import br.com.w2c.controller.business.ParametroGeralBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.enums.ParametroGeral;
import br.com.w2c.model.wrapper.EmailWrapper;
import br.com.w2c.util.SpringUtil;

/**
 * 
 * @author charlles
 * @since 18/10/2013
 */
public class EnviarEmail {
	
	private static ParametroGeralBO parametroGeralBO = SpringUtil.getBean("parametroGeralBO");

	/**
	 * Usar enviar(final EmailWrapper emailWrapper)<br>
	 * Por Thread
	 * @param assunto
	 * @param destinatario
	 * @param mensagem
	 * @throws AplicacaoException 
	 */
	@Deprecated
	public static void enviar(final String assunto, final String destinatario, final String mensagem) throws AplicacaoException {
		final String smtpServer = parametroGeralBO.obterValorPorChave(ParametroGeral.SMTP_EMAIL_SERVIDOR.getChave());
		final String remetente = parametroGeralBO.obterValorPorChave(ParametroGeral.EMAIL_REMETENTE.getChave());
		final String senhaRemetente = parametroGeralBO.obterValorPorChave(ParametroGeral.SENHA_EMAIL_REMETENTE.getChave());
		final String nomeRemetente = parametroGeralBO.obterValorPorChave(ParametroGeral.NOME_REMETENTE.getChave());
		
		Runnable run = new Runnable() {
			public void run() {
				try {
					EmailUtil.enviar(smtpServer, assunto, destinatario, remetente, senhaRemetente, nomeRemetente, mensagem);
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		};
		Thread enviar = new Thread(run);
		enviar.start();
	}
	
	/**
	 * Por Thread
	 * @param assunto
	 * @param destinatario
	 * @param mensagem
	 * @throws AplicacaoException 
	 */
	public static void enviarThread(final EmailWrapper emailWrapper) throws AplicacaoException {
		final String smtpServer = parametroGeralBO.obterValorPorChave(ParametroGeral.SMTP_EMAIL_SERVIDOR.getChave());
		final String remetente = parametroGeralBO.obterValorPorChave(ParametroGeral.EMAIL_REMETENTE.getChave());
		final String senhaRemetente = parametroGeralBO.obterValorPorChave(ParametroGeral.SENHA_EMAIL_REMETENTE.getChave());
		final String nomeRemetente = parametroGeralBO.obterValorPorChave(ParametroGeral.NOME_REMETENTE.getChave());
		
		Runnable run = new Runnable() {
			public void run() {
				try {
					EmailUtil.enviar(smtpServer, emailWrapper.getAssunto(),
							emailWrapper.getDestinatario(), remetente,
							senhaRemetente, nomeRemetente,
							emailWrapper.getMensagem());
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		};
		Thread enviar = new Thread(run);
		enviar.start();
	}
	
	/**
	 * Sem Thread
	 * @param emailWrappers
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 * @throws AplicacaoException 
	 */
	public static void enviarEmail(EmailWrapper emailWrapper) throws MessagingException, UnsupportedEncodingException, AplicacaoException {
		
		String smtpServer = parametroGeralBO.obterValorPorChave(ParametroGeral.SMTP_EMAIL_SERVIDOR.getChave());
		String remetente = parametroGeralBO.obterValorPorChave(ParametroGeral.EMAIL_REMETENTE.getChave());
		String senhaRemetente = parametroGeralBO.obterValorPorChave(ParametroGeral.SENHA_EMAIL_REMETENTE.getChave());
		String nomeRemetente = parametroGeralBO.obterValorPorChave(ParametroGeral.NOME_REMETENTE.getChave());
		
		EmailUtil.enviar(smtpServer, emailWrapper.getAssunto(),
				emailWrapper.getDestinatario(), remetente, senhaRemetente,
				nomeRemetente, emailWrapper.getMensagem());
	}
	
}

package br.com.w2c.model.wrapper;

/**
 * 
 * @author Charlles
 * @since 24/03/2015
 */
public class SmsEmailWrapper {

	private String assunto;
	private String mensagem;
	private String destinatario;
	private boolean enviarSMS;
	private boolean enviarEmail;
	private String mensagemDetalhes;
	
	public SmsEmailWrapper(String assunto, String mensagem, String destinatario) {
		super();
		this.assunto = assunto;
		this.mensagem = mensagem;
		this.destinatario = destinatario;
	}
	
	public SmsEmailWrapper(String mensagem) {
		super();
		this.mensagem = mensagem;
	}
	
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public EmailWrapper getEmailWrapper() {
		return new EmailWrapper(assunto, mensagemDetalhes != null ? mensagemDetalhes : mensagem, destinatario);
	}

	public boolean isEnviarSMS() {
		return enviarSMS;
	}

	public void setEnviarSMS(boolean enviarSMS) {
		this.enviarSMS = enviarSMS;
	}

	public boolean isEnviarEmail() {
		return enviarEmail;
	}

	public void setEnviarEmail(boolean enviarEmail) {
		this.enviarEmail = enviarEmail;
	}

	public String getMensagemDetalhes() {
		return mensagemDetalhes;
	}

	public void setMensagemDetalhes(String mensagemDetalhes) {
		this.mensagemDetalhes = mensagemDetalhes;
	}
}

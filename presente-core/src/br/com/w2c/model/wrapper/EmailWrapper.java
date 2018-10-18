package br.com.w2c.model.wrapper;

/**
 * 
 * @author Charlles
 * @since
 */
public class EmailWrapper {

	private String assunto;
	private String mensagem;
	private String destinatario;
	
	public EmailWrapper(String assunto, String mensagem, String destinatario) {
		super();
		this.assunto = assunto;
		this.mensagem = mensagem;
		this.destinatario = destinatario;
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
	
}

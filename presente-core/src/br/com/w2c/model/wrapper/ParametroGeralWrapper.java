package br.com.w2c.model.wrapper;

import java.io.Serializable;

/**
 * 
 * @author charlles
 * @since 20/10/2013
 */
public class ParametroGeralWrapper implements Serializable {

	private static final long serialVersionUID = -498866669364577805L;
	
	private Integer anoLetivo;
	private String nomeRemetente;
	private String senhaEmailRemetente;
	private String emailRemetente;
	private String smtpEmailServidor;

	public Integer getAnoLetivo() {
		return anoLetivo;
	}
	public void setAnoLetivo(Integer anoLetivo) {
		this.anoLetivo = anoLetivo;
	}
	public String getNomeRemetente() {
		return nomeRemetente;
	}
	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}
	public String getSenhaEmailRemetente() {
		return senhaEmailRemetente;
	}
	public void setSenhaEmailRemetente(String senhaEmailRemetente) {
		this.senhaEmailRemetente = senhaEmailRemetente;
	}
	public String getEmailRemetente() {
		return emailRemetente;
	}
	public void setEmailRemetente(String emailRemetente) {
		this.emailRemetente = emailRemetente;
	}
	public String getSmtpEmailServidor() {
		return smtpEmailServidor;
	}
	public void setSmtpEmailServidor(String smtpEmailServidor) {
		this.smtpEmailServidor = smtpEmailServidor;
	}
	@Override
	public String toString() {
		return "ParametroGeralWrapper [anoLetivo=" + anoLetivo
				+ ", nomeRemetente=" + nomeRemetente + ", senhaEmailRemetente="
				+ senhaEmailRemetente + ", emailRemetente=" + emailRemetente
				+ ", smtpEmailServidor=" + smtpEmailServidor + "]";
	}

}

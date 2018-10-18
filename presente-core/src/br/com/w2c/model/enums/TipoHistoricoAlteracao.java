package br.com.w2c.model.enums;

/**
 * 
 * @author Charlles
 * @since 19/01/2014
 */
public enum TipoHistoricoAlteracao {

	INCLUSAO("INCLUSAO"),
	ALTERACAO("ALTERACAO"),
	EXCLUSAO("EXCLUSAO"),
	DESATIVACAO("DESATIVACAO");
	
	private String tipo;

	TipoHistoricoAlteracao(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
}

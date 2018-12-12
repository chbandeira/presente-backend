package com.presente.backend.domains.enums;

/**
 * 
 * @author Charlles
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

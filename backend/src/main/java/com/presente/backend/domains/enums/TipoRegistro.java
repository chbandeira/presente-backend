package com.presente.backend.domains.enums;

/**
 * @author charlles
 * @since 01/09/2013
 */
public enum TipoRegistro {

	ENTRADA("Entrada"),
	SAIDA("Saída");
	
	private String descricao;

	TipoRegistro(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}

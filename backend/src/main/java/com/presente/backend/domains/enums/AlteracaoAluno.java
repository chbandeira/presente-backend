package com.presente.backend.domains.enums;

/**
 * 
 * @author Charlles
 */
public enum AlteracaoAluno {

	INCLUSAO("INCLUSAO"),
	ALTERACAO("ALTERACAO");
	
	private String tipo;

	AlteracaoAluno(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
}

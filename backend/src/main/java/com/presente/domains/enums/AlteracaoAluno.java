package com.presente.domains.enums;

/**
 * 
 * @author Charlles
 */
public enum AlteracaoAluno {

	INCLUSAO("INCLUSAO"),
	ALTERACAO("ALTERACAO");

	private String descricao;

	AlteracaoAluno(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}

package com.presente.backend.domains.enums;

/**
 * @author charlles
 */
public enum Turno {
	
	MANHA("M","Matutino"),
	TARDE("V","Vespertino"),
	NOITE("N","Noturno");
	
	private String sigla;
	private String descricao;

	Turno(String sigla, String descricao) {
		this.sigla = sigla;
		this.descricao = descricao;
	}
	
	public String getSigla() {
		return sigla;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Turno toEnum(String sigla) {
		for (Turno turno : Turno.values()) {
			if (turno.getSigla().equals(sigla)) {
				return turno;
			}
		}
		return null;
	}
	
}

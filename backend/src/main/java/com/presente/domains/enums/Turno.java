package com.presente.domains.enums;

/**
 * @author charlles
 */
public enum Turno {
	
	MANHA("Matutino"),
	TARDE("Vespertino"),
	NOITE("Noturno");
	
	private String descricao;

	Turno(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Turno toEnum(int ordinal) {
		for (Turno turno : Turno.values()) {
			if (turno.ordinal() == ordinal) {
				return turno;
			}
		}
		return null;
	}
	
	public static Turno toEnum(String ordinal) {
		for (Turno turno : Turno.values()) {
			if (turno.ordinal() == Integer.parseInt(ordinal)) {
				return turno;
			}
		}
		return null;
	}
	
}

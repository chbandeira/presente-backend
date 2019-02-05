package com.presente.domains.enums;

public enum TipoTelefone {
	
	CELULAR("Celular"), FIXO("Fixo");
	
	private String descricao;
	
	private TipoTelefone(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoTelefone toEnum(Integer ordinal) {
		for (TipoTelefone tipo : TipoTelefone.values()) {
			if (tipo.ordinal() == ordinal) {
				return tipo;
			}
		}
		return null;
	}

}

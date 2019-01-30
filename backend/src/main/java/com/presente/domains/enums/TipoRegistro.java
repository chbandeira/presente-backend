package com.presente.domains.enums;

/**
 * @author charlles
 * @since 01/09/2013
 */
public enum TipoRegistro {

	ENTRADA(1, "Entrada"), SAIDA(2, "Saída");

	private Integer cod;

	private String descricao;

	TipoRegistro(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public static TipoRegistro toEnum(Integer tipo) {
		for (TipoRegistro tr : TipoRegistro.values()) {
			if (tr.getCod().equals(tipo)) {
				return tr;
			}
		}
		return null;
	}

}

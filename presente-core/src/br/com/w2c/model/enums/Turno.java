package br.com.w2c.model.enums;

/**
 * @author charlles
 * @since 01/09/2013
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
	
}

package br.com.w2c.model.enums;

/**
 * 
 * @author Charlles
 * @since 31/01/2016
 */
public enum StatusAtualizacaoWeb {

	ATUALIZADO("ATUALIZADO"),
	ATUALIZANDO("ATUALIZANDO"),
	ERRO("ERRO");
	
	private String valor;

	private StatusAtualizacaoWeb(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
}

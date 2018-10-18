package br.com.w2c.model.enums;

/**
 * 
 * @author charlles
 * @since 26/11/2013
 */
public enum StatusCalendarioEscolar {

	INCLUIR(1L),
	EXCLUIR(2L);
	
	private Long status;
	
	StatusCalendarioEscolar(Long status) {
		this.status = status;
	}
	
	public Long getStatus() {
		return status;
	}
}


package com.presente.backend.domains.enums;

/**
 * 
 * @author charlles
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


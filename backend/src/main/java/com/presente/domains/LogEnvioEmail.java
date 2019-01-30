package com.presente.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LogEnvioEmail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String logError;
	
	@ManyToOne
	@JoinColumn(name = "registro_id")
	private Registro registro;

	public LogEnvioEmail() {
	}
	
	public LogEnvioEmail(Registro registro, String logError) {
		this.registro = registro;
		this.logError = logError;
	}

	public String getLogError() {
		return logError;
	}

	public void setLogError(String logError) {
		this.logError = logError;
	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

}

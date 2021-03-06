package com.presente.dto;

import java.io.Serializable;

public class RegistroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer tipoRegistro;
	private String matricula;
	private String messageRetorno;
	private String urlFoto;

	public Integer getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(Integer tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMessageRetorno() {
		return messageRetorno;
	}

	public void setMessageRetorno(String messageRetorno) {
		this.messageRetorno = messageRetorno;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

}

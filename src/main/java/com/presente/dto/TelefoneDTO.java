package com.presente.dto;

import java.io.Serializable;

import com.presente.domains.Telefone;

public class TelefoneDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String telefone;

	private Integer tipo;

	private String descricao;
	
	public TelefoneDTO() {
	}

	public TelefoneDTO(Telefone telefone) {
		this.telefone = telefone.getId().getTelefone();
		this.descricao = telefone.getDescricao();
		this.tipo = telefone.getTipo().ordinal();
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

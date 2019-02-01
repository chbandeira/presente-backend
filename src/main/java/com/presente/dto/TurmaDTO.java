package com.presente.dto;

import java.io.Serializable;

import com.presente.domains.Turma;

public class TurmaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String descricao;

	private String serie;

	private String sala;

	private Integer turno;

	public TurmaDTO() {
	}

	public TurmaDTO(Turma turma) {
		this.id = turma.getId();
		this.descricao = turma.getDescricao();
		this.sala = turma.getSala();
		this.serie = turma.getSerie();
		if (turma.getTurno() != null) {			
			this.turno = turma.getTurno().ordinal();
		}
	}

	public TurmaDTO(String descricao, String serie, String sala, Integer turno) {
		super();
		this.descricao = descricao;
		this.serie = serie;
		this.sala = sala;
		this.turno = turno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public Integer getTurno() {
		return turno;
	}

	public void setTurno(Integer turno) {
		this.turno = turno;
	}

}

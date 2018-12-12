package com.presente.backend.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.presente.backend.domains.enums.Turno;

/**
 * @author charlles
 */
@Entity
@Table(name="turma")
public class Turma extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_turma")
	private Long id;
	
	@Column(name="str_descricao", nullable=false)
	private String descricao;
	
	@Column(name="bol_ativo", columnDefinition="boolean default true", insertable=false, updatable=true)
	private Boolean ativo;
	
	@Transient
	private Serie serie;
	
	@Transient
	private Integer anoLetivo;
	
	@Transient
	private Turno turno;
	
	public Turma(){}
	
	public Turma(Long id, String descricao, Boolean ativo, Serie serie,
			Integer anoLetivo, Turno turno) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.ativo = ativo;
		this.serie = serie;
		this.anoLetivo = anoLetivo;
		this.turno = turno;
	}

	public Turma(Turma turma, Matricula matricula) {
		super();
		this.id = turma.getId();
		this.descricao = turma.getDescricao();
		this.ativo = matricula.getAtivo();
		this.serie = matricula.getSerie();
		this.anoLetivo = matricula.getAnoLetivo();
		this.turno = matricula.getTurno();
	}

	public Turma(Turma turma, String serie, String turno, Integer anoLetivo) {
		super();
		this.id = turma.getId();
		this.descricao = turma.getDescricao();
		this.serie = new Serie(serie);
		this.turno = Turno.valueOf(turno);
		this.anoLetivo = anoLetivo;
	}
	
	public Turma(String descricao) {
		this.descricao = descricao;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turma other = (Turma) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Turma [id=" + id + ", descricao=" + descricao + ", ativo="
				+ ativo + "]";
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Integer getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(Integer anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
}

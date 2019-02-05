package com.presente.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.presente.domains.enums.TipoTelefone;

@Entity
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TelefoneResponsavelPK id = new TelefoneResponsavelPK();

	@Enumerated(EnumType.ORDINAL)
	private TipoTelefone tipo;

	@Column(length = 20)
	private String descricao;

	public Telefone() {
	}

	public Telefone(Integer tipo, String telefone, String descricao) {
		this.id.setTelefone(telefone);
		this.tipo = TipoTelefone.toEnum(tipo);
		this.descricao = descricao;
	}
	
	public String getTelefone() {
		return id.getTelefone();
	}

	public void setTelefone(String telefone) {
		this.id.setTelefone(telefone);
	}
	public TipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(TipoTelefone tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TelefoneResponsavelPK getId() {
		return id;
	}

	public void setId(TelefoneResponsavelPK id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Telefone other = (Telefone) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

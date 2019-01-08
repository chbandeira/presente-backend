package com.presente.backend.domains;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.presente.backend.domains.enums.TipoRegistro;

/**
 * 
 * @author Charlles
 */
@Entity
public class Registro extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "historico_alteracao_aluno_id", nullable = false)
	private HistoricoAlteracaoAluno historicoAlteracaoAluno;

	@Enumerated(EnumType.ORDINAL)
	private TipoRegistro tipoRegistro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoRegistro getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(TipoRegistro tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public HistoricoAlteracaoAluno getHistoricoAlteracaoAluno() {
		return historicoAlteracaoAluno;
	}

	public void setHistoricoAlteracaoAluno(HistoricoAlteracaoAluno historicoAlteracaoAluno) {
		this.historicoAlteracaoAluno = historicoAlteracaoAluno;
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
		Registro other = (Registro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

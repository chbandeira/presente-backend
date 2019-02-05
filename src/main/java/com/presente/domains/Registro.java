package com.presente.domains;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.presente.domains.enums.TipoRegistro;

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
	@JoinColumn(name = "LOG_ALTERACAO_ALUNO_ID", nullable = false)
	private LogAlteracaoAluno logAlteracaoAluno;

	@Enumerated(EnumType.ORDINAL)
	private TipoRegistro tipoRegistro;

	public Registro() {
	}

	public Registro(LogAlteracaoAluno logAlteracaoAluno, TipoRegistro tipoRegistro, Date data) {
		this.logAlteracaoAluno = logAlteracaoAluno;
		this.tipoRegistro = tipoRegistro;
		setDataUltimaAtualizacao(data);
	}

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

	public LogAlteracaoAluno getLogAlteracaoAluno() {
		return logAlteracaoAluno;
	}

	public void setLogAlteracaoAluno(LogAlteracaoAluno logAlteracaoAluno) {
		this.logAlteracaoAluno = logAlteracaoAluno;
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

	@Override
	public String toString() {
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));
		DateFormat tf = DateFormat.getTimeInstance(DateFormat.FULL, new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(this.logAlteracaoAluno.getNome());
		builder.append(" registrou ").append(this.tipoRegistro.getDescricao());
		builder.append(" em ").append(df.format(this.getDataUltimaAtualizacao()));
		builder.append(" às ").append(tf.format(this.getDataUltimaAtualizacao()));
		return builder.toString();
	}

}

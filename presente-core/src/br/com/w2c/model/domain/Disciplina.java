package br.com.w2c.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Charlles
 * @since 05/05/2015
 */
@NamedQueries({
	@NamedQuery(name="Disciplina.obterRepetido", query="select d from Disciplina d where upper(d.nome) = upper(:nome) and d.id != :id and d.ativo = true"),
	@NamedQuery(name="Disciplina.obterListaPorParametros", query="select d from Disciplina d where upper(d.nome) like upper(:nome) and d.ativo = true")
})
@Entity
@Table(name="disciplina")
public class Disciplina extends BaseEntity {

	private static final long serialVersionUID = 8696378397874017688L;
	
	@Id
	@SequenceGenerator(sequenceName="seq_disciplina", name="seq_disciplina")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_disciplina")
	@Column(name="id_disciplina")
	private Long id;
	
	@Column(name="str_nome")
	private String nome;
	
	@Column(name="num_carga_horaria")
	private Integer cargaHoraria;
	
	@Column(name="bol_ativo", columnDefinition="boolean default true", insertable=false, updatable=true)
	private Boolean ativo;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result
				+ ((cargaHoraria == null) ? 0 : cargaHoraria.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Disciplina other = (Disciplina) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (cargaHoraria == null) {
			if (other.cargaHoraria != null)
				return false;
		} else if (!cargaHoraria.equals(other.cargaHoraria))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Disciplina [id=" + id + ", nome=" + nome + ", cargaHoraria="
				+ cargaHoraria + ", ativo=" + ativo + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
}

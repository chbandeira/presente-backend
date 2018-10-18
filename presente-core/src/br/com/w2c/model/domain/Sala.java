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
 * @author charlles
 * @since 04/09/2013
 */
@NamedQueries({
	@NamedQuery(name="Sala.obterListaPorParametros", query="select s from Sala s where upper(s.descricao) like upper(:descricao) and s.ativo = true"),
	@NamedQuery(name="Sala.obterListaPorSugestaoDescricao", query="select s from Sala s where upper(s.descricao) like upper(:descricao) and s.ativo = true"),
	@NamedQuery(name="Sala.obterPorParametrosUnicos", query="select s from Sala s where upper(s.descricao) = upper(:descricao) and s.ativo = true"),
	@NamedQuery(name="Sala.obterRepetido", query="select s from Sala s where upper(s.descricao) = upper(:descricao) and s.id != :id and s.ativo = true")
})
@Entity
@Table(name="sala")
public class Sala extends BaseEntity {

	private static final long serialVersionUID = -6498440549638030996L;

	@Id
	@SequenceGenerator(name="seq_sala", sequenceName="seq_sala")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_sala")
	@Column(name="id_sala")
	private Long id;
	
	@Column(name="str_descricao", nullable=false)
	private String descricao;
	
	@Column(name="bol_ativo", columnDefinition="boolean default true", insertable=false, updatable=true)
	private Boolean ativo;
	
	public Sala() {
		
	}
	
	public Sala(String descricao) {
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
		Sala other = (Sala) obj;
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
		return "Sala [id=" + id + ", descricao=" + descricao + ", ativo="
				+ ativo + "]";
	}
	
}

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

@NamedQueries({
	@NamedQuery(name="TipoOcorrencia.obterListaPorParametros", query="select to from TipoOcorrencia to where upper(to.descricao) like upper(:descricao) and to.ativo = true"),
	@NamedQuery(name="TipoOcorrencia.obterListaPorSugestaoDescricao", query="select to from TipoOcorrencia to where upper(to.descricao) like upper(:descricao) and to.ativo = true"),
	@NamedQuery(name="TipoOcorrencia.obterPorDescricao", query="select to from TipoOcorrencia to where upper(to.descricao) = upper(:descricao) and to.ativo = true"),
	@NamedQuery(name="TipoOcorrencia.obterListaPorSugestaoDescricaoEUsuario", query="select to from TipoOcorrencia to where upper(to.descricao) like upper(:descricao) and to.ativo = true and to.identificador = :identificador")
})
@Entity
@Table(name="tipo_ocorrencia")
public class TipoOcorrencia extends BaseEntity {

	private static final long serialVersionUID = -3532313910285061961L;

	@Id
	@SequenceGenerator(sequenceName="seq_tipo_ocorrencia", name="seq_tipo_ocorrencia")
	@GeneratedValue(generator="seq_tipo_ocorrencia", strategy=GenerationType.AUTO)
	@Column(name="id_tipo_ocorrencia")
	private Long id;
	
	@Column(name="str_descricao")
	private String descricao;

	@Column(name="bol_ativo", columnDefinition="boolean default true", insertable=false, updatable=true)
	private Boolean ativo;

	public TipoOcorrencia() {
	}
	
	public TipoOcorrencia(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "TipoOcorrencia [id=" + id + ", descricao=" + descricao
				+ ", ativo=" + ativo + "]";
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
		TipoOcorrencia other = (TipoOcorrencia) obj;
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
}

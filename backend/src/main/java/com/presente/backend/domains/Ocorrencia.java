package com.presente.backend.domains;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Charlles
 */
@Entity
@Table(name="ocorrencia")
public class Ocorrencia extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ocorrencia")
	private Long id;
	
	@Column(name="tmp_data")
	private Timestamp data;
	
	/**
	 * Data automatica
	 */
	@Column(name="tmp_geracao")
	private Timestamp dataGeracao;
	
	@Column(name="num_dias")
	private Integer qtdDias;
	
	@Column(name="str_descricao")
	private String descricao;

	@ManyToOne
	@JoinColumn(name="id_matricula", nullable=false)
	private Matricula matricula;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_ocorrencia", nullable=false)
	private TipoOcorrencia tipo;
	
	/**
	 * Responsavel pela ocorrencia
	 */
	@ManyToOne
	@JoinColumn(name="str_login", nullable=false)
	private Usuario usuario;
	
	@Column(name="bol_ativo", columnDefinition="boolean default true", insertable=false, updatable=true)
	private Boolean ativo;

	public Ocorrencia() {
	}

	public Ocorrencia(Timestamp dataAtualTimestamp) {
		this.data = dataAtualTimestamp;
	}

	public Ocorrencia(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public Timestamp getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Timestamp dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public Integer getQtdDias() {
		return qtdDias;
	}

	public void setQtdDias(Integer qtdDias) {
		this.qtdDias = qtdDias;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public TipoOcorrencia getTipo() {
		return tipo;
	}

	public void setTipo(TipoOcorrencia tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Ocorrencia [id=" + id + ", data=" + data + ", dataGeracao="
				+ dataGeracao + ", qtdDias=" + qtdDias + ", descricao="
				+ descricao + ", matricula=" + matricula + ", tipo=" + tipo
				+ ", usuario=" + usuario + ", ativo=" + ativo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((dataGeracao == null) ? 0 : dataGeracao.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((qtdDias == null) ? 0 : qtdDias.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Ocorrencia other = (Ocorrencia) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (dataGeracao == null) {
			if (other.dataGeracao != null)
				return false;
		} else if (!dataGeracao.equals(other.dataGeracao))
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
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (qtdDias == null) {
			if (other.qtdDias != null)
				return false;
		} else if (!qtdDias.equals(other.qtdDias))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}

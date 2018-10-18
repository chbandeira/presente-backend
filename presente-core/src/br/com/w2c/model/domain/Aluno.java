package br.com.w2c.model.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.chbandeira.utilitario.annotation.ExclusionStrategy;

/**
 * @author charlles
 * @since 01/09/2013
 */
@Entity
@Table(name="aluno")
public class Aluno extends BaseEntity {

	private static final long serialVersionUID = 7298327112984431256L;
	
	@Id
	@SequenceGenerator(name="seq_aluno", sequenceName="seq_aluno")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_aluno")
	@Column(name="id_aluno")
	private Long id;
	
	@Column(name="str_nome", nullable=false)
	private String nome;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_nascimento")
	private Date dataNascimento;
	
	@ExclusionStrategy
	@OneToMany(mappedBy="aluno")
	private List<Matricula> listaMatricula;
	
	@Column(name="bol_ativo", columnDefinition="boolean default true", insertable=false, updatable=true)
	private Boolean ativo;
	
	@Column
	private byte[] foto;

	public Aluno() {}
	
	public Aluno(String nome) {
		this.nome = nome;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Matricula> getListaMatricula() {
		return listaMatricula;
	}

	public void setListaMatricula(List<Matricula> listaMatricula) {
		this.listaMatricula = listaMatricula;
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
		Aluno other = (Aluno) obj;
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
		return "Aluno [id=" + id + ", nome=" + nome + ", dataNascimento="
				+ dataNascimento + ", ativo=" + ativo + "]";
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
}

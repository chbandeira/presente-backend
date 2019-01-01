package com.presente.backend.domains;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author charlles
 */
@Entity
public class Aluno extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String nome;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Column
	private String urlFoto;

	private String matricula;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "turma_id")
	private Turma turma;

	private Integer anoLetivo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp DEFAULT now()", insertable = true, updatable = true, nullable = false)
	private Date dataMatricula;

	@Column(columnDefinition = "boolean default false", insertable = true, updatable = true, nullable = false)
	private Boolean bolsista;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "responsavel_id")
	private Responsavel responsavel;

	@Column(columnDefinition = "boolean default true", insertable = true, updatable = true, nullable = false)
	private Boolean enviarEmailRegistro;
	
	@Column(columnDefinition = "boolean default true", insertable = true, updatable = true, nullable = false)
	private Boolean enviarMensagem;

	@Column(columnDefinition = "boolean default true", insertable = true, updatable = true, nullable = false)
	private Boolean ativo;
	
	public Aluno() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Integer getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(Integer anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public Boolean getBolsista() {
		return bolsista;
	}

	public void setBolsista(Boolean bolsista) {
		this.bolsista = bolsista;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Boolean getEnviarEmailRegistro() {
		return enviarEmailRegistro;
	}

	public void setEnviarEmailRegistro(Boolean enviarEmailRegistro) {
		this.enviarEmailRegistro = enviarEmailRegistro;
	}

	public Boolean getEnviarMensagem() {
		return enviarMensagem;
	}

	public void setEnviarMensagem(Boolean enviarMensagem) {
		this.enviarMensagem = enviarMensagem;
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
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

package com.presente.backend.domains;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.presente.backend.domains.enums.Turno;

/**
 * O controle deverá ser feito por matricula e ano letivo, sendo que o ano letivo não pode ser repetido, mas a matricula pode.
 * @author charlles
 */
@Entity
@Table(name="matricula")
public class Matricula extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_matricula")
	private Long id;
	
	@Column(name="str_matricula")
	private String matricula;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="id_serie")
	private Serie serie;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="id_turma")
	private Turma turma;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="id_sala")
	private Sala sala;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="id_aluno", nullable=false)
	private Aluno aluno;
	
	@Column(name="int_ano_letivo")
	private Integer anoLetivo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="str_turno")
	private Turno turno;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_data_matricula", columnDefinition="timestamp DEFAULT now()", insertable=false, updatable=true, nullable=false)
	private Date data;
	
	@Column(name="bol_bolsista", columnDefinition="boolean default false", insertable=true, updatable=true, nullable=false)
	private Boolean bolsista;
	
	@Column(name="bol_ativo", columnDefinition="boolean default true", insertable=true, updatable=true, nullable=false)
	private Boolean ativo;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="id_responsavel", nullable=true)
	private Responsavel responsavel;
	
	@Column(name="bol_enviar_email_registro", columnDefinition="boolean default true", insertable=true, updatable=true, nullable=false)
	private Boolean enviarEmailRegistro;
	
	@Column(name="bol_enviar_sms_registro", columnDefinition="boolean default true", insertable=true, updatable=true, nullable=false)
	private Boolean enviarSmsRegistro;
	
	@OneToMany(mappedBy="alunoDisciplinaPK.matricula", targetEntity=AlunoDisciplina.class)
	private List<AlunoDisciplina> listaAlunoDisciplina;
	
	public Matricula() {
		
	}
	
	public Matricula(String matricula) {
		this.matricula = matricula;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Boolean getBolsista() {
		return bolsista;
	}

	public void setBolsista(Boolean bolsista) {
		this.bolsista = bolsista;
	}

	public Integer getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(Integer anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
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
		Matricula other = (Matricula) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "Matricula [id=" + id + ", matricula=" + matricula + ", serie="
				+ serie + ", turma=" + turma + ", sala=" + sala + ", aluno="
				+ aluno + ", anoLetivo=" + anoLetivo + ", turno=" + turno
				+ ", data=" + data + ", bolsista=" + bolsista + ", ativo="
				+ ativo + ", responsavel=" + responsavel
				+ ", enviarEmailRegistro=" + enviarEmailRegistro + "]";
	}

	public Boolean getEnviarSmsRegistro() {
		return enviarSmsRegistro;
	}

	public void setEnviarSmsRegistro(Boolean enviarSmsRegistro) {
		this.enviarSmsRegistro = enviarSmsRegistro;
	}
	
}

package com.presente.backend.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

import com.presente.backend.domains.Aluno;
import com.presente.backend.domains.Matricula;
import com.presente.backend.services.validations.AlunoInsert;

/**
 * @author Charlles Bandeira
 *
 */
@AlunoInsert
public class AlunoCadastroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idMatricula;
	@NotEmpty
	private String nome;
	@Past
	private Date dataNascimento;
	@NotEmpty
	private String matricula;
	private String serie;
	private String turma;
	private String sala;
	private String turno;
	private boolean alunoBolsista;
	private String uriFoto;

	private String nomeResponsavel;
	@CPF
	private String cpf;
	@Email
	private String email;
	private String telefoneFixo;
	private String telefoneCelular;
	private boolean enviarEmail;
	private boolean enviarSMS;

	private Integer anoLetivo;

	public AlunoCadastroDTO() {
	}

	public AlunoCadastroDTO(Aluno aluno) {
		this.nome = aluno.getNome();
		this.dataNascimento = aluno.getDataNascimento();
		this.uriFoto = aluno.getFoto();
	}

	public AlunoCadastroDTO(Matricula matricula) {
		this.idMatricula = matricula.getId();
		this.nome = matricula.getAluno().getNome();
		this.dataNascimento = matricula.getAluno().getDataNascimento();
		this.uriFoto = matricula.getAluno().getFoto();
		this.idMatricula = matricula.getId();
		this.alunoBolsista = matricula.getBolsista();
		this.matricula = matricula.getMatricula();
		if (matricula.getSala() != null) {
			this.sala = matricula.getSala().getDescricao();
		}
		if (matricula.getSerie() != null) {
			this.serie = matricula.getSerie().getDescricao();
		}
		if (matricula.getTurma() != null) {
			this.turma = matricula.getTurma().getDescricao();
		}
		if (matricula.getTurno() != null) {
			this.turno = matricula.getTurno().getDescricao();
		}
		this.enviarEmail = matricula.getEnviarEmailRegistro();
		this.enviarSMS = matricula.getEnviarSmsRegistro();
		this.anoLetivo = matricula.getAnoLetivo();
		if (matricula.getResponsavel() != null) {
			this.cpf = matricula.getResponsavel().getCpf();
			this.email = matricula.getResponsavel().getEmail();
			this.nomeResponsavel = matricula.getResponsavel().getNome();
			this.telefoneCelular = matricula.getResponsavel().getTelefoneCelular();
			this.telefoneFixo = matricula.getResponsavel().getTelefoneFixo();
		}
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public boolean isAlunoBolsista() {
		return alunoBolsista;
	}

	public void setAlunoBolsista(boolean alunoBolsista) {
		this.alunoBolsista = alunoBolsista;
	}

	public String getUriFoto() {
		return uriFoto;
	}

	public void setUriFoto(String uriFoto) {
		this.uriFoto = uriFoto;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public boolean isEnviarEmail() {
		return enviarEmail;
	}

	public void setEnviarEmail(boolean enviarEmail) {
		this.enviarEmail = enviarEmail;
	}

	public boolean isEnviarSMS() {
		return enviarSMS;
	}

	public void setEnviarSMS(boolean enviarSMS) {
		this.enviarSMS = enviarSMS;
	}

	public Integer getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(Integer anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public Long getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(Long idMatricula) {
		this.idMatricula = idMatricula;
	}

}

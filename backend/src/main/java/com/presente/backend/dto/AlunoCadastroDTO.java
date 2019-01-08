package com.presente.backend.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.presente.backend.domains.Aluno;
import com.presente.backend.services.validations.AlunoInsert;

/**
 * @author Charlles Bandeira
 *
 */
@AlunoInsert
public class AlunoCadastroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty
	private String nome;
	@Past
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	@NotEmpty
	private String matricula;
	private String serie;
	private String turma;
	private String sala;
	private Integer turno;
	private boolean alunoBolsista;
	private String urlFoto;

	private String nomeResponsavel;
	@CPF
	private String cpf;
	@Email
	private String email;
	@Email
	private String email2;
	private String telefoneFixo;
	private String telefoneCelular;
	private boolean enviarEmail;
	private boolean enviarMensagem;

	private Integer anoLetivo;

	public AlunoCadastroDTO() {
	}

	public AlunoCadastroDTO(Aluno aluno) {
		this.id = aluno.getId();
		this.nome = aluno.getNome();
		this.dataNascimento = aluno.getDataNascimento();
		this.urlFoto = aluno.getUrlFoto();
		this.alunoBolsista = aluno.getBolsista();
		this.matricula = aluno.getMatricula();
		if (aluno.getTurma() != null) {
			this.turma = aluno.getTurma().getDescricao();
			this.sala = aluno.getTurma().getSala();
			this.serie = aluno.getTurma().getSerie();
			if (aluno.getTurma().getTurno() != null) {				
				this.turno = aluno.getTurma().getTurno().ordinal();
			}
		}
		this.enviarEmail = aluno.getEnviarEmailRegistro();
		this.enviarMensagem = aluno.getEnviarMensagem();
		this.anoLetivo = aluno.getAnoLetivo();
		if (aluno.getResponsavel() != null) {
			this.cpf = aluno.getResponsavel().getCpf();
			this.email = aluno.getResponsavel().getEmail();
			this.email2 = aluno.getResponsavel().getEmail2();
			this.nomeResponsavel = aluno.getResponsavel().getNome();
			this.telefoneCelular = aluno.getResponsavel().getTelefoneCelular();
			this.telefoneFixo = aluno.getResponsavel().getTelefoneFixo();
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

	public Integer getTurno() {
		return turno;
	}

	public void setTurno(Integer turno) {
		this.turno = turno;
	}

	public boolean isAlunoBolsista() {
		return alunoBolsista;
	}

	public void setAlunoBolsista(boolean alunoBolsista) {
		this.alunoBolsista = alunoBolsista;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
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

	public boolean isEnviarMensagem() {
		return enviarMensagem;
	}

	public void setEnviarMensagem(boolean enviarMensagem) {
		this.enviarMensagem = enviarMensagem;
	}

	public Integer getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(Integer anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setIdMatricula(Integer id) {
		this.id = id;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

}

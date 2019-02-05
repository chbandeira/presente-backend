package com.presente.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.presente.domains.Aluno;
import com.presente.domains.Telefone;

/**
 * @author Charlles Bandeira
 *
 */
public class AlunoCadastroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty
	@Length(min = 3, max = 200)
	private String nome;
	
	@Past
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
	@NotEmpty
	@Length(min = 1, max = 20)
	private String matricula;
	
	@Length(max = 5)
	private String serie;
	
	@Length(max = 5)
	private String turma;
	
	@Length(max = 5)
	private String sala;
	
	private Integer turno;
	
	private boolean alunoBolsista;
	
	private String urlFoto;

	@Length(max = 200)
	private String nomeResponsavel;
	
	@CPF
	private String cpf;
	
	@Email
	@Length(max = 200)
	private String email;
	
	@Email
	@Length(max = 200)
	private String email2;
	
	private Set<TelefoneDTO> telefones = new HashSet<>();
	
	private boolean enviarEmail;
	
	private boolean enviarMensagem;
	
	@JsonIgnore
	private String senha;
	
	private Integer anoLetivo;

	public AlunoCadastroDTO() {
	}

	public AlunoCadastroDTO(Aluno aluno) {
		this.id = aluno.getId();
		this.nome = aluno.getNome();
		this.dataNascimento = aluno.getDataNascimento();
		this.urlFoto = aluno.getUrlFoto();
		this.alunoBolsista = aluno.isBolsista();
		this.matricula = aluno.getMatricula();
		if (aluno.getTurma() != null) {
			this.turma = aluno.getTurma().getDescricao();
			this.sala = aluno.getTurma().getSala();
			this.serie = aluno.getTurma().getSerie();
			if (aluno.getTurma().getTurno() != null) {				
				this.turno = aluno.getTurma().getTurno().ordinal();
			}
		}
		this.enviarEmail = aluno.isEnviarEmailRegistro();
		this.enviarMensagem = aluno.isEnviarMensagem();
		this.anoLetivo = aluno.getAnoLetivo();
		if (aluno.getResponsavel() != null) {
			this.cpf = aluno.getResponsavel().getCpf();
			this.email = aluno.getResponsavel().getEmail();
			this.email2 = aluno.getResponsavel().getEmail2();
			this.nomeResponsavel = aluno.getResponsavel().getNome();
			for (Telefone tel : aluno.getResponsavel().getTelefones()) {				
				this.telefones.add(new TelefoneDTO(tel));
			}
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<TelefoneDTO> getTelefones() {
		return telefones;
	}

	public void addTelefone(Telefone telefone) {
		this.telefones.add(new TelefoneDTO(telefone));
	}

}

package com.presente.backend.domains;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.presente.backend.domains.enums.AlteracaoAluno;

/**
 * Guarda todas as informacoes de historico de inclusoes, alteracoes e exclusoes
 * relacionadas ao aluno e sua matricula
 * 
 * @author Charlles
 */
@Entity
public class HistoricoAlteracaoAluno extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	private String urlFoto;

	private String matricula;

	private Integer idTurma;

	private String turma;

	private String serie;

	private String sala;

	private Integer anoLetivo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataMatricula;

	private Boolean bolsista;

	private Integer idResponsavel;

	private String nomeResponsavel;

	private String emailResponsavel;
	
	private String emailResponsavel2;

	private String telefoneFixoResponsavel;

	private String telefoneCelularResponsavel;

	private String cpfResponsavel;

	private String loginResponsavel;

	private Boolean enviarEmailRegistro;
	
	private Boolean enviarMensagem;

	@Enumerated(EnumType.STRING)
	private AlteracaoAluno alteracaoAluno;

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

	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
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

	public Integer getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(Integer idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getEmailResponsavel() {
		return emailResponsavel;
	}

	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}

	public String getTelefoneFixoResponsavel() {
		return telefoneFixoResponsavel;
	}

	public void setTelefoneFixoResponsavel(String telefoneFixoResponsavel) {
		this.telefoneFixoResponsavel = telefoneFixoResponsavel;
	}

	public String getTelefoneCelularResponsavel() {
		return telefoneCelularResponsavel;
	}

	public void setTelefoneCelularResponsavel(String telefoneCelularResponsavel) {
		this.telefoneCelularResponsavel = telefoneCelularResponsavel;
	}

	public String getCpfResponsavel() {
		return cpfResponsavel;
	}

	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public String getLoginResponsavel() {
		return loginResponsavel;
	}

	public void setLoginResponsavel(String loginResponsavel) {
		this.loginResponsavel = loginResponsavel;
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
		HistoricoAlteracaoAluno other = (HistoricoAlteracaoAluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public AlteracaoAluno getAlteracaoAluno() {
		return alteracaoAluno;
	}

	public void setAlteracaoAluno(AlteracaoAluno alteracaoAluno) {
		this.alteracaoAluno = alteracaoAluno;
	}

	public Boolean getEnviarMensagem() {
		return enviarMensagem;
	}

	public void setEnviarMensagem(Boolean enviarMensagem) {
		this.enviarMensagem = enviarMensagem;
	}

	public String getEmailResponsavel2() {
		return emailResponsavel2;
	}

	public void setEmailResponsavel2(String emailResponsavel2) {
		this.emailResponsavel2 = emailResponsavel2;
	}

}

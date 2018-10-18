package br.com.w2c.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.util.Constantes;

/**
 * @author charlles
 * @since 03/10/2013
 */
@Entity
@Table(name="ocorrencia_importacao")
public class OcorrenciaImportacao extends BaseEntity {

	private static final long serialVersionUID = 1419796431525034404L;
	
	@Id
	@SequenceGenerator(name="seq_ocorrencia_importacao", sequenceName="seq_ocorrencia_importacao")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_ocorrencia_importacao")
	@Column(name="id_ocorrencia_importacao")
	private Long id;
	
	@Column(name="str_matricula")
	private String matricula;
	
	@Column(name="str_nome")
	private String nome;
	
	@Column(name="str_serie")
	private String serie;
	
	@Column(name="str_turma")
	private String turma;
	
	@Column(name="str_turno")
	private String turno;
	
	@Column(name="str_sala")
	private String sala;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dat_nascimento")
	private Date dataNascimento;
	
	@Column(name="int_ano_letivo")
	private Integer anoLetivo;
	
	@Column(name="bol_bolsista")
	private Boolean bolsista;
	
	@Column(name="str_responsavel")
	private String responsavel;
	
	@Column(name="str_responsavel_email")
	private String responsavelEmail;
	
	@Column(name="str_responsavel_celular")
	private String responsavelCelular;
	
	@Column(name="str_responsavel_telefone")
	private String responsavelTelefone;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(Integer anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public Boolean getBolsista() {
		return bolsista;
	}

	public void setBolsista(Boolean bolsista) {
		this.bolsista = bolsista;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getResponsavelEmail() {
		return responsavelEmail;
	}

	public void setResponsavelEmail(String responsavelEmail) {
		this.responsavelEmail = responsavelEmail;
	}

	public String getResponsavelCelular() {
		return responsavelCelular;
	}

	public void setResponsavelCelular(String responsavelCelular) {
		this.responsavelCelular = responsavelCelular;
	}

	public String getResponsavelTelefone() {
		return responsavelTelefone;
	}

	public void setResponsavelTelefone(String responsavelTelefone) {
		this.responsavelTelefone = responsavelTelefone;
	}

	@Override
	public String toString() {
		return "OcorrenciaImportacao [id=" + id + ", matricula=" + matricula
				+ ", nome=" + nome + ", serie=" + serie + ", turma=" + turma
				+ ", turno=" + turno + ", sala=" + sala + ", dataNascimento="
				+ dataNascimento + ", anoLetivo=" + anoLetivo + ", bolsista="
				+ bolsista + ", responsavel=" + responsavel
				+ ", responsavelEmail=" + responsavelEmail
				+ ", responsavelCelular=" + responsavelCelular
				+ ", responsavelTelefone=" + responsavelTelefone + "]";
	}

	/**
	 * Matricula;Aluno;Série;Turma;Turno;Sala;Data Nascimento;Ano Letivo;Bolsista;Responsável;E-mail Responsável;Celular Responsável;Telefone Responsável
	 * @return
	 */
	public String toStringExporter() {
		StringBuilder sb = new StringBuilder();
		sb.append(matricula == null ? "" : matricula).append(";");
		sb.append(nome == null ? "" : nome).append(";");
		sb.append(serie == null ? "" : serie).append(";");
		sb.append(turma == null ? "" : turma).append(";");
		sb.append(turno == null ? "" : turno).append(";");
		sb.append(sala == null ? "" : sala).append(";");
		sb.append(dataNascimento == null ? "" : UtilDate.getDataFormatada(dataNascimento, Constantes.DD_MM_YYYY)).append(";");
		sb.append(anoLetivo == null ? "" : anoLetivo).append(";");
		sb.append(bolsista == null ? "" : bolsista).append(";");
		sb.append(responsavel == null ? "" : responsavel).append(";");
		sb.append(responsavelEmail == null ? "" : responsavelEmail).append(";");
		sb.append(responsavelCelular == null ? "" : responsavelCelular).append(";");
		sb.append(responsavelTelefone == null ? "" : responsavelTelefone);
		return sb.toString();
	}

}

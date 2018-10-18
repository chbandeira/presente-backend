package br.com.w2c.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.w2c.model.enums.TipoHistoricoAlteracao;
import br.com.w2c.model.enums.Turno;

/**
 * Guarda todas as informacoes de historico de inclusoes, alteracoes e exclusoes relacionadas ao aluno e sua matricula
 * @author Charlles
 * @since 19/01/2014
 */
@Entity
@Table(name="historico_alteracao")
public class HistoricoAlteracao extends BaseEntity {

	private static final long serialVersionUID = 2386773431462747517L;

	@Id
	@SequenceGenerator(name="seq_historico_alteracao", sequenceName="seq_historico_alteracao")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_historico_alteracao")
	@Column(name="id_historico_alteracao")
	private Long id;
	
	@Column(name="id_responsavel")
	private Long idResponsavel;
	
	@Column(name="str_responsavel_nome")
	private String responsavelNome;
	
	@Column(name="str_responsavel_email")
	private String responsavelEmail;
	
	@Column(name="str_responsavel_telefone_fixo")
	private String responsavelTelefoneFixo;
	
	@Column(name="str_responsavel_telefone_celular")
	private String responsavelTelefoneCelular;
	
	@Column(name="id_aluno")
	private Long idAluno;
	
	@Column(name="str_aluno_nome")
	private String alunoNome;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dat_aluno_nascimento")
	private Date alunoDataNascimento;
	
	@Column(name="bol_aluno_ativo")
	private Boolean alunoAtivo;
	
	@Column(name="id_sala")
	private Long idSala;
	
	@Column(name="str_sala_descricao")
	private String salaDescricao;
	
	@Column(name="bol_sala_ativo")
	private Boolean salaAtivo;
	
	@Column(name="id_turma")
	private Long idTurma;
	
	@Column(name="str_turma_descricao")
	private String turmaDescricao;
	
	@Column(name="bol_turma_ativo")
	private Boolean turmaAtivo;
	
	@Column(name="id_serie")
	private Long idSerie;
	
	@Column(name="str_serie_descricao")
	private String serieDescricao;
	
	@Column(name="bol_serie_ativo")
	private Boolean serieAtivo;
	
	@Column(name="id_matricula")
	private Long idMatricula;
	
	@Column(name="str_matricula")
	private String matricula;
	
	@Column(name="int_matricula_ano_letivo")
	private Integer matriculaAnoLetivo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="str_matricula_turno")
	private Turno matriculaTurno;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_matricula_data_matricula")
	private Date matriculaData;
	
	@Column(name="bol_matricula_bolsista")
	private Boolean matriculaBolsista;
	
	@Column(name="bol_matricula_ativo")
	private Boolean matriculaAtivo;
	
	@Column(name="bol_matricula_enviar_email_registro")
	private Boolean matriculaEnviarEmailRegistro;
	
	@Enumerated(EnumType.STRING)
	@Column(name="str_tipo_alteracao")
	private TipoHistoricoAlteracao tipoHistoricoAlteracao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_ultima_alteracao")
	private Date ultimaAlteracao;
	
	@Column(name="str_entidade_origem")
	private String entidadeOrigem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(Long idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

	public String getResponsavelNome() {
		return responsavelNome;
	}

	public void setResponsavelNome(String responsavelNome) {
		this.responsavelNome = responsavelNome;
	}

	public String getResponsavelEmail() {
		return responsavelEmail;
	}

	public void setResponsavelEmail(String responsavelEmail) {
		this.responsavelEmail = responsavelEmail;
	}

	public String getResponsavelTelefoneFixo() {
		return responsavelTelefoneFixo;
	}

	public void setResponsavelTelefoneFixo(String responsavelTelefoneFixo) {
		this.responsavelTelefoneFixo = responsavelTelefoneFixo;
	}

	public String getResponsavelTelefoneCelular() {
		return responsavelTelefoneCelular;
	}

	public void setResponsavelTelefoneCelular(String responsavelTelefoneCelular) {
		this.responsavelTelefoneCelular = responsavelTelefoneCelular;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public String getAlunoNome() {
		return alunoNome;
	}

	public void setAlunoNome(String alunoNome) {
		this.alunoNome = alunoNome;
	}

	public Date getAlunoDataNascimento() {
		return alunoDataNascimento;
	}

	public void setAlunoDataNascimento(Date alunoDataNascimento) {
		this.alunoDataNascimento = alunoDataNascimento;
	}

	public Boolean getAlunoAtivo() {
		return alunoAtivo;
	}

	public void setAlunoAtivo(Boolean alunoAtivo) {
		this.alunoAtivo = alunoAtivo;
	}

	public Long getIdSala() {
		return idSala;
	}

	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}

	public String getSalaDescricao() {
		return salaDescricao;
	}

	public void setSalaDescricao(String salaDescricao) {
		this.salaDescricao = salaDescricao;
	}

	public Boolean getSalaAtivo() {
		return salaAtivo;
	}

	public void setSalaAtivo(Boolean salaAtivo) {
		this.salaAtivo = salaAtivo;
	}

	public Long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}

	public String getTurmaDescricao() {
		return turmaDescricao;
	}

	public void setTurmaDescricao(String turmaDescricao) {
		this.turmaDescricao = turmaDescricao;
	}

	public Boolean getTurmaAtivo() {
		return turmaAtivo;
	}

	public void setTurmaAtivo(Boolean turmaAtivo) {
		this.turmaAtivo = turmaAtivo;
	}

	public Long getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(Long idSerie) {
		this.idSerie = idSerie;
	}

	public String getSerieDescricao() {
		return serieDescricao;
	}

	public void setSerieDescricao(String serieDescricao) {
		this.serieDescricao = serieDescricao;
	}

	public Boolean getSerieAtivo() {
		return serieAtivo;
	}

	public void setSerieAtivo(Boolean serieAtivo) {
		this.serieAtivo = serieAtivo;
	}

	public Long getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(Long idMatricula) {
		this.idMatricula = idMatricula;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getMatriculaAnoLetivo() {
		return matriculaAnoLetivo;
	}

	public void setMatriculaAnoLetivo(Integer matriculaAnoLetivo) {
		this.matriculaAnoLetivo = matriculaAnoLetivo;
	}

	public Turno getMatriculaTurno() {
		return matriculaTurno;
	}

	public void setMatriculaTurno(Turno matriculaTurno) {
		this.matriculaTurno = matriculaTurno;
	}

	public Date getMatriculaData() {
		return matriculaData;
	}

	public void setMatriculaData(Date matriculaData) {
		this.matriculaData = matriculaData;
	}

	public Boolean getMatriculaBolsista() {
		return matriculaBolsista;
	}

	public void setMatriculaBolsista(Boolean matriculaBolsista) {
		this.matriculaBolsista = matriculaBolsista;
	}

	public Boolean getMatriculaAtivo() {
		return matriculaAtivo;
	}

	public void setMatriculaAtivo(Boolean matriculaAtivo) {
		this.matriculaAtivo = matriculaAtivo;
	}

	public Boolean getMatriculaEnviarEmailRegistro() {
		return matriculaEnviarEmailRegistro;
	}

	public void setMatriculaEnviarEmailRegistro(Boolean matriculaEnviarEmailRegistro) {
		this.matriculaEnviarEmailRegistro = matriculaEnviarEmailRegistro;
	}

	public TipoHistoricoAlteracao getTipoHistoricoAlteracao() {
		return tipoHistoricoAlteracao;
	}

	public void setTipoHistoricoAlteracao(
			TipoHistoricoAlteracao tipoHistoricoAlteracao) {
		this.tipoHistoricoAlteracao = tipoHistoricoAlteracao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getEntidadeOrigem() {
		return entidadeOrigem;
	}

	public void setEntidadeOrigem(String entidadeOrigem) {
		this.entidadeOrigem = entidadeOrigem;
	}
	
}

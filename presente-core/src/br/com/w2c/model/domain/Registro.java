package br.com.w2c.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.w2c.model.enums.TipoRegistro;
import br.com.w2c.model.enums.Turno;

/**
 * Para o registro, deve guardar suas próprias informações como: nome do aluno, série, turma no momento do registro e
 * o relacionamento com a matricula para obter as informações mais atuais.
 * @author charlles
 * @since 01/09/2013
 */
@NamedQueries({
	@NamedQuery(name="Registro.obterDataStringPorMatriculaEPeriodo", query="select to_char(r.data, 'dd/MM/yyyy') from Registro r inner join r.matricula m inner join m.aluno a where upper(m.matricula) like upper(:matricula) and upper(m.aluno.nome) like upper(:nome) and (to_char(r.data, 'yyyy-MM-dd') between :periodoInicial and :periodoFinal) and m.ativo = true and a.ativo = true order by r.data desc"),
	@NamedQuery(name="Registro.obterListaPorParametros", query="select r from Registro r where upper(r.matricula.matricula) like upper(:matricula)"),
	@NamedQuery(name="Registro.obterPorMatriculaEPeriodo", query="select r from Registro r inner join r.matricula m inner join m.aluno a where upper(m.matricula) like upper(:matricula) and upper(m.aluno.nome) like upper(:nome) and (to_char(r.data, 'yyyy-MM-dd') between :periodoInicial and :periodoFinal) and m.ativo = true and a.ativo = true order by r.data desc"),
	@NamedQuery(name="Registro.obterPorParametrosUnicos", query="select r from Registro r where upper(r.matricula.matricula) = upper(:matricula)"),
	@NamedQuery(name="Registro.obterPorPeriodo", query="select r from Registro r inner join r.matricula m inner join m.aluno a where (to_char(r.data, 'yyyy-MM-dd') between :periodoInicial and :periodoFinal) and m.ativo = true and a.ativo = true order by r.data desc"),
	@NamedQuery(name="Registro.obterPorPeriodoFinal", query="select r from Registro r inner join r.matricula m inner join m.aluno a where upper(m.matricula) like upper(:matricula) and upper(m.aluno.nome) like upper(:nome) and (to_char(r.data, 'yyyy-MM-dd') <= :periodoFinal) and m.ativo = true and a.ativo = true order by r.data desc"),
	@NamedQuery(name="Registro.obterPorPeriodoInicial", query="select r from Registro r inner join r.matricula m inner join m.aluno a where upper(m.matricula) like upper(:matricula) and upper(m.aluno.nome) like upper(:nome) and (to_char(r.data, 'yyyy-MM-dd') >= :periodoInicial) and m.ativo = true and a.ativo = true order by r.data desc"),
	@NamedQuery(name="Registro.obterRepetido", query="select r from Registro r where to_char(r.data, 'yyyy-MM-dd') = :dataFormatada and r.tipoRegistro = :tipo and upper(r.matricula.matricula) = upper(:matricula)"),
	@NamedQuery(name="Registro.obterSemPeriodo", query="select r from Registro r inner join r.matricula m inner join m.aluno a where upper(m.matricula) like upper(:matricula) and upper(m.aluno.nome) like upper(:nome) and m.ativo = true and a.ativo = true order by r.data desc"),
	@NamedQuery(name="Registro.obterSmsNaoEnviado", query="select r from Registro r where r.matriculaEnviarSmsRegistro = true and r.smsRegistroEnviado = false and to_char(r.data,'yyyy-mm-dd') = to_char(current_date,'yyyy-mm-dd')"),
	@NamedQuery(name="Registro.obterEmailNaoEnviado", query="select r from Registro r where r.matriculaEnviarEmailRegistro = true and r.emailRegistroEnviado = false and to_char(r.data,'yyyy-mm-dd') = to_char(current_date,'yyyy-mm-dd')"),
	@NamedQuery(name="Registro.obterNaoEnviado", query="select r from Registro r where ((r.matriculaEnviarEmailRegistro = true and r.emailRegistroEnviado = false) or (r.matriculaEnviarSmsRegistro = true and r.smsRegistroEnviado = false) and to_char(r.data,'yyyy-mm-dd') = to_char(current_date,'yyyy-mm-dd'))")
})
@Entity
@Table(name="registro")
public class Registro extends BaseEntity {

	private static final long serialVersionUID = -278991527154840788L;
	
	@Id
	@SequenceGenerator(name="seq_registro", sequenceName="seq_registro")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_registro")
	@Column(name="id_registro")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_data", nullable=false)
	private Date data;
	
	@Enumerated(EnumType.STRING)
	@Column(name="str_tipo_registro")
	private TipoRegistro tipoRegistro;
	
	@ManyToOne
	@JoinColumn(name="id_matricula")
	private Matricula matricula;
	
	@Column(name="id_aluno")
	private Long idAluno;
	
	@Column(name="str_aluno_nome")
	private String alunoNome;
	
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
	
	@Column(name="str_matricula")
	private String matriculaDesc;
	
	@Column(name="int_matricula_ano_letivo")
	private Integer matriculaAnoLetivo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="str_matricula_turno")
	private Turno matriculaTurno;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_matricula_data_matricula")
	private Date matriculaData;
	
	@Column(name="bol_matricula_ativo")
	private Boolean matriculaAtivo;
	
	@Column(name="bol_matricula_enviar_email_registro")
	private Boolean matriculaEnviarEmailRegistro;
	
	@Column(name="bol_matricula_enviar_sms_registro")
	private Boolean matriculaEnviarSmsRegistro;
	
	@Column(name="str_email")
	private String responsavelEmail;
	
	@Column(name="str_telefone_celular")
	private String responsavelTelefoneCelular;
	
	@Column(name="bol_email_registro_enviado")
	private Boolean emailRegistroEnviado;
	
	@Column(name="bol_sms_registro_enviado")
	private Boolean smsRegistroEnviado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public TipoRegistro getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(TipoRegistro tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
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
	public String getMatriculaDesc() {
		return matriculaDesc;
	}
	public void setMatriculaDesc(String matriculaDesc) {
		this.matriculaDesc = matriculaDesc;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((tipoRegistro == null) ? 0 : tipoRegistro.hashCode());
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
		Registro other = (Registro) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tipoRegistro != other.tipoRegistro)
			return false;
		return true;
	}
	public Boolean getMatriculaEnviarSmsRegistro() {
		return matriculaEnviarSmsRegistro;
	}
	public void setMatriculaEnviarSmsRegistro(Boolean matriculaEnviarSmsRegistro) {
		this.matriculaEnviarSmsRegistro = matriculaEnviarSmsRegistro;
	}
	public String getResponsavelEmail() {
		return responsavelEmail;
	}
	public void setResponsavelEmail(String responsavelEmail) {
		this.responsavelEmail = responsavelEmail;
	}
	public String getResponsavelTelefoneCelular() {
		return responsavelTelefoneCelular;
	}
	public void setResponsavelTelefoneCelular(String responsavelTelefoneCelular) {
		this.responsavelTelefoneCelular = responsavelTelefoneCelular;
	}
	public Boolean getEmailRegistroEnviado() {
		return emailRegistroEnviado;
	}
	public void setEmailRegistroEnviado(Boolean emailRegistroEnviado) {
		this.emailRegistroEnviado = emailRegistroEnviado;
	}
	public Boolean getSmsRegistroEnviado() {
		return smsRegistroEnviado;
	}
	public void setSmsRegistroEnviado(Boolean smsRegistroEnviado) {
		this.smsRegistroEnviado = smsRegistroEnviado;
	}

	@Override
	public String toString() {
		return "Registro [id=" + id + ", data=" + data + ", tipoRegistro="
				+ tipoRegistro + ", alunoNome=" + alunoNome
				+ ", turmaDescricao=" + turmaDescricao + ", serieDescricao="
				+ serieDescricao + ", matriculaDesc=" + matriculaDesc
				+ ", matriculaTurno=" + matriculaTurno + "]";
	}
}

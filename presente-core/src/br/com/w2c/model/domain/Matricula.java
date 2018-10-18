package br.com.w2c.model.domain;

import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.chbandeira.utilitario.annotation.ExclusionStrategy;
import br.com.w2c.model.enums.Turno;

/**
 * O controle deverá ser feito por matricula e ano letivo, sendo que o ano letivo não pode ser repetido, mas a matricula pode.
 * @author charlles
 * @since 01/09/2013
 */

@NamedQueries({
	@NamedQuery(name="Matricula.obterListaPorParametros", query="select m from Matricula m inner join m.aluno a where upper(m.matricula) like upper(:matricula) and upper(a.nome) like upper(:nome) and m.ativo = true and a.ativo = true order by a.nome, m.matricula, m.anoLetivo asc"),
	@NamedQuery(name="Matricula.obterMatriculasPorPeriodo", query="select m from Matricula m inner join m.aluno a where upper(m.matricula) like upper(:matricula) and upper(a.nome) like upper(:nome) and m.ativo = true and a.ativo = true and (to_char(m.data, 'yyyy-MM-dd') between :periodoInicial and :periodoFinal)"),
	@NamedQuery(name="Matricula.obterMatriculasPorPeriodoFinal", query="select m from Matricula m inner join m.aluno a where upper(m.matricula) like upper(:matricula) and upper(a.nome) like upper(:nome) and m.ativo = true and a.ativo = true and (to_char(m.data, 'yyyy-MM-dd') <= :periodoFinal)"),
	@NamedQuery(name="Matricula.obterMatriculasPorPeriodoInicial", query="select m from Matricula m inner join m.aluno a where upper(m.matricula) like upper(:matricula) and upper(a.nome) like upper(:nome) and m.ativo = true and a.ativo = true and (to_char(m.data, 'yyyy-MM-dd') >= :periodoInicial)"),
	@NamedQuery(name="Matricula.obterPorMatricula", query="select m from Matricula m where upper(m.matricula) = upper(:matricula) and m.ativo = true"),
	@NamedQuery(name="Matricula.obterPorParametrosUnicos", query="select m from Matricula m where upper(m.matricula) = upper(:matricula) and m.ativo = true"),
	@NamedQuery(name="Matricula.obterRepetido ", query="select m from Matricula m where upper(m.matricula) = upper(:matricula) and m.id != :id and m.ativo = true"),
	@NamedQuery(name="Matricula.obterTodos", query="select m from Matricula m inner join m.aluno a where m.ativo = true order by a.nome asc"),
	@NamedQuery(name="Matricula.obterQtdAtivos", query="select count(m) from Matricula m where m.ativo = true"),
	@NamedQuery(name="Matricula.obterRepetidoComMesmoAno", query="select m from Matricula m where upper(m.matricula) = upper(:matricula) and m.id != :id and m.ativo = true and m.anoLetivo = :anoLetivo"),
	@NamedQuery(name="Matricula.obterPorMatriculaEAnoLetivo", query="select m from Matricula m where upper(m.matricula) = upper(:matricula) and m.ativo = true and m.anoLetivo = :anoLetivo"),
	@NamedQuery(name="Matricula.obterListaPorParametrosEAnoLetivo", query="select m from Matricula m inner join m.aluno a where upper(m.matricula) like upper(:matricula) and upper(a.nome) like upper(:nome) and m.ativo = true and a.ativo = true and m.anoLetivo = :anoLetivo order by a.nome, m.matricula, m.anoLetivo asc"),
	@NamedQuery(name="Matricula.obterListaPorParametrosOrdemData", query="select m from Matricula m inner join m.aluno a where upper(m.matricula) like upper(:matricula) and upper(a.nome) like upper(:nome) and m.ativo = true and a.ativo = true order by m.data desc"),
	@NamedQuery(name="Matricula.obterListaPorParametrosOrdem", query="select m from Matricula m inner join m.aluno a where upper(m.matricula) like upper(:matricula) and upper(a.nome) like upper(:nome) and m.ativo = true and a.ativo = true and m.anoLetivo = :anoLetivo order by m.data desc"),
	@NamedQuery(name="Matricula.obterListaPorSugestaoNomeAluno", query="select m from Matricula m inner join m.aluno a where upper(a.nome) like upper(:nomeAluno) and m.ativo = true and a.ativo = true and m.anoLetivo = :anoLetivo"),
	@NamedQuery(name="Matricula.obterPorTurma", query="select m from Matricula m inner join m.turma t where t.id = :id and m.ativo = true and t.ativo = true"),
	@NamedQuery(name="Matricula.obterPorTurmaSerieAnoLetivo", query="select m from Matricula m where m.turma.id = :idTurma and m.serie.id = :idSerie and m.anoLetivo = :anoLetivo and m.ativo = true"),
	@NamedQuery(name="Matricula.obterPorTurmaAnoLetivo", query="select m from Matricula m where m.turma.id = :idTurma and m.anoLetivo = :anoLetivo and m.ativo = true"),
	@NamedQuery(name="Matricula.obterPorUsuario", query="select m from Matricula m inner join m.aluno a inner join m.responsavel r inner join r.usuario u where m.ativo = true and a.ativo = true and u.login = :idUsuario and u.identificador = :identificador order by a.nome, m.matricula, m.anoLetivo asc"),
	@NamedQuery(name="Matricula.obterListaPorSugestaoNomeAlunoEUsuario", query="select m from Matricula m inner join m.aluno a where upper(a.nome) like upper(:nomeAluno) and m.ativo = true and a.ativo = true and m.identificador = :identificador"),
	@NamedQuery(name="Matricula.obterListaPorSugestaoMatricula", query="select m from Matricula m inner join m.aluno a where m.matricula like :matricula and m.ativo = true and a.ativo = true and m.anoLetivo = :anoLetivo")
})
@Entity
@Table(name="matricula")
public class Matricula extends BaseEntity {

	private static final long serialVersionUID = 2234940647112715368L;

	@Id
	@SequenceGenerator(name="seq_matricula", sequenceName="seq_matricula")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_matricula")
	@Column(name="id_matricula")
	private Long id;
	
	@Column(name="str_matricula")
	private String matricula;
	
	@ManyToOne
	@JoinColumn(name="id_serie")
	private Serie serie;
	
	@ManyToOne
	@JoinColumn(name="id_turma")
	private Turma turma;
	
	@ManyToOne
	@JoinColumn(name="id_sala")
	private Sala sala;
	
	@ManyToOne
	@JoinColumn(name="id_aluno", nullable=false)
	private Aluno aluno;
	
	@Column(name="int_ano_letivo")
	private Integer anoLetivo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="str_turno")
	private Turno turno;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_data_matricula", nullable=false)
	private Date data;
	
	@Column(name="bol_bolsista", columnDefinition="boolean default false", insertable=true, updatable=true, nullable=false)
	private Boolean bolsista;
	
	@Column(name="bol_ativo", columnDefinition="boolean default true", insertable=true, updatable=true, nullable=false)
	private Boolean ativo;
	
	@ManyToOne
	@JoinColumn(name="id_responsavel", nullable=true)
	private Responsavel responsavel;
	
	@Column(name="bol_enviar_email_registro", columnDefinition="boolean default true", insertable=true, updatable=true, nullable=false)
	private Boolean enviarEmailRegistro;
	
	@Column(name="bol_enviar_sms_registro", columnDefinition="boolean default true", insertable=true, updatable=true, nullable=false)
	private Boolean enviarSmsRegistro;
	
	@ExclusionStrategy
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

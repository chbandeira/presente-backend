package br.com.w2c.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Charlles
 * @since 05/05/2015
 */
@Entity
@Table(name = "aluno_disciplina")
public class AlunoDisciplina extends BaseEntity {

	private static final long serialVersionUID = 1756661791116001929L;

	@EmbeddedId
	private AlunoDisciplinaPK alunoDisciplinaPK;
	
	@Column(name = "vlr_nota_b1", precision = 2)
	private Double notaB1;

	@Column(name = "vlr_nota_b2", precision = 2)
	private Double notaB2;

	@Column(name = "vlr_nota_b3", precision = 2)
	private Double notaB3;

	@Column(name = "vlr_nota_b4", precision = 2)
	private Double notaB4;

	@Column(name = "vlr_nota_final")
	private Double notaFinal;

	@Column(name = "num_falta_b1")
	private Integer numeroFaltaB1;

	@Column(name = "num_falta_b2")
	private Integer numeroFaltaB2;

	@Column(name = "num_falta_b3")
	private Integer numeroFaltaB3;

	@Column(name = "num_falta_b4")
	private Integer numeroFaltaB4;

	@Column(name = "num_falta_final")
	private Integer numeroFaltaFinal;

	public AlunoDisciplinaPK getAlunoDisciplinaPK() {
		return alunoDisciplinaPK;
	}

	public void setAlunoDisciplinaPK(AlunoDisciplinaPK alunoDisciplinaPK) {
		this.alunoDisciplinaPK = alunoDisciplinaPK;
	}

	public Double getNotaB1() {
		return notaB1;
	}

	public void setNotaB1(Double notaB1) {
		this.notaB1 = notaB1;
	}

	public Double getNotaB2() {
		return notaB2;
	}

	public void setNotaB2(Double notaB2) {
		this.notaB2 = notaB2;
	}

	public Double getNotaB3() {
		return notaB3;
	}

	public void setNotaB3(Double notaB3) {
		this.notaB3 = notaB3;
	}

	public Double getNotaB4() {
		return notaB4;
	}

	public void setNotaB4(Double notaB4) {
		this.notaB4 = notaB4;
	}

	public Double getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(Double notaFinal) {
		this.notaFinal = notaFinal;
	}

	public Integer getNumeroFaltaB1() {
		return numeroFaltaB1;
	}

	public void setNumeroFaltaB1(Integer numeroFaltaB1) {
		this.numeroFaltaB1 = numeroFaltaB1;
	}

	public Integer getNumeroFaltaB2() {
		return numeroFaltaB2;
	}

	public void setNumeroFaltaB2(Integer numeroFaltaB2) {
		this.numeroFaltaB2 = numeroFaltaB2;
	}

	public Integer getNumeroFaltaB3() {
		return numeroFaltaB3;
	}

	public void setNumeroFaltaB3(Integer numeroFaltaB3) {
		this.numeroFaltaB3 = numeroFaltaB3;
	}

	public Integer getNumeroFaltaB4() {
		return numeroFaltaB4;
	}

	public void setNumeroFaltaB4(Integer numeroFaltaB4) {
		this.numeroFaltaB4 = numeroFaltaB4;
	}

	public Integer getNumeroFaltaFinal() {
		return numeroFaltaFinal;
	}

	public void setNumeroFaltaFinal(Integer numeroFaltaFinal) {
		this.numeroFaltaFinal = numeroFaltaFinal;
	}
	
	@Embeddable
	public class AlunoDisciplinaPK implements Serializable {

		private static final long serialVersionUID = 1L;
		
		@ManyToOne
		@JoinColumn(name="id_matricula", nullable=false)
		private Matricula matricula;
		
		@ManyToOne
		@JoinColumn(name="id_disciplina", nullable=false)
		private Disciplina disciplina;

		public Matricula getMatricula() {
			return matricula;
		}

		public void setMatricula(Matricula matricula) {
			this.matricula = matricula;
		}

		public Disciplina getDisciplina() {
			return disciplina;
		}

		public void setDisciplina(Disciplina disciplina) {
			this.disciplina = disciplina;
		}
		
	}
	
}
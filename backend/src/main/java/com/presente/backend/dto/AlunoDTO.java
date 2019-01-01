package com.presente.backend.dto;

import java.io.Serializable;

import com.presente.backend.domains.Aluno;

/**
 * @author Charlles Bandeira
 *
 */
public class AlunoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String matricula;
	private String serie;
	private String turma;
	private String sala;
	private String turno;
	private Integer anoLetivo;
	private boolean alunoBolsista;

	public AlunoDTO() {
	}

	public AlunoDTO(Aluno aluno) {
		this.id = aluno.getId();
		this.nome = aluno.getNome();
		this.alunoBolsista = aluno.getBolsista();
		this.matricula = aluno.getMatricula();
		if (aluno.getTurma() != null) {
			this.turma = aluno.getTurma().getDescricao();
			this.serie = aluno.getTurma().getSerie();
			this.sala = aluno.getTurma().getSala();
			if (aluno.getTurma().getTurno() != null) {
				this.turno = aluno.getTurma().getTurno().getSigla();
			}
		}
		this.anoLetivo = aluno.getAnoLetivo();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(Integer anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

}

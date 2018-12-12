package com.presente.backend.dto;

import java.io.Serializable;

import com.presente.backend.domains.Aluno;
import com.presente.backend.domains.Matricula;

/**
 * @author Charlles Bandeira
 *
 */
public class AlunoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idMatricula;
	private String nome;
	private String matricula;
	private String serie;
	private String turma;
	private String sala;
	private String turno;
	private boolean alunoBolsista;

	public AlunoDTO() {
	}

	public AlunoDTO(Aluno aluno) {
		this.nome = aluno.getNome();
	}

	public AlunoDTO(Matricula matricula) {
		this.idMatricula = matricula.getId();
		this.nome = matricula.getAluno().getNome();
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

	public Long getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(Long idMatricula) {
		this.idMatricula = idMatricula;
	}

}

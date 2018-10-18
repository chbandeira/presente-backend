package br.com.w2c.model.wrapper;

import java.text.ParseException;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.model.domain.Aluno;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Sala;
import br.com.w2c.model.domain.Serie;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.model.enums.Turno;

/**
 * 
 * @author Charlles
 * @since 21/07/2015
 */
public class MatriculaWrapper {

	private String matricula;
	private String nome;
	private String serie;
	private String turma;
	private String sala;
	private String turno;
	private String data;
	
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	/**
	 * @return Retorna entidade Matricula populada
	 * @throws ParseException 
	 */
	public Matricula getEntity() throws ParseException {
		
		Matricula matricula = new Matricula();
		matricula.setMatricula(this.matricula);
		matricula.setAluno(new Aluno(nome));
		matricula.setSerie(new Serie(serie));
		matricula.setTurma(new Turma(turma));
		matricula.setTurno(Turno.valueOf(turno));
		matricula.setSala(new Sala(sala));
		matricula.setData(UtilDate.getStringToDate(data));
		
		return matricula;
	}
	
}

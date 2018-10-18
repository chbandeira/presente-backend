package br.com.w2c.jasperreport.wrapper;

import java.io.Serializable;

/**
 * 
 * @author charlles
 * @since 27/10/2013
 */
public class BoletimFrequenciaHeaderWrapper implements Serializable {

	private static final long serialVersionUID = -7207156248693927578L;
	
	private String matricula;
	private String serie;
	private String turma;
	private String turno;
	private String sala;
	private String nomeAluno;
	private String responsavelAluno;
	private String dataNascimento;
	private String ocorrencias;
	
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
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getResponsavelAluno() {
		return responsavelAluno;
	}
	public void setResponsavelAluno(String responsavelAluno) {
		this.responsavelAluno = responsavelAluno;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getOcorrencias() {
		return ocorrencias;
	}
	public void setOcorrencias(String ocorrencias) {
		this.ocorrencias = ocorrencias;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result
				+ ((nomeAluno == null) ? 0 : nomeAluno.hashCode());
		result = prime * result
				+ ((ocorrencias == null) ? 0 : ocorrencias.hashCode());
		result = prime
				* result
				+ ((responsavelAluno == null) ? 0 : responsavelAluno.hashCode());
		result = prime * result + ((sala == null) ? 0 : sala.hashCode());
		result = prime * result + ((serie == null) ? 0 : serie.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
		result = prime * result + ((turno == null) ? 0 : turno.hashCode());
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
		BoletimFrequenciaHeaderWrapper other = (BoletimFrequenciaHeaderWrapper) obj;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (nomeAluno == null) {
			if (other.nomeAluno != null)
				return false;
		} else if (!nomeAluno.equals(other.nomeAluno))
			return false;
		if (ocorrencias == null) {
			if (other.ocorrencias != null)
				return false;
		} else if (!ocorrencias.equals(other.ocorrencias))
			return false;
		if (responsavelAluno == null) {
			if (other.responsavelAluno != null)
				return false;
		} else if (!responsavelAluno.equals(other.responsavelAluno))
			return false;
		if (sala == null) {
			if (other.sala != null)
				return false;
		} else if (!sala.equals(other.sala))
			return false;
		if (serie == null) {
			if (other.serie != null)
				return false;
		} else if (!serie.equals(other.serie))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		if (turno == null) {
			if (other.turno != null)
				return false;
		} else if (!turno.equals(other.turno))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "BoletimFrequenciaWrapper [matricula=" + matricula + ", serie="
				+ serie + ", turma=" + turma + ", turno=" + turno + ", sala="
				+ sala + ", nomeAluno=" + nomeAluno + ", responsavelAluno="
				+ responsavelAluno + ", dataNascimento=" + dataNascimento
				+ ", ocorrencias=" + ocorrencias + "]";
	}

}

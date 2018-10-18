package br.com.w2c.jasperreport.wrapper;

import java.io.Serializable;

/**
 * 
 * @author charlles
 * @since 27/10/2013
 */
public class BoletimFrequenciaWrapper implements Serializable {

	private static final long serialVersionUID = -3890766847520089401L;
	
	private Integer dia;
	private String frequenciaMatutino;
	private String entradaMatutino;
	private String saidaMatutino;
	private String frequenciaVespertino;
	private String entradaVespertino;
	private String saidaVespertino;
	private String frequenciaNoturno;
	private String entradaNoturno;
	private String saidaNoturno;
	
	public Integer getDia() {
		return dia;
	}
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	public String getFrequenciaMatutino() {
		return frequenciaMatutino;
	}
	public void setFrequenciaMatutino(String frequenciaMatutino) {
		this.frequenciaMatutino = frequenciaMatutino;
	}
	public String getEntradaMatutino() {
		return entradaMatutino;
	}
	public void setEntradaMatutino(String entradaMatutino) {
		this.entradaMatutino = entradaMatutino;
	}
	public String getSaidaMatutino() {
		return saidaMatutino;
	}
	public void setSaidaMatutino(String saidaMatutino) {
		this.saidaMatutino = saidaMatutino;
	}
	public String getFrequenciaVespertino() {
		return frequenciaVespertino;
	}
	public void setFrequenciaVespertino(String frequenciaVespertino) {
		this.frequenciaVespertino = frequenciaVespertino;
	}
	public String getEntradaVespertino() {
		return entradaVespertino;
	}
	public void setEntradaVespertino(String entradaVespertino) {
		this.entradaVespertino = entradaVespertino;
	}
	public String getSaidaVespertino() {
		return saidaVespertino;
	}
	public void setSaidaVespertino(String saidaVespertino) {
		this.saidaVespertino = saidaVespertino;
	}
	public String getFrequenciaNoturno() {
		return frequenciaNoturno;
	}
	public void setFrequenciaNoturno(String frequenciaNoturno) {
		this.frequenciaNoturno = frequenciaNoturno;
	}
	public String getEntradaNoturno() {
		return entradaNoturno;
	}
	public void setEntradaNoturno(String entradaNoturno) {
		this.entradaNoturno = entradaNoturno;
	}
	public String getSaidaNoturno() {
		return saidaNoturno;
	}
	public void setSaidaNoturno(String saidaNoturno) {
		this.saidaNoturno = saidaNoturno;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
		result = prime * result
				+ ((entradaMatutino == null) ? 0 : entradaMatutino.hashCode());
		result = prime * result
				+ ((entradaNoturno == null) ? 0 : entradaNoturno.hashCode());
		result = prime
				* result
				+ ((entradaVespertino == null) ? 0 : entradaVespertino
						.hashCode());
		result = prime
				* result
				+ ((frequenciaMatutino == null) ? 0 : frequenciaMatutino
						.hashCode());
		result = prime
				* result
				+ ((frequenciaNoturno == null) ? 0 : frequenciaNoturno
						.hashCode());
		result = prime
				* result
				+ ((frequenciaVespertino == null) ? 0 : frequenciaVespertino
						.hashCode());
		result = prime * result
				+ ((saidaMatutino == null) ? 0 : saidaMatutino.hashCode());
		result = prime * result
				+ ((saidaNoturno == null) ? 0 : saidaNoturno.hashCode());
		result = prime * result
				+ ((saidaVespertino == null) ? 0 : saidaVespertino.hashCode());
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
		BoletimFrequenciaWrapper other = (BoletimFrequenciaWrapper) obj;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		if (entradaMatutino == null) {
			if (other.entradaMatutino != null)
				return false;
		} else if (!entradaMatutino.equals(other.entradaMatutino))
			return false;
		if (entradaNoturno == null) {
			if (other.entradaNoturno != null)
				return false;
		} else if (!entradaNoturno.equals(other.entradaNoturno))
			return false;
		if (entradaVespertino == null) {
			if (other.entradaVespertino != null)
				return false;
		} else if (!entradaVespertino.equals(other.entradaVespertino))
			return false;
		if (frequenciaMatutino == null) {
			if (other.frequenciaMatutino != null)
				return false;
		} else if (!frequenciaMatutino.equals(other.frequenciaMatutino))
			return false;
		if (frequenciaNoturno == null) {
			if (other.frequenciaNoturno != null)
				return false;
		} else if (!frequenciaNoturno.equals(other.frequenciaNoturno))
			return false;
		if (frequenciaVespertino == null) {
			if (other.frequenciaVespertino != null)
				return false;
		} else if (!frequenciaVespertino.equals(other.frequenciaVespertino))
			return false;
		if (saidaMatutino == null) {
			if (other.saidaMatutino != null)
				return false;
		} else if (!saidaMatutino.equals(other.saidaMatutino))
			return false;
		if (saidaNoturno == null) {
			if (other.saidaNoturno != null)
				return false;
		} else if (!saidaNoturno.equals(other.saidaNoturno))
			return false;
		if (saidaVespertino == null) {
			if (other.saidaVespertino != null)
				return false;
		} else if (!saidaVespertino.equals(other.saidaVespertino))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BoletimFrequenciaListaWrapper [dia=" + dia
				+ ", frequenciaMatutino=" + frequenciaMatutino
				+ ", entradaMatutino=" + entradaMatutino + ", saidaMatutino="
				+ saidaMatutino + ", frequenciaVespertino="
				+ frequenciaVespertino + ", entradaVespertino="
				+ entradaVespertino + ", saidaVespertino=" + saidaVespertino
				+ ", frequenciaNoturno=" + frequenciaNoturno
				+ ", entradaNoturno=" + entradaNoturno + ", saidaNoturno="
				+ saidaNoturno + "]";
	}

}

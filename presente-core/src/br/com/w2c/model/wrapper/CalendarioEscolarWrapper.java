package br.com.w2c.model.wrapper;


/**
 * @author charlles
 * @since 08/12/2013
 */
public class CalendarioEscolarWrapper {

	private String descricao;
	
	private String mes;
	
	private String dia;
	
	private Long status;
	
	private Boolean recorrente;
	
	private Boolean automatico;

	public CalendarioEscolarWrapper() {
	}

	public CalendarioEscolarWrapper(String descricao, String mes, String dia,
			Long status, Boolean recorrente, Boolean automatico) {
		super();
		this.descricao = descricao;
		this.mes = mes;
		this.dia = dia;
		this.status = status;
		this.recorrente = recorrente;
		this.automatico = automatico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Boolean getRecorrente() {
		return recorrente;
	}

	public void setRecorrente(Boolean recorrente) {
		this.recorrente = recorrente;
	}

	public Boolean getAutomatico() {
		return automatico;
	}

	public void setAutomatico(Boolean automatico) {
		this.automatico = automatico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((automatico == null) ? 0 : automatico.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result
				+ ((recorrente == null) ? 0 : recorrente.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		CalendarioEscolarWrapper other = (CalendarioEscolarWrapper) obj;
		if (automatico == null) {
			if (other.automatico != null)
				return false;
		} else if (!automatico.equals(other.automatico))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		if (recorrente == null) {
			if (other.recorrente != null)
				return false;
		} else if (!recorrente.equals(other.recorrente))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CalendarioEscolarWrapper [descricao=" + descricao + ", mes="
				+ mes + ", dia=" + dia + ", status=" + status + ", recorrente="
				+ recorrente + ", automatico=" + automatico + "]";
	}
	
}

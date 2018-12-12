package com.presente.backend.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author charlles
 */
@Entity
@Table(name="calendario_escolar")
public class CalendarioEscolar extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_calendario_escolar")
	private Long id;
	
	@Column(name="str_descricao", nullable=false)
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dat_data", nullable=false)
	private Date data;
	
	/**
	 * Status '1' para incluir e '2' para excluir a data do calendario escolar
	 * @see br.com.w2c.model.enums.StatusCalendarioEscolar
	 */
	@Column(name="num_status", nullable=false)
	private Long status;
	
	@Column(name="bol_recorrente", nullable=false)
	private Boolean recorrente;
	
	/**
	 * Se foi salvo automatico ou manual
	 */
	@Column(name="bol_automatico", nullable=false)
	private Boolean automatico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CalendarioEscolar other = (CalendarioEscolar) obj;
		if (automatico == null) {
			if (other.automatico != null)
				return false;
		} else if (!automatico.equals(other.automatico))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return "CalendarioEscolar [id=" + id + ", descricao=" + descricao
				+ ", data=" + data + ", status=" + status + ", recorrente="
				+ recorrente + ", automatico=" + automatico + "]";
	}
	
}

package br.com.w2c.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author charlles
 * @since 21/09/2013
 */
@NamedQueries({
	@NamedQuery(name="ParametroGeral.obterPorChave", query="select pg from ParametroGeral pg where upper(pg.chave) = upper(:chave)"),
	@NamedQuery(name="ParametroGeral.obterPorChaves", query="select pg from ParametroGeral pg where pg.chave in (:chaves)"),
	@NamedQuery(name="ParametroGeral.obterPorChaveIdentificador", query="select pg from ParametroGeral pg where upper(pg.chave) = upper(:chave) and pg.identificador = :identificador")
})
@Entity
@Table(name="parametro_geral")
public class ParametroGeral extends BaseEntity {

	private static final long serialVersionUID = -1256171758364246603L;
	
	@Id
	@Column(name="str_chave")
	private String chave;
	
	@Column(name="str_valor")
	private String valor;

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chave == null) ? 0 : chave.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		ParametroGeral other = (ParametroGeral) obj;
		if (chave == null) {
			if (other.chave != null)
				return false;
		} else if (!chave.equals(other.chave))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ParametroGeral [chave=" + chave + ", valor=" + valor + "]";
	}

}

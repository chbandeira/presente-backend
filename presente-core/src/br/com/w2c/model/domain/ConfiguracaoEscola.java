package br.com.w2c.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author charlles
 * @since 29/10/2013
 */
@NamedQueries({
	@NamedQuery(name="ConfiguracaoEscola.obterIdentificador", query="select ce.identificador from ConfiguracaoEscola ce order by ce.id desc"),
	@NamedQuery(name="ConfiguracaoEscola.obterConfiguracoesEscola", query="select ce from ConfiguracaoEscola ce order by ce.id desc"),
	@NamedQuery(name="ConfiguracaoEscola.obterNomeEscola", query="select ce.nome from ConfiguracaoEscola ce order by ce.id desc")
})
@Entity
@Table(name="configuracao_escola")
public class ConfiguracaoEscola extends BaseEntity {

	private static final long serialVersionUID = 2866787625224500881L;
	
	@Id
	@SequenceGenerator(name="seq_configuracao_escola", sequenceName="seq_configuracao_escola")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_configuracao_escola")
	@Column(name="id_configuracao_escola")
	private Long id;
	
	@Column(name="str_codigo")
	private String codigo;

	@Column(name="str_nome", nullable=false)
	private String nome;
	
	@Column(name="str_ua")
	private String ua;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dat_inicio_ano_letivo")
	private Date inicioAnoLetivo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dat_fim_ano_letivo")
	private Date fimAnoLetivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public Date getInicioAnoLetivo() {
		return inicioAnoLetivo;
	}

	public void setInicioAnoLetivo(Date inicioAnoLetivo) {
		this.inicioAnoLetivo = inicioAnoLetivo;
	}

	public Date getFimAnoLetivo() {
		return fimAnoLetivo;
	}

	public void setFimAnoLetivo(Date fimAnoLetivo) {
		this.fimAnoLetivo = fimAnoLetivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((fimAnoLetivo == null) ? 0 : fimAnoLetivo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((inicioAnoLetivo == null) ? 0 : inicioAnoLetivo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((ua == null) ? 0 : ua.hashCode());
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
		ConfiguracaoEscola other = (ConfiguracaoEscola) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (fimAnoLetivo == null) {
			if (other.fimAnoLetivo != null)
				return false;
		} else if (!fimAnoLetivo.equals(other.fimAnoLetivo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inicioAnoLetivo == null) {
			if (other.inicioAnoLetivo != null)
				return false;
		} else if (!inicioAnoLetivo.equals(other.inicioAnoLetivo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (ua == null) {
			if (other.ua != null)
				return false;
		} else if (!ua.equals(other.ua))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConfiguracaoEscola [id=" + id + ", codigo=" + codigo
				+ ", nome=" + nome + ", ua=" + ua + ", inicioAnoLetivo="
				+ inicioAnoLetivo + ", fimAnoLetivo=" + fimAnoLetivo + "]";
	}

}

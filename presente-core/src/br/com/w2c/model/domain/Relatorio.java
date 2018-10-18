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
 * @since 04/12/2013
 */
@NamedQueries({
	@NamedQuery(name="Relatorio.obterNome", query="select r.nome from Relatorio r where r.id = :idRelatorio")
})
@Entity
@Table(name="relatorio")
public class Relatorio extends BaseEntity {

	private static final long serialVersionUID = 1613007956697161502L;
	
	@Id
	@Column(name="id_relatorio")
	private Long id;
	
	@Column(name="str_nome", nullable=false)
    private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Relatorio [id=" + id + ", nome=" + nome + "]";
	}

}

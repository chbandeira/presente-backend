package com.presente.backend.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author charlles
 */
@Entity
@Table(name="relatorio")
public class Relatorio extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
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

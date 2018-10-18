package br.com.w2c.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe para autorizacao do usuário
 * @author charlles
 * @version 2.0 - 06/10/2013
 * @since 23/01/2012
 */
@NamedQueries({
	@NamedQuery(name="Perfil.obterPorNome", query="select p from Perfil p where p.nome = :nome"),
	@NamedQuery(name="Perfil.obterTodos", query="select p from Perfil p"),
	@NamedQuery(name="Perfil.obterTodosSemAdmin", query="select p from Perfil p where p.perfil <> :perfil")
})
@Entity
@Table(name="perfil")
public class Perfil extends BaseEntity {

	private static final long serialVersionUID = -4945007444274089234L;
	
	@Id
	@Column(name="str_perfil", nullable=false)
    private String perfil;
	
	@Column(name="str_nome", nullable=false)
    private String nome;

    public Perfil() {}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
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
		Perfil other = (Perfil) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Perfil [perfil=" + perfil + ", nome=" + nome + "]";
	}

}

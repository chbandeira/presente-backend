package br.com.w2c.model.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe de usuario
 * @author charlles
 * @version 2.0 - 06/10/2013
 * @since 23/01/2012
 */
@Entity
@Table(name="usuario")
public class Usuario extends BaseEntity {

	private static final long serialVersionUID = -304732953368465447L;

	@Id
	@Column(name="str_login", nullable=false)
    private String login;
	
	@Column(name="str_nome", nullable=false)
    private String nome;
	
	@Column(name="str_senha", nullable=false)
	private String senha;
    
	@Column(name="bol_ativo", nullable=false, columnDefinition="boolean default true", insertable=false, updatable=true)
    private Boolean ativo;
    
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name = "usuario_perfil",
    	joinColumns = {@JoinColumn(name="str_login")},
    	inverseJoinColumns = {@JoinColumn(name="str_perfil")} )
    private List<Perfil> perfil;

    public Usuario() {}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Perfil> getPerfil() {
		return perfil;
	}

	public void setPerfil(List<Perfil> perfil) {
		this.perfil = perfil;
	}

	/**
	 * Obtem string de perfis do usuario
	 * @return Texto com todos os perfis sepadados por vírgula
	 */
	public String getStringPerfis() {
		StringBuilder listaPerfis = new StringBuilder();
		for (Perfil perfil : getPerfil()) {
			listaPerfis.append(perfil.getNome().concat(", "));
		}
		if (listaPerfis != null && listaPerfis.length() >= 2) {
			return listaPerfis.substring(0, listaPerfis.length()-2).toString();
		} else {
			return "";
		}
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
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
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", nome=" + nome + ", senha="
				+ senha + ", ativo=" + ativo + ", perfil=" + perfil + "]";
	}
	
}

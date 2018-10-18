package br.com.w2c.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.w2c.controller.business.PerfilBO;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.util.Constantes;

@ManagedBean
@SessionScoped
public class PerfilMB extends BaseMB implements Serializable {

	private static final long serialVersionUID = -4201465337420493102L;
	
	protected PerfilBO perfilBO = getBean("perfilBO");
	
	/**
	 * perfis
	 */
	private boolean admin;
	private boolean diretor;
	private boolean coordenador;
	private boolean comum;

	protected Usuario usuario;
	
	public PerfilMB() {
		carregandoPerfis();
	}

	private void carregandoPerfis() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		usuario = (Usuario) session.getAttribute(Constantes.SESSION_USUARIO);
		admin = perfilBO.isPerfilAdministrador(usuario);
		diretor = perfilBO.isPerfilDiretor(usuario);
		coordenador = perfilBO.isPerfilCoordenador(usuario);
		comum = perfilBO.isPerfilUsuarioComum(usuario);
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isDiretor() {
		return diretor;
	}

	public void setDiretor(boolean diretor) {
		this.diretor = diretor;
	}

	public boolean isCoordenador() {
		return coordenador;
	}

	public void setCoordenador(boolean coordenador) {
		this.coordenador = coordenador;
	}

	public boolean isComum() {
		return comum;
	}

	public void setComum(boolean comum) {
		this.comum = comum;
	}
	
}

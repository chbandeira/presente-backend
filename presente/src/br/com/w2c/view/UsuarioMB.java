package br.com.w2c.view;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.w2c.controller.business.PerfilBO;
import br.com.w2c.controller.business.UsuarioBO;
import br.com.w2c.view.exception.AplicacaoFacesException;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.domain.Perfil;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 12/10/2013
 */
@ManagedBean
@SessionScoped
public class UsuarioMB extends BaseMB {

	private Usuario usuario;
	private Usuario usuarioSelecionado;
	private Perfil perfil;
	private boolean alteracao;
	private boolean desabilitarAlteracao;
	private String senhaAtual;
	
	private DataModel<Usuario> usuarios;
	private List<Perfil> perfis = new ArrayList<Perfil>();
	
	private UsuarioBO usuarioBO = getBean("usuarioBO");
	private PerfilBO perfilBO = getBean("perfilBO");
	
	public UsuarioMB() throws AplicacaoException {
		iniciarEntidades();
	}
	
	private void carregarPerfis() throws AplicacaoException {
		perfis = perfilBO.obterPerfis(getUsuarioSession());
	}
	
	@Override
	protected void iniciarEntidades() throws AplicacaoException {
		usuario = new Usuario();
		usuario.setPerfil(new ArrayList<Perfil>());
		usuarioSelecionado = new Usuario();
		usuarios = new ListDataModel<Usuario>();
		desabilitarAlteracao = false;
		carregarPerfis();
	}
	
	public void pageCadastro(ActionEvent event) throws AplicacaoException {
		if (!alteracao) {
			iniciarEntidades();
		} else {
			desabilitarAlteracao = true;
		}
		alteracao = false;
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CADASTRO_USUARIO);
	}
	
	public void pageConsulta(ActionEvent event) throws AplicacaoException {
		iniciarEntidades();
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_USUARIO);
	}

	public void salvar(ActionEvent event) throws AplicacaoFacesException {
		try {
			usuarioBO.salvar(usuario, perfil, desabilitarAlteracao);
			mensagemSalvoSucesso();
			iniciarEntidades();
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void consultar(ActionEvent event) throws AplicacaoFacesException {
		try {
			List<Usuario> lista = usuarioBO.obterListaPorParametros(usuario);
			usuarios = new ListDataModel<Usuario>(lista);
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void alterar(ActionEvent event) throws AplicacaoException {
		alteracao = true;
		usuario = usuarios.getRowData();
		pageCadastro(event);
	}
	
	public void excluir(ActionEvent event) throws AplicacaoFacesException {
		try {
			usuarioBO.desativar(usuarioSelecionado);
			mensagemExcluidoSucesso();
			consultar(event);
			iniciarEntidades();
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public DataModel<Usuario> getUsuarios() throws AplicacaoFacesException {
		if (!isNuloOuVazio(usuario) && 
				(!isNuloOuVazio(usuario.getNome()) || !isNuloOuVazio(usuario.getLogin()))) {
			consultar(null);
		}
		return usuarios;
	}
	
	public void pageAlterarSenha(ActionEvent event) {
		senhaAtual = "";
		redirect.redirecionaPaginaJSF(Constantes.PAGE_ALTERAR_SENHA);
	}
	
	public void alterarSenha(ActionEvent event) throws AplicacaoException {
		try {
			usuarioBO.alterarSenha(usuario, getUsuarioSession(), senhaAtual);
			senhaAtual = "";
			mensagemSalvoSucesso();
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		}
	}
	
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public boolean isAlteracao() {
		return alteracao;
	}

	public void setAlteracao(boolean alteracao) {
		this.alteracao = alteracao;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public boolean isDesabilitarAlteracao() {
		return desabilitarAlteracao;
	}

	public void setDesabilitarAlteracao(boolean desabilitarAlteracao) {
		this.desabilitarAlteracao = desabilitarAlteracao;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}
	
}

package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.chbandeira.utilitario.UtilSecurity;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.UsuarioDAO;
import br.com.w2c.model.domain.Perfil;
import br.com.w2c.model.domain.Responsavel;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.util.CriptografadorUtil;

/**
 * 
 * @author charlles
 * @since 12/10/2013
 */
@Component
public class UsuarioBO extends BaseBO<Usuario> {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private PerfilBO perfilBO;
	
	public void validarCamposObrigatorios(Usuario usuario, Perfil perfil) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(usuario)) {
			lancarMensagemSuportePadrao();
		} else { 
			if (isNuloOuVazio(usuario.getNome())) {
				addMensagemCampo("usuario");
			}
			if (isNuloOuVazio(usuario.getLogin())) {
				addMensagemCampo("login");
			}
			if (isNuloOuVazio(usuario.getSenha())) {
				addMensagemCampo("senha");
			}
			if (isNuloOuVazio(perfil)
					|| isNuloOuVazio(perfil.getPerfil())) {
				addMensagemCampo("perfil");
			}
			validarMensagens();
		}
	}
	
	private void validarCampoSenha(Usuario usuario) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(usuario)) {
			lancarMensagemSuportePadrao();
		} else if (isNuloOuVazio(usuario.getSenha())) {
			addMensagemCampo("senha");
		} else if (usuario.getSenha().length() < 6) {
			addMensagem("MSG049");
		}
 	}

	private void validarSenhaAtual(String senhaAtual, String login) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(senhaAtual)) {
			addMensagemCampo("senhaAtual");
		} else {
			try {
				if (!usuarioDAO.isSenhaAtualLogin(UtilSecurity.getSha256(senhaAtual), login)) {
					addMensagem("MSG048");
				}
			} catch (NoSuchAlgorithmException e) {
				lancarMensagemSuportePadrao();
			}
		}
	}

	public void validarCamposParaExclusao(Usuario usuario) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(usuario) || isNuloOuVazio(usuario.getLogin())) {
			lancarMensagemSuportePadrao();
		}
	}
	
	public void validarExisteCadastrado(Usuario usuario) throws NegocioException, AplicacaoException {
		if (existe(usuario)) {
			addMensagemParametros("MSG007", "usuario");
		}
		validarCamposObrigatorios(usuario);
	}
	
	public boolean existe(Usuario usuario) throws AplicacaoException {
		Usuario usuarioObtido = usuarioDAO.obterPorLogin(usuario.getLogin());
		return !isNuloOuVazio(usuarioObtido);
	}
	
	public Usuario obterPorLogin(String login) throws AplicacaoException {
		if (!isNuloOuVazio(login)) {
			return usuarioDAO.obterPorLogin(login);
		} else {
			return null;
		}
	}
	
	private void removePerfisUsuario(Usuario usuario) throws NegocioException {
		usuario.setPerfil(new ArrayList<Perfil>());
	}

	private void addPerfilUsuario(Usuario usuario, Perfil perfil) throws NegocioException {
		if (!isNuloOuVazio(perfil) && !usuario.getPerfil().contains(perfil)) {
			usuario.getPerfil().add(perfil);
		}
	}

	private void criptografarSenha(Usuario usuario) {
		String senhaCriptografada = CriptografadorUtil.getInstance().criptografarSHA256(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
	}

	public List<Usuario> obterListaPorParametros(Usuario usuario) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(usuario)) {
			lancarMensagemSuportePadrao();
		}
		return usuarioDAO.obterListaPorParametros(usuario);
	}

	public void desativar(Usuario usuario) throws NegocioException, AplicacaoException {
		validarCamposParaExclusao(usuario);
		usuario = usuarioDAO.obterPorLogin(usuario.getLogin());
		validarCamposParaExclusao(usuario);
		validarMensagens();
	
		usuario.setAtivo(Boolean.FALSE);
		usuario.setDataUltimaAtualizacao(null);
		usuarioDAO.salvar(usuario);
	}
	
	public Usuario salvar(Usuario usuario, Perfil perfil, boolean alteracao) throws NegocioException, AplicacaoException {
		validarCamposObrigatorios(usuario, perfil);
		if (alteracao) {
			/*
			 * Se for alteração, remove todos os perfis de usuário.
			 * Futuramente pode ter alteração caso for utilizar lógica de mais de um perfil.
			 */
			removePerfisUsuario(usuario);
		} else {
			validarExisteCadastrado(usuario);
		}
		validarMensagens();
		addPerfilUsuario(usuario, perfil);
		trimString(usuario.getLogin(), usuario.getNome(), usuario.getSenha());
		criptografarSenha(usuario);
	
		usuario.setDataUltimaAtualizacao(null);
		usuarioDAO.salvar(usuario);
		
		return usuario;
	}
	
	public void alterarSenha(Usuario usuario, Usuario usuarioSession, String senhaAtual) throws NegocioException, AplicacaoException {
		validarCampoSenha(usuario);
		validarSenhaAtual(senhaAtual, usuarioSession.getLogin());
		validarMensagens();
		usuarioSession.setSenha(usuario.getSenha());
		trimString(usuarioSession.getSenha());
		criptografarSenha(usuarioSession);
		usuarioSession.setDataUltimaAtualizacao(null);
		usuarioDAO.salvar(usuarioSession);
	}
	
	public List<Usuario> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return usuarioDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, Usuario.class);
	}
	
	public void salvarOnline(Usuario usuario) throws AplicacaoException, NegocioException {
		Usuario retorno = obterPorCriterioOnline(usuario);
		
		if (retorno == null) {
			retorno = new Usuario();
			copiarPropriedades(retorno, usuario);
			retorno.setPerfil(perfilBO.obterPerfis(retorno.getPerfil()));
		}
		
		usuarioDAO.salvar(retorno);
	}

	public Usuario obterPorCriterioOnline(Usuario usuario)
			throws AplicacaoException, NegocioException {
		List<Usuario> lista = usuarioDAO.obterPorCriterioOnline(usuario);
		String objeto = getLabel("login") + " " + usuario.getLogin();
		Usuario retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}
	
	public Usuario criarUsuarioPorResponsavel(Responsavel responsavel, boolean alteracao) throws NegocioException, AplicacaoException {
		if (responsavel != null
				&& responsavel.getCpf() != null
				&& !responsavel.getCpf().trim().equals("")
				&& responsavel.getNome() != null
				&& !responsavel.getNome().trim().equals("")) {
			
			if (!alteracao) {
				responsavel.setUsuario(new Usuario());
				responsavel.getUsuario().setLogin(responsavel.getCpf().trim());
				responsavel.getUsuario().setSenha(responsavel.getCpf().trim());
				responsavel.getUsuario().setAtivo(true);
				responsavel.getUsuario().setPerfil(new ArrayList<Perfil>());
			}
			responsavel.getUsuario().setNome(responsavel.getNome());
			
			Perfil perfil = perfilBO.obterPorEnum(br.com.w2c.model.enums.Perfil.RESPONSAVEL);
			salvar(responsavel.getUsuario(), perfil, alteracao);
		}
		return responsavel.getUsuario();
	}

	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

}

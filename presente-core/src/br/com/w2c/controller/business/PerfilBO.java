package br.com.w2c.controller.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.PerfilDAO;
import br.com.w2c.model.domain.Perfil;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 13/10/2013
 */
@Component
public class PerfilBO extends BaseBO<Perfil> {

	@Autowired
	private PerfilDAO perfilDAO;
	
	public Perfil obterPorNome(String nomePerfil) throws AplicacaoException {
		return perfilDAO.obterPorNome(nomePerfil);
	}

	/**
	 * 
	 * @param usuarioSession É o usuário logado
	 * @return
	 * @throws AplicacaoException 
	 */
	public List<Perfil> obterPerfis(Usuario usuarioSession) throws AplicacaoException {
		List<Perfil> perfis = null;
		if (isPerfilAdministrador(usuarioSession)) {
			perfis = perfilDAO.obterTodos();
		} else {
			perfis = perfilDAO.obterTodosSemAdmin();
		}
		return perfis;
	}
	
	/**
	 * 
	 * @param usuario
	 * @param constantePerfil br.com.w2c.util.Constantes
	 * @return
	 */
	public boolean checkPerfil(Usuario usuario, String constantePerfil) {
		boolean perfil = false;
		for (Perfil p : usuario.getPerfil()) {
			if (p.getPerfil().equalsIgnoreCase(constantePerfil)) {
				perfil = true;
			}
		}
		return perfil;
	}
	
	public boolean isPerfilAdministrador(Usuario usuario) {
		return checkPerfil(usuario, Constantes.ADMIN);
	}
	
	public boolean isPerfilDiretor(Usuario usuario) {
		return checkPerfil(usuario, Constantes.DIRETOR);
	}
	
	public boolean isPerfilCoordenador(Usuario usuario) {
		return checkPerfil(usuario, Constantes.COORDENADOR);
	}
	
	public boolean isPerfilUsuarioComum(Usuario usuario) {
		return checkPerfil(usuario, Constantes.COMUM);
	}
	
	public boolean isPerfilResponsavel(Usuario usuario) {
		return checkPerfil(usuario, Constantes.RESPONSAVEL);
	}

	public List<Perfil> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return perfilDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, Perfil.class);
	}

	public void salvarOnline(Perfil perfil) throws AplicacaoException, NegocioException {
		Perfil retorno = obterPorCriterioOnline(perfil);
		
		if (retorno == null) {
			retorno = new Perfil();
			copiarPropriedades(retorno, perfil);
		}
		else {
			retorno.setNome(perfil.getNome());
		}
		
		retorno.setDataUltimaAtualizacao(perfil.getDataUltimaAtualizacao());
		
		perfilDAO.salvar(retorno);
	}

	public Perfil obterPorCriterioOnline(Perfil perfil)
			throws AplicacaoException, NegocioException {
		List<Perfil> lista = perfilDAO.obterPorCriterioOnline(perfil);
		String objeto = getLabel("perfil") + " " + perfil.getNome();
		Perfil retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}
	
	public Perfil obterPorId(String id) {
		return perfilDAO.obter(Perfil.class, id);
	}
	
	public Perfil obterPorEnum(br.com.w2c.model.enums.Perfil perfil) {
		return obterPorId(perfil.getPerfil());
	}
	
	public List<Perfil> obterPerfis(List<Perfil> perfis) {
		List<Perfil> perfisEncontrados = new ArrayList<>();
		
		for (Perfil perfil : perfis) {
			Perfil perfilEncontrado = obterPorId(perfil.getPerfil());
			if (perfilEncontrado != null) {
				perfisEncontrados.add(perfilEncontrado);
			}
		}
		
		return perfisEncontrados;
	}
	
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public PerfilDAO getPerfilDAO() {
		return perfilDAO;
	}

	public void setPerfilDAO(PerfilDAO perfilDAO) {
		this.perfilDAO = perfilDAO;
	}

}

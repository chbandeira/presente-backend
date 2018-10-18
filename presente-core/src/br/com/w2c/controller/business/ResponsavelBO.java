package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.ResponsavelDAO;
import br.com.w2c.model.domain.Responsavel;

/**
 * 
 * @author charlles
 * @since 18/10/2013
 */
@Component
public class ResponsavelBO extends BaseBO<Responsavel> {

	@Autowired
	private ResponsavelDAO responsavelDAO;
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	
	@Deprecated
	public void validarCamposObrigatorios(Responsavel responsavel) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(responsavel)) {
			lancarMensagemSuportePadrao();
		} else { 
			if (isNuloOuVazio(responsavel.getEmail())) {
				addMensagem("MSG038");
			}
			if (isNuloOuVazio(responsavel.getTelefoneCelular())) {
				addMensagem("MSG051");
			}
			validarMensagens();
		}
	}
	
	@Deprecated
	public void salvar(Responsavel responsavel, boolean enviarEmailRegistro) throws NegocioException, AplicacaoException {
		if (enviarEmailRegistro) {
			validarCamposObrigatorios(responsavel);
		}
		validarMensagens();
		trimString(responsavel.getEmail(), responsavel.getNome());
	
		responsavel.setDataUltimaAtualizacao(null);
		responsavelDAO.salvar(responsavel);
	}
	
	public void validarCampoEmail(Responsavel responsavel) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(responsavel)) {
			lancarMensagemSuportePadrao();
		} else { 
			if (isNuloOuVazio(responsavel.getEmail())) {
				addMensagem("MSG038");
			}
			validarMensagens();
		}
	}
	
	public void validarCampoSms(Responsavel responsavel) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(responsavel)) {
			lancarMensagemSuportePadrao();
		} else { 
			if (isNuloOuVazio(responsavel.getTelefoneCelular())) {
				addMensagem("MSG051");
			}
			validarMensagens();
		}
	}
	
	@Transactional
	public void salvar(Responsavel responsavel, Boolean enviarEmailRegistro, Boolean enviarSmsRegistro) 
			throws NegocioException, AplicacaoException {
		
		Responsavel responsavelPersistir = null;
		
		if (enviarEmailRegistro) {
			validarCampoEmail(responsavel);
		}
		if (enviarSmsRegistro) {
			validarCampoSms(responsavel);
		}
		validarMensagens();
		
		boolean alteracao = responsavel.getId() != null;
		if (alteracao) {
			responsavelPersistir = responsavelDAO.obter(Responsavel.class, responsavel.getId());
			responsavelPersistir.setCpf(responsavel.getCpf());
			responsavelPersistir.setDataUltimaAtualizacao(UtilDate.getDataAtual());
			responsavelPersistir.setEmail(responsavel.getEmail());
			responsavelPersistir.setNome(responsavel.getNome());
			responsavelPersistir.setTelefoneCelular(responsavel.getTelefoneCelular());
			responsavelPersistir.setTelefoneFixo(responsavel.getTelefoneFixo());
		}
		else {
			responsavelPersistir = responsavel;
		}
		
		trimString(responsavelPersistir.getNome(), responsavelPersistir.getEmail(), responsavelPersistir.getTelefoneCelular());
	
		responsavelPersistir.setUsuario(usuarioBO.criarUsuarioPorResponsavel(responsavelPersistir, alteracao));
		
		responsavelPersistir.setDataUltimaAtualizacao(null);
		responsavelDAO.salvar(responsavelPersistir);
	}
	
	public List<Responsavel> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return responsavelDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, Responsavel.class);
	}
	
	public void salvarOnline(Responsavel responsavel) throws AplicacaoException, NegocioException {
		Responsavel retorno = obterPorCriterioOnline(responsavel);
		Long id = null;
		
		if (retorno == null) {
			retorno = new Responsavel();
		}
		else{
			id = retorno.getId();
		}
		
		copiarPropriedades(retorno, responsavel);
		retorno.setId(id);
		if (retorno.getUsuario() != null) {
			retorno.setUsuario(usuarioBO.obterPorCriterioOnline(retorno.getUsuario()));
		}

		responsavelDAO.salvar(retorno);
	}

	public Responsavel obterPorCriterioOnline(Responsavel responsavel)
			throws AplicacaoException, NegocioException {
		List<Responsavel> lista = responsavelDAO.obterPorCriterioOnline(responsavel);
		String objeto = getLabel("responsavel") + " " + responsavel.getNome();
		Responsavel retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}

	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public ResponsavelDAO getResponsavelDAO() {
		return responsavelDAO;
	}

}

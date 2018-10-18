package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.TipoOcorrenciaDAO;
import br.com.w2c.model.domain.TipoOcorrencia;
import br.com.w2c.model.domain.Usuario;

/**
 * 
 * @author charlles
 * @since 19/05/2015
 */
@Component
public class TipoOcorrenciaBO extends BaseBO<TipoOcorrencia> {

	@Autowired
	private TipoOcorrenciaDAO tipoOcorrenciaDAO;
	
	@Override
	public void validarCamposParaExclusao(TipoOcorrencia tipoOcorrencia) throws NegocioException, AplicacaoException {
		super.validarCamposParaExclusao(tipoOcorrencia);
		if (isNuloOuVazio(tipoOcorrencia.getId())) {
			lancarMensagemSuportePadrao();
		}
	}
	
	public void validarCamposObrigatorios(TipoOcorrencia ocorrencia) throws NegocioException, AplicacaoException {
		super.validarCamposObrigatorios(ocorrencia);
		if (isNuloOuVazio(ocorrencia.getDescricao())) {
			addMensagemCampo("descricao");
		}
		validarMensagens();
	}
	
	public void salvar(TipoOcorrencia tipoOcorrencia) throws NegocioException, AplicacaoException {
		validarCamposObrigatorios(tipoOcorrencia);
		trimString(tipoOcorrencia.getDescricao());
		validarMensagens();

		tipoOcorrencia.setDataUltimaAtualizacao(null);
		tipoOcorrenciaDAO.salvar(tipoOcorrencia);
	}

	public List<TipoOcorrencia> obterListaPorParametros(TipoOcorrencia tipoOcorrencia) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(tipoOcorrencia)) {
			lancarMensagemSuportePadrao();
		}
		return tipoOcorrenciaDAO.obterListaPorParametros(tipoOcorrencia);
	}

	public void desativar(TipoOcorrencia tipoOcorrencia) throws NegocioException, AplicacaoException {
		validarCamposParaExclusao(tipoOcorrencia);
		tipoOcorrencia = tipoOcorrenciaDAO.obter(TipoOcorrencia.class, tipoOcorrencia.getId());
		validarCamposParaExclusao(tipoOcorrencia);
		validarMensagens();
	
		tipoOcorrencia.setAtivo(Boolean.FALSE);
		tipoOcorrencia.setDataUltimaAtualizacao(null);
		tipoOcorrenciaDAO.salvar(tipoOcorrencia);
	}
	
	public List<TipoOcorrencia> obterListaPorParametrosPesquisa(TipoOcorrencia tipoOcorrencia) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(tipoOcorrencia)) {
			lancarMensagemSuportePadrao();
		}
		return tipoOcorrenciaDAO.obterListaPorParametros(tipoOcorrencia);
	}

	public TipoOcorrencia obterPorDescricao(String descricao) throws AplicacaoException {
		return tipoOcorrenciaDAO.obterPorDescricao(new TipoOcorrencia(descricao));
	}
	
	public List<TipoOcorrencia> obterListaPorSugestaoDescricao(String descricao) throws AplicacaoException {
		return tipoOcorrenciaDAO.obterListaPorSugestaoDescricao(descricao);
	}
	
	public List<TipoOcorrencia> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return tipoOcorrenciaDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, TipoOcorrencia.class);
	}
	
	public void salvarOnline(TipoOcorrencia tipoOcorrencia) throws AplicacaoException, NegocioException {
		TipoOcorrencia retorno = obterPorCriterioOnline(tipoOcorrencia);
		Long id = null;
		
		if (retorno == null) {
			retorno = new TipoOcorrencia();
		}
		else{
			id = retorno.getId();
		}
		
		copiarPropriedades(retorno, tipoOcorrencia);
		retorno.setId(id);
		
		tipoOcorrenciaDAO.salvar(retorno);
	}

	public TipoOcorrencia obterPorCriterioOnline(TipoOcorrencia tipoOcorrencia)
			throws AplicacaoException, NegocioException {
		List<TipoOcorrencia> lista = tipoOcorrenciaDAO.obterPorCriterioOnline(tipoOcorrencia);
		String objeto = getLabel("tipoOcorrencia") + " " + tipoOcorrencia.getDescricao();
		TipoOcorrencia retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}
	
	public List<TipoOcorrencia> obterListaPorSugestaoDescricao(String descricao, Usuario usuario) throws AplicacaoException {
		if (usuario == null) {
			return obterListaPorSugestaoDescricao(descricao);
		}
		else {
			return tipoOcorrenciaDAO.obterListaPorSugestaoDescricaoEUsuario(descricao, usuario);
		}
	}
	
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public TipoOcorrenciaDAO getTipoOcorrenciaDAO() {
		return tipoOcorrenciaDAO;
	}

}

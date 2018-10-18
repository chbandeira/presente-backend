package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.SerieDAO;
import br.com.w2c.model.domain.Serie;
import br.com.w2c.model.enums.TipoHistoricoAlteracao;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
@Component
public class SerieBO extends BaseBO<Serie> {

	@Autowired
	private SerieDAO serieDAO;
	@Autowired
	private HistoricoAlteracaoBO historicoAlteracaoBO;
	
	@Override
	public void validarCamposParaExclusao(Serie serie) throws NegocioException, AplicacaoException {
		super.validarCamposParaExclusao(serie);
		if (isNuloOuVazio(serie.getId())) {
			lancarMensagemSuportePadrao();
		}
	}
	
	public void validarCamposObrigatorios(Serie serie) throws NegocioException, AplicacaoException {
		super.validarCamposObrigatorios(serie);
		if (isNuloOuVazio(serie.getDescricao())) {
			addMensagemCampo("serie");
		}
		validarMensagens();
	}
	
	public void validarExisteCadastrada(Serie serie) throws NegocioException, AplicacaoException {
		if (existe(serie)) {
			addMensagemParametros("MSG054", "serie");
		}
		validarMensagens();
	}

	public boolean existe(Serie serie) throws AplicacaoException {
		Serie serieObtida = serieDAO.obterRepetido(serie);
		return !isNuloOuVazio(serieObtida);
	}
	
	public void salvar(Serie serie) throws NegocioException, AplicacaoException {
		validarCamposObrigatorios(serie);
		validarExisteCadastrada(serie);
		validarMensagens();

		TipoHistoricoAlteracao tipo = historicoAlteracaoBO.getTipo(serie.getId());
		
		trimString(serie.getDescricao());
		serie.setDataUltimaAtualizacao(null);
		serieDAO.salvar(serie);
		
		historicoAlteracaoBO.salvar(serie, tipo);
	}

	public List<Serie> obterListaPorParametros(Serie serie) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(serie)) {
			lancarMensagemSuportePadrao();
		}
		return serieDAO.obterListaPorParametros(serie);
	}

	public void desativar(Serie serie) throws NegocioException, AplicacaoException {
		validarCamposParaExclusao(serie);
		serie = serieDAO.obter(Serie.class, serie.getId());
		validarCamposParaExclusao(serie);
		validarMensagens();
	
		serie.setAtivo(Boolean.FALSE);
		serie.setDataUltimaAtualizacao(null);
		serieDAO.salvar(serie);
		
		historicoAlteracaoBO.salvar(serie, TipoHistoricoAlteracao.DESATIVACAO);
	}
	
	public List<Serie> obterListaPorSugestaoDescricao(String descricao) throws AplicacaoException {
		return serieDAO.obterListaPorSugestaoDescricao(descricao);
	}
	
	public Serie obterPorDescricao(String descricao) throws AplicacaoException {
		return serieDAO.obterPorParametrosUnicos(new Serie(descricao));
	}
	
	public List<Serie> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return serieDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, Serie.class);
	}
	
	public void salvarOnline(Serie serie) throws AplicacaoException, NegocioException {
		Serie retorno = obterPorCriterioOnline(serie);
		Long id = null;
		
		if (retorno == null) {
			retorno = new Serie();
		}
		else{
			id = retorno.getId();
		}
		
		copiarPropriedades(retorno, serie);
		retorno.setId(id);
		
		serieDAO.salvar(retorno);
	}

	public Serie obterPorCriterioOnline(Serie serie) throws AplicacaoException,
			NegocioException {
		List<Serie> lista = serieDAO.obterPorCriterioOnline(serie);
		String objeto = getLabel("serie") + " " + serie.getDescricao();
		Serie retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}
		
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public SerieDAO getSerieDAO() {
		return serieDAO;
	}

	public void setSerieDAO(SerieDAO serieDAO) {
		this.serieDAO = serieDAO;
	}

}

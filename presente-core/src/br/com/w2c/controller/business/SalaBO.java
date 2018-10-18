package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.SalaDAO;
import br.com.w2c.model.domain.Sala;
import br.com.w2c.model.enums.TipoHistoricoAlteracao;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
@Component
public class SalaBO extends BaseBO<Sala> {

	@Autowired
	private SalaDAO salaDAO;
	@Autowired
	private HistoricoAlteracaoBO historicoAlteracaoBO;
	
	public void validarCamposObrigatorios(Sala sala) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(sala)) {
			lancarMensagemSuportePadrao();
		} else { 
			if (isNuloOuVazio(sala.getDescricao())) {
				addMensagemCampo("sala");
			}
			validarMensagens();
		}
	}
	
	public void validarCamposParaExclusao(Sala sala) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(sala) || isNuloOuVazio(sala.getId())) {
			lancarMensagemSuportePadrao();
		}
	}
	
	public void validarExisteCadastrado(Sala sala) throws NegocioException, AplicacaoException {
		if (existe(sala)) {
			addMensagemParametros("MSG054", "sala");
		}
		validarCamposObrigatorios(sala);
	}

	public boolean existe(Sala sala) throws AplicacaoException {
		Sala salaObtida = salaDAO.obterRepetido(sala);
		return !isNuloOuVazio(salaObtida);
	}

	public void salvar(Sala sala) throws NegocioException, AplicacaoException {
		validarCamposObrigatorios(sala);
		validarExisteCadastrado(sala);
		validarMensagens();

		TipoHistoricoAlteracao tipo = historicoAlteracaoBO.getTipo(sala.getId());
		
		trimString(sala.getDescricao());
		sala.setDataUltimaAtualizacao(null);
		salaDAO.salvar(sala);
		
		historicoAlteracaoBO.salvar(sala, tipo);
	}

	public List<Sala> obterListaPorParametros(Sala sala) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(sala)) {
			lancarMensagemSuportePadrao();
		}
		return salaDAO.obterListaPorParametros(sala);
	}

	public void desativar(Sala sala) throws NegocioException, AplicacaoException {
		validarCamposParaExclusao(sala);
		sala = salaDAO.obter(Sala.class, sala.getId());
		validarCamposParaExclusao(sala);
		validarMensagens();
	
		sala.setAtivo(Boolean.FALSE);
		sala.setDataUltimaAtualizacao(null);
		salaDAO.salvar(sala);
		
		historicoAlteracaoBO.salvar(sala, TipoHistoricoAlteracao.DESATIVACAO);
	}
	
	public List<Sala> obterListaPorSugestaoDescricao(String descricao) throws AplicacaoException {
		return salaDAO.obterListaPorSugestaoDescricao(descricao);
	}
	
	public Sala obterPorDescricao(String descricao) throws AplicacaoException {
		return salaDAO.obterPorParametrosUnicos(new Sala(descricao));
	}
	
	public List<Sala> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return salaDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, Sala.class);
	}
	
	public void salvarOnline(Sala sala) throws AplicacaoException, NegocioException {
		Sala retorno = obterPorCriterioOnline(sala);
		Long id = null;
		
		if (retorno == null) {
			retorno = new Sala();
		}
		else{
			id = retorno.getId();
		}
		
		copiarPropriedades(retorno, sala);
		retorno.setId(id);
		
		salaDAO.salvar(retorno);
	}

	public Sala obterPorCriterioOnline(Sala sala) throws AplicacaoException,
			NegocioException {
		List<Sala> lista = salaDAO.obterPorCriterioOnline(sala);
		String objeto = getLabel("sala") + " " + sala.getDescricao();
		Sala retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}
	
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public SalaDAO getSalaDAO() {
		return salaDAO;
	}

	public void setSalaDAO(SalaDAO salaDAO) {
		this.salaDAO = salaDAO;
	}

}

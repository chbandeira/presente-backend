package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.DisciplinaDAO;
import br.com.w2c.model.domain.Disciplina;

/**
 * 
 * @author charlles
 * @since 05/05/2015
 */
@Component
public class DisciplinaBO extends BaseBO<Disciplina> {

	@Autowired
	private DisciplinaDAO disciplinaDAO;
	
	@Override
	public void validarCamposParaExclusao(Disciplina disciplina) throws NegocioException, AplicacaoException {
		super.validarCamposParaExclusao(disciplina);
		if (isNuloOuVazio(disciplina.getId())) {
			lancarMensagemSuportePadrao();
		}
	}
	
	public void validarCamposObrigatorios(Disciplina disciplina) throws NegocioException, AplicacaoException {
		super.validarCamposObrigatorios(disciplina);
		if (isNuloOuVazio(disciplina.getNome())) {
			addMensagemCampo("disciplina");
		}
		validarMensagens();
	}
	
	public void validarExisteCadastrada(Disciplina disciplina) throws NegocioException, AplicacaoException {
		if (existe(disciplina)) {
			addMensagemParametros("MSG054", "disciplina");
		}
		validarMensagens();
	}

	public boolean existe(Disciplina disciplina) throws AplicacaoException {
		Disciplina disciplinaObtida = disciplinaDAO.obterRepetido(disciplina);
		return !isNuloOuVazio(disciplinaObtida);
	}
	
	public void salvar(Disciplina disciplina) throws NegocioException, AplicacaoException {
		validarCamposObrigatorios(disciplina);
		trimString(disciplina.getNome());
		validarExisteCadastrada(disciplina);
		validarMensagens();

		disciplina.setDataUltimaAtualizacao(null);
		disciplinaDAO.salvar(disciplina);
	}

	public List<Disciplina> obterListaPorParametros(Disciplina disciplina) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(disciplina)) {
			lancarMensagemSuportePadrao();
		}
		return disciplinaDAO.obterListaPorParametros(disciplina);
	}

	public void desativar(Disciplina disciplina) throws NegocioException, AplicacaoException {
		validarCamposParaExclusao(disciplina);
		disciplina = disciplinaDAO.obter(Disciplina.class, disciplina.getId());
		validarCamposParaExclusao(disciplina);
		validarMensagens();
	
		disciplina.setAtivo(Boolean.FALSE);
		disciplina.setDataUltimaAtualizacao(null);
		disciplinaDAO.salvar(disciplina);
	}
	
	public List<Disciplina> obterListaPorParametrosPesquisa(Disciplina disciplina) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(disciplina)) {
			lancarMensagemSuportePadrao();
		}
		return disciplinaDAO.obterListaPorParametros(disciplina);
	}
	
	public List<Disciplina> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return disciplinaDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, Disciplina.class);
	}
	
	public void salvarOnline(Disciplina disciplina) throws AplicacaoException, NegocioException {
		Disciplina retorno = obterPorCriterioOnline(disciplina);
		
		if (retorno == null) {
			retorno = new Disciplina();
			retorno.setIdentificador(disciplina.getIdentificador());
			retorno.setNome(disciplina.getNome());
		}
		
		retorno.setAtivo(disciplina.getAtivo());
		retorno.setCargaHoraria(disciplina.getCargaHoraria());
		retorno.setDataUltimaAtualizacao(disciplina.getDataUltimaAtualizacao());
		
		disciplinaDAO.salvar(retorno);
	}

	public Disciplina obterPorCriterioOnline(Disciplina disciplina)
			throws AplicacaoException, NegocioException {
		List<Disciplina> lista = disciplinaDAO.obterPorCriterioOnline(disciplina);
		String objeto = getLabel("disciplina") + " " + disciplina.getNome();
		Disciplina retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}
	
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public DisciplinaDAO getDisciplinaDAO() {
		return disciplinaDAO;
	}

	public void setDisciplinaDAO(DisciplinaDAO disciplinaDAO) {
		this.disciplinaDAO = disciplinaDAO;
	}

}

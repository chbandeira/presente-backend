package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.TurmaDAO;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.model.enums.TipoHistoricoAlteracao;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
@Component
public class TurmaBO extends BaseBO<Turma> {

	@Autowired
	private TurmaDAO turmaDAO;
	@Autowired
	private HistoricoAlteracaoBO historicoAlteracaoBO;
	
	@Override
	public void validarCamposObrigatorios(Turma turma) throws NegocioException, AplicacaoException {
		super.validarCamposObrigatorios(turma);
		if (isNuloOuVazio(turma.getDescricao())) {
			addMensagemCampo("turma");
		}
		validarMensagens();
	}
	
	@Override
	public void validarCamposParaExclusao(Turma turma) throws NegocioException, AplicacaoException {
		super.validarCamposParaExclusao(turma);
		if (isNuloOuVazio(turma.getId())) {
			lancarMensagemSuportePadrao();
		}
	}
	
	public void validarExisteCadastrado(Turma turma) throws NegocioException, AplicacaoException {
		if (existe(turma)) {
			addMensagemParametros("MSG054", "turma");
		}
		validarMensagens();
	}

	public boolean existe(Turma turma) throws AplicacaoException {
		Turma turmaObtida = turmaDAO.obterRepetido(turma);
		return !isNuloOuVazio(turmaObtida);
	}
	
	public void salvar(Turma turma) throws NegocioException, AplicacaoException {
		validarCamposObrigatorios(turma);
		validarExisteCadastrado(turma);
		validarMensagens();

		TipoHistoricoAlteracao tipo = historicoAlteracaoBO.getTipo(turma.getId());
		
		trimString(turma.getDescricao());
		turma.setDataUltimaAtualizacao(null);
		turmaDAO.salvar(turma);
		
		historicoAlteracaoBO.salvar(turma, tipo);
	}

	public List<Turma> obterListaPorParametros(Turma turma) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(turma)) {
			lancarMensagemSuportePadrao();
		}
		return turmaDAO.obterListaPorParametros(turma);
	}

	public void desativar(Turma turma) throws NegocioException, AplicacaoException {
		validarCamposParaExclusao(turma);
		turma = turmaDAO.obter(Turma.class, turma.getId());
		validarCamposParaExclusao(turma);
		validarMensagens();
	
		turma.setAtivo(Boolean.FALSE);
		turma.setDataUltimaAtualizacao(null);
		turmaDAO.salvar(turma);
		
		historicoAlteracaoBO.salvar(turma, TipoHistoricoAlteracao.DESATIVACAO);
	}
	
	public List<Turma> obterListaPorSugestaoDescricao(String descricao) throws AplicacaoException {
		return turmaDAO.obterListaPorSugestaoDescricao(descricao);
	}
	
	public Turma obterPorDescricao(String descricao) throws AplicacaoException {
		return turmaDAO.obterPorParametrosUnicos(new Turma(descricao));
	}
	
	public List<Turma> obterListaPorSugestaoDescricaoMatriculados(String descricao) throws AplicacaoException {
		return turmaDAO.obterListaPorSugestaoDescricaoMatriculados(descricao);
	}
	
	public List<Turma> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return turmaDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, Turma.class);
	}
	
	public void salvarOnline(Turma turma) throws AplicacaoException, NegocioException {
		Turma retorno = obterPorCriterioOnline(turma);
		Long id = null;
		
		if (retorno == null) {
			retorno = new Turma();
		}
		else{
			id = retorno.getId();
		}
		
		copiarPropriedades(retorno, turma);
		retorno.setId(id);
		
		turmaDAO.salvar(retorno);
	}

	public Turma obterPorCriterioOnline(Turma turma) throws AplicacaoException,
			NegocioException {
		List<Turma> lista = turmaDAO.obterPorCriterioOnline(turma);
		String objeto = getLabel("turma") + " " + turma.getDescricao();
		Turma retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}
	
	public List<Turma> obterListaPorSugestaoDescricaoMatriculados(
			String descricaoTurma, Usuario usuario) throws AplicacaoException {
		if (usuario == null) {
			return obterListaPorSugestaoDescricaoMatriculados(descricaoTurma);
		}
		else {
			return turmaDAO.obterListaPorSugestaoDescricaoMatriculadosEUsuario(descricaoTurma, usuario);
		}
	}
	
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public TurmaDAO getTurmaDAO() {
		return turmaDAO;
	}


}

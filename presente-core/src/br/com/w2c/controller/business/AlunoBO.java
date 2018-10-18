package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.AlunoDAO;
import br.com.w2c.model.domain.Aluno;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.model.enums.TipoHistoricoAlteracao;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
@Component
public class AlunoBO extends BaseBO<Aluno> {

	@Autowired
	private AlunoDAO alunoDAO;
	
	@Autowired
	private HistoricoAlteracaoBO historicoAlteracaoBO;

	public void validarCamposObrigatorios(Aluno aluno) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(aluno)) {
			lancarMensagemSuportePadrao();
		} else {
			if (isNuloOuVazio(aluno.getNome())) {
				addMensagemCampo("nome");
			}
		}
	}
	
	public void salvar(Aluno aluno) throws NegocioException, AplicacaoException {
		validarCamposObrigatorios(aluno);
		validarMensagens();
		validarExisteCadastrado(aluno);

		TipoHistoricoAlteracao tipo = historicoAlteracaoBO.getTipo(aluno.getId());
		
		trimString(aluno.getNome());
		aluno.setAtivo(Boolean.TRUE);
		aluno.setDataUltimaAtualizacao(null);
		alunoDAO.salvar(aluno);
		
		historicoAlteracaoBO.salvar(aluno, tipo);
	}
	
	public void desativar(Aluno aluno) throws NegocioException, AplicacaoException {
		validarCamposParaExclusao(aluno);
		aluno = alunoDAO.obter(Aluno.class, aluno.getId());
		validarCamposParaExclusao(aluno);
		validarMensagens();
	
		historicoAlteracaoBO.salvar(aluno, TipoHistoricoAlteracao.DESATIVACAO);
		
		aluno.setAtivo(Boolean.FALSE);
		aluno.setDataUltimaAtualizacao(null);
		alunoDAO.salvar(aluno);
	}
	
	public void validarCamposParaExclusao(Aluno aluno) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(aluno) || isNuloOuVazio(aluno.getId())) {
			lancarMensagemSuportePadrao();
		}
	}
	
	public List<Aluno> obterListaPorSugestaoNome(String nome) throws AplicacaoException {
		return alunoDAO.obterPorNome(nome);
	}
	
	public Aluno obterPorNome(String nome) throws AplicacaoException {
		return alunoDAO.obterPorNomeExato(nome);
	}
	
	public List<Aluno> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return alunoDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, Aluno.class);
	}
	
	public void salvarOnline(Aluno aluno) throws AplicacaoException, NegocioException {
		Aluno retorno = obterPorCriterioOnline(aluno);
		
		if (retorno == null) {
			retorno = new Aluno();
			retorno.setIdentificador(aluno.getIdentificador());
			retorno.setNome(aluno.getNome());
		}
		
		retorno.setAtivo(aluno.getAtivo());
		retorno.setDataNascimento(aluno.getDataNascimento());
		retorno.setDataUltimaAtualizacao(aluno.getDataUltimaAtualizacao());
		//TODO Pode ser muito grande para salvar foto. Versao 9 do postgre OK, mas a 8 não funciona online.
//		retorno.setFoto(aluno.getFoto());
		
		alunoDAO.salvar(retorno);
	}

	public Aluno obterPorCriterioOnline(Aluno aluno) throws AplicacaoException,
			NegocioException {
		List<Aluno> lista = alunoDAO.obterPorCriterioOnline(aluno);
		String objeto = getLabel("aluno") + " " + aluno.getNome();
		Aluno retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}
	
	public Aluno obterPorId(Long id) {
		return alunoDAO.obter(Aluno.class, id);
	}
	public List<Aluno> obterListaPorSugestaoNome(String nome, Usuario usuario) throws AplicacaoException {
		if (usuario == null) {
			return obterListaPorSugestaoNome(nome);
		}
		else {
			return alunoDAO.obterPorNomeEUsuario(nome, usuario);
		}
	}

	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public AlunoDAO getAlunoDAO() {
		return alunoDAO;
	}


}

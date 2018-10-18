package br.com.w2c.model.dao;

import java.util.Date;
import java.util.List;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.model.wrapper.MatriculaWrapper;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
public interface MatriculaDAO extends BaseDAO<Matricula> {

	Matricula obterPorParametrosUnicos(Matricula matricula) throws AplicacaoException;

	@Deprecated
	/**
	 * Pode retornar mais de um
	 */
	Matricula obterPorMatricula(String matricula) throws AplicacaoException;

	List<Matricula> obterListaPorParametros(Matricula matricula) throws AplicacaoException;
	
	Matricula obterRepetido(Matricula matricula) throws AplicacaoException;

	List<Matricula> obterMatriculasPorPeriodo(Matricula matricula, Date periodoInicial, Date periodoFinal) throws AplicacaoException;

	List<Matricula> obterMatriculasPorPeriodoInicial(Matricula matricula, Date periodoInicial) throws AplicacaoException;

	List<Matricula> obterMatriculasPorPeriodoFinal(Matricula matricula, Date periodoFinal) throws AplicacaoException;

	void salvar(Matricula matricula);

	List<Matricula> obterTodos() throws AplicacaoException;

	Long obterQtdAtivos() throws AplicacaoException;

	Matricula obterRepetidoComMesmoAno(Matricula matricula) throws AplicacaoException;

	Matricula obterPorMatriculaEAnoLetivo(Matricula matricula) throws AplicacaoException;

	List<Matricula> obterListaPorParametrosEAnoLetivo(Matricula matricula) throws AplicacaoException;

	List<Matricula> obterListaPorParametrosOrdemData(Matricula matricula) throws AplicacaoException;

	List<Matricula> obterListaPorParametrosEAnoLetivoOrdemData(Matricula matricula) throws AplicacaoException;

	List<Matricula> obterListaPorSugestaoNomeAluno(String nomeAluno) throws AplicacaoException;

	List<Matricula> obterPorTurma(Turma turma) throws AplicacaoException;

	List<Matricula> obterPorTurmaSerieAnoLetivo(Turma turma) throws AplicacaoException;

	List<Matricula> obterPorUsuario(Usuario usuario) throws AplicacaoException;

	List<MatriculaWrapper> obterRelatorioOnlineFaltas(Date periodoInicial,
			Date periodoFinal, Matricula matricula) throws AplicacaoException;

	List<Matricula> obterListaPorSugestaoNomeAlunoEUsuario(String nomeAluno, Usuario usuario) throws AplicacaoException;

	List<MatriculaWrapper> obterRelatorioOnlineFaltasEUsuario(
			Date periodoInicial, Date periodoFinal, Matricula matricula,
			Usuario usuario) throws AplicacaoException;

	List<Matricula> obterListaPorSugestaoMatricula(String matricula) throws AplicacaoException;

	List<Matricula> obterPorTurmaAnoLetivo(Turma turma) throws AplicacaoException;

}

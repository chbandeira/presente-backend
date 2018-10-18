package br.com.w2c.model.dao;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;
import static br.com.chbandeira.utilitario.UtilDate.getDataFormatada;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.model.wrapper.MatriculaWrapper;
import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 08/09/2013
 */
@Repository
public class MatriculaDAOImpl extends BaseDAOImpl<Matricula> implements MatriculaDAO {

	/**
	 * Parametros únicos: matricula
	 * @throws AplicacaoException 
	 */
	@Override
	public Matricula obterPorParametrosUnicos(Matricula matricula) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", matricula.getMatricula());
		return (Matricula) obterResultadoUnico("Matricula.obterPorParametrosUnicos", parametros);
	}

	@Override
	@Deprecated
	/**
	 * Pode retornar mais de um
	 */
	public Matricula obterPorMatricula(String matricula) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", matricula);
		return (Matricula) obterResultadoUnico("Matricula.obterPorMatricula", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obterListaPorParametros(Matricula matricula) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", isNuloOuVazio(matricula.getMatricula()) ? "%" : matricula.getMatricula());
		parametros.put("nome", like(matricula.getAluno().getNome()));
		
		return obterResultado("Matricula.obterListaPorParametros", parametros);
	}

	@Override
	public Matricula obterRepetido(Matricula matricula) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", matricula.getMatricula());
		parametros.put("id", isNuloOuVazio(matricula.getId()) ? 0L : matricula.getId());
		return (Matricula) obterResultadoUnico("Matricula.obterRepetido", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obterMatriculasPorPeriodo(Matricula matricula, Date periodoInicial, Date periodoFinal) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", like(matricula.getMatricula()));
		parametros.put("nome", like(matricula.getAluno().getNome()));
		parametros.put("periodoInicial", getDataFormatada(periodoInicial, Constantes.DD_MM_YYYY_DB));
		parametros.put("periodoFinal", getDataFormatada(periodoFinal, Constantes.DD_MM_YYYY_DB));
		return obterResultado("Matricula.obterMatriculasPorPeriodo", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obterMatriculasPorPeriodoInicial(Matricula matricula, Date periodoInicial) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", like(matricula.getMatricula()));
		parametros.put("nome", like(matricula.getAluno().getNome()));
		parametros.put("periodoInicial", getDataFormatada(periodoInicial, Constantes.DD_MM_YYYY_DB));
		return obterResultado("Matricula.obterMatriculasPorPeriodoInicial", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obterMatriculasPorPeriodoFinal(Matricula matricula, Date periodoFinal) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", like(matricula.getMatricula()));
		parametros.put("nome", like(matricula.getAluno().getNome()));
		parametros.put("periodoFinal", getDataFormatada(periodoFinal, Constantes.DD_MM_YYYY_DB));
		return obterResultado("Matricula.obterMatriculasPorPeriodoFinal", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obterTodos() throws AplicacaoException {
		return obterResultado("Matricula.obterTodos");
	}

	@Override
	public Long obterQtdAtivos() throws AplicacaoException {
		return (Long) obterResultadoUnico("Matricula.obterQtdAtivos");
	}

	@Override
	public Matricula obterRepetidoComMesmoAno(Matricula matricula) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", matricula.getMatricula());
		parametros.put("id", isNuloOuVazio(matricula.getId()) ? 0L : matricula.getId());
		parametros.put("anoLetivo", matricula.getAnoLetivo());
		return (Matricula) obterResultadoUnico("Matricula.obterRepetidoComMesmoAno", parametros);
	}

	@Override
	public Matricula obterPorMatriculaEAnoLetivo(Matricula matricula) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", matricula.getMatricula());
		parametros.put("anoLetivo", matricula.getAnoLetivo());
		return (Matricula) obterResultadoUnico("Matricula.obterPorMatriculaEAnoLetivo", parametros);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obterListaPorParametrosEAnoLetivo(Matricula matricula) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", isNuloOuVazio(matricula.getMatricula()) ? "%" : matricula.getMatricula());
		parametros.put("nome", like(matricula.getAluno().getNome()));
		parametros.put("anoLetivo", matricula.getAnoLetivo());
		
		return obterResultado("Matricula.obterListaPorParametrosEAnoLetivo", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obterListaPorParametrosOrdemData(Matricula matricula) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", isNuloOuVazio(matricula.getMatricula()) ? "%" : matricula.getMatricula());
		parametros.put("nome", like(matricula.getAluno().getNome()));
		
		return obterResultado("Matricula.obterListaPorParametrosOrdemData", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obterListaPorParametrosEAnoLetivoOrdemData(Matricula matricula) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", isNuloOuVazio(matricula.getMatricula()) ? "%" : matricula.getMatricula());
		parametros.put("nome", like(matricula.getAluno().getNome()));
		parametros.put("anoLetivo", matricula.getAnoLetivo());
		
		return obterResultado("Matricula.obterListaPorParametrosOrdem", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obterListaPorSugestaoNomeAluno(String nomeAluno) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("nomeAluno", like(nomeAluno));
		parametros.put("anoLetivo", UtilDate.getAnoAtual());
		return obterResultado("Matricula.obterListaPorSugestaoNomeAluno", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obterPorTurma(Turma turma) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("id", turma.getId());
		return obterResultado("Matricula.obterPorTurma", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obterPorTurmaSerieAnoLetivo(Turma turma) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("idTurma", turma.getId());
		parametros.put("idSerie", turma.getSerie().getId());
		parametros.put("anoLetivo", turma.getAnoLetivo());
		return obterResultado("Matricula.obterPorTurmaSerieAnoLetivo", parametros);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obterPorTurmaAnoLetivo(Turma turma) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("idTurma", turma.getId());
		parametros.put("anoLetivo", turma.getAnoLetivo());
		return obterResultado("Matricula.obterPorTurmaAnoLetivo", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obterPorUsuario(Usuario usuario) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("idUsuario", usuario.getLogin());
		parametros.put("identificador", usuario.getIdentificador());
		return obterResultado("Matricula.obterPorUsuario", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MatriculaWrapper> obterRelatorioOnlineFaltas(Date periodoInicial,
			Date periodoFinal, Matricula matricula) throws AplicacaoException {
		
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", matricula.getMatricula());
		parametros.put("dataInicio", UtilDate.getDataFormatada(periodoInicial, UtilDate.DD_MM_YYYY_DB));
		parametros.put("dataFim", UtilDate.getDataFormatada(periodoFinal, UtilDate.DD_MM_YYYY_DB));
		parametros.put("ano", UtilDate.getAno(periodoInicial));
		String sql = getQuerySQL("Presenca.relatorioOnlineFaltas");
		
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		addParametrosSql(parametros, sqlQuery);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(MatriculaWrapper.class));
		
		return sqlQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obterPorCriterioOnline(Matricula matricula)
			throws AplicacaoException {
		Criteria criteria = createCriteria(Matricula.class);
		criteria.add(Restrictions.eq("identificador", matricula.getIdentificador()));
		criteria.add(Restrictions.eq("matricula", matricula.getMatricula()));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obterListaPorSugestaoNomeAlunoEUsuario(String nomeAluno, Usuario usuario) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("nomeAluno", like(nomeAluno));
		parametros.put("identificador", usuario.getIdentificador());
		return obterResultado("Matricula.obterListaPorSugestaoNomeAlunoEUsuario", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MatriculaWrapper> obterRelatorioOnlineFaltasEUsuario(
			Date periodoInicial, Date periodoFinal, Matricula matricula,
			Usuario usuario) throws AplicacaoException {
		
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", matricula.getMatricula());
		parametros.put("dataInicio", UtilDate.getDataFormatada(periodoInicial, UtilDate.DD_MM_YYYY_DB));
		parametros.put("dataFim", UtilDate.getDataFormatada(periodoFinal, UtilDate.DD_MM_YYYY_DB));
		parametros.put("ano", UtilDate.getAno(periodoInicial));
		parametros.put("identificador", usuario.getIdentificador());
		String sql = getQuerySQL("Presenca.relatorioOnlineFaltasComUsuario");
		
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		addParametrosSql(parametros, sqlQuery);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(MatriculaWrapper.class));
		
		return sqlQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obterListaPorSugestaoMatricula(String matricula) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", like(matricula));
		parametros.put("anoLetivo", UtilDate.getAnoAtual());
		return obterResultado("Matricula.obterListaPorSugestaoMatricula", parametros);
	}
}

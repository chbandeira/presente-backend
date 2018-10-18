package br.com.w2c.model.dao;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;
import static br.com.chbandeira.utilitario.UtilDate.getDataFormatada;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Registro;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.model.enums.TipoRegistro;
import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 17/09/2013
 */
@Repository
public class RegistroDAOImpl extends BaseDAOImpl<Registro> implements RegistroDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Registro> obterListaPorParametros(Registro registro) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", like(registro.getMatricula().getMatricula()));
		return obterResultado("Registro.obterListaPorParametros", parametros);
	}

	@Override
	public Registro obterListaPorParametrosUnicos(Registro registro) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", registro.getMatricula().getMatricula());
		return (Registro) obterResultadoUnico("Registro.obterListaPorParametrosUnicos", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Registro> obterRepetido(Registro registro) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", registro.getMatricula().getMatricula());
		parametros.put("dataFormatada", getDataFormatada(registro.getData(), Constantes.DD_MM_YYYY_DB));
		parametros.put("tipo", registro.getTipoRegistro());
		return obterResultado("Registro.obterRepetido", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Registro> obterPorMatriculaEPeriodo(Matricula matricula, Date periodoInicial, Date periodoFinal) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", like(matricula.getMatricula()));
		parametros.put("nome", like(matricula.getAluno().getNome()));
		parametros.put("periodoInicial", getDataFormatada(periodoInicial, Constantes.DD_MM_YYYY_DB));
		parametros.put("periodoFinal", getDataFormatada(periodoFinal, Constantes.DD_MM_YYYY_DB));
	
		return obterResultado("Registro.obterPorMatriculaEPeriodo", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Registro> obterPorPeriodoInicial(Registro registro, Date periodoInicial) throws AplicacaoException {
		Matricula matricula = registro.getMatricula();
		
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", like(matricula.getMatricula()));
		parametros.put("nome", like(matricula.getAluno().getNome()));
		parametros.put("periodoInicial", getDataFormatada(periodoInicial, Constantes.DD_MM_YYYY_DB));
		
		return obterResultado("Registro.obterPorPeriodoInicial", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Registro> obterPorPeriodoFinal(Registro registro, Date periodoFinal) throws AplicacaoException {
		Matricula matricula = registro.getMatricula();
		
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", like(matricula.getMatricula()));
		parametros.put("nome", like(matricula.getAluno().getNome()));
		parametros.put("periodoFinal", getDataFormatada(periodoFinal, Constantes.DD_MM_YYYY_DB));
		
		return obterResultado("Registro.obterPorPeriodoFinal", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Registro> obterSemPeriodo(Registro registro) throws AplicacaoException {
		Matricula matricula = registro.getMatricula();
		
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", like(matricula.getMatricula()));
		parametros.put("nome", like(matricula.getAluno().getNome()));
		
		return obterResultado("Registro.obterSemPeriodo", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Registro> obterPorPeriodo(Date periodoInicial, Date periodoFinal) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("periodoInicial", getDataFormatada(periodoInicial, Constantes.DD_MM_YYYY_DB));
		parametros.put("periodoFinal", getDataFormatada(periodoFinal, Constantes.DD_MM_YYYY_DB));
	
		return obterResultado("Registro.obterPorPeriodo", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> obterDataStringPorMatriculaEPeriodo(Matricula matricula, Date periodoInicial, Date periodoFinal) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("matricula", like(matricula.getMatricula()));
		parametros.put("nome", like(matricula.getAluno().getNome()));
		parametros.put("periodoInicial", getDataFormatada(periodoInicial, Constantes.DD_MM_YYYY_DB));
		parametros.put("periodoFinal", getDataFormatada(periodoFinal, Constantes.DD_MM_YYYY_DB));
	
		return obterResultado("Registro.obterDataStringPorMatriculaEPeriodo", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Registro> obterSmsNaoEnviado() throws AplicacaoException {
		return obterResultado("Registro.obterSmsNaoEnviado", 100);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Registro> obterEmailNaoEnviado() throws AplicacaoException {
		return obterResultado("Registro.obterEmailNaoEnviado", 100);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Registro> obterNaoEnviado() throws AplicacaoException {
		return obterResultado("Registro.obterNaoEnviado", 100);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Registro> obterRegistrosPorUsuario(Matricula matricula,
			Date periodoInicial, Date periodoFinal, Usuario usuario) throws AplicacaoException {
		
		Criteria criteria = createCriteria(Registro.class);
		Criteria matriculaCriteria = criteria.createCriteria("matricula");
		Criteria alunoCriteria = criteria.createCriteria("matricula.aluno");
		Criteria usuarioCriteria = matriculaCriteria.createCriteria("responsavel.usuario");
		
		matriculaCriteria.add(Restrictions.eq("matricula", matricula.getMatricula()));
		usuarioCriteria.add(Restrictions.eq("login", usuario.getLogin()));
		usuarioCriteria.add(Restrictions.eq("identificador", usuario.getIdentificador()));
		
		if (!isNuloOuVazio(periodoInicial) && !isNuloOuVazio(periodoFinal)) {
			criteria.add(Restrictions.between("data", periodoInicial, periodoFinal));
		} 
		else if (!isNuloOuVazio(periodoInicial)) {
			criteria.add(Restrictions.sqlRestriction("to_char(dat_data, 'yyyy-MM-dd') = '" + UtilDate.getDataFormatada(periodoInicial, "yyyy-MM-dd") + "'"));
		} 
		else if (!isNuloOuVazio(periodoFinal)) {
			criteria.add(Restrictions.sqlRestriction("to_char(dat_data, 'yyyy-MM-dd') = '" + UtilDate.getDataFormatada(periodoFinal, "yyyy-MM-dd") + "'"));
		}
		matriculaCriteria.add(Restrictions.eq("ativo", Boolean.TRUE));
		alunoCriteria.add(Restrictions.eq("ativo", Boolean.TRUE));
		criteria.addOrder(Order.desc("data"));
		
		return criteria.list(); 
	}

	@Override
	public int obterQtdRegistrosEntradaPorUsuario(Matricula matricula,
			Date periodoInicial, Date periodoFinal, Usuario usuario)
			throws AplicacaoException {
		
		Criteria criteria = createCriteria(Registro.class);
		Criteria matriculaCriteria = criteria.createCriteria("matricula");
		Criteria alunoCriteria = criteria.createCriteria("matricula.aluno");
		Criteria usuarioCriteria = matriculaCriteria.createCriteria("responsavel.usuario");
		
		matriculaCriteria.add(Restrictions.eq("matricula", matricula.getMatricula()));
		usuarioCriteria.add(Restrictions.eq("login", usuario.getLogin()));
		usuarioCriteria.add(Restrictions.eq("identificador", usuario.getIdentificador()));
		
		if (!isNuloOuVazio(periodoInicial) && !isNuloOuVazio(periodoFinal)) {
			criteria.add(Restrictions.between("data", periodoInicial, periodoFinal));
		} 
		else if (!isNuloOuVazio(periodoInicial)) {
			criteria.add(Restrictions.sqlRestriction("to_char(dat_data, 'yyyy-MM-dd') = '" + UtilDate.getDataFormatada(periodoInicial, "yyyy-MM-dd") + "'"));
		} 
		else if (!isNuloOuVazio(periodoFinal)) {
			criteria.add(Restrictions.sqlRestriction("to_char(dat_data, 'yyyy-MM-dd') = '" + UtilDate.getDataFormatada(periodoFinal, "yyyy-MM-dd") + "'"));
		}
		matriculaCriteria.add(Restrictions.eq("ativo", Boolean.TRUE));
		alunoCriteria.add(Restrictions.eq("ativo", Boolean.TRUE));
		criteria.add(Restrictions.eq("tipoRegistro", TipoRegistro.ENTRADA));
		criteria.addOrder(Order.desc("data"));
		
		return criteria.list().size(); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Registro> obterPorCriterioOnline(Registro entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(Registro.class);
		Criteria matriculaCriteria = criteria.createCriteria("matricula");
		
		matriculaCriteria.add(Restrictions.eq("matricula", entidade.getMatricula().getMatricula()));
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		criteria.add(Restrictions.eq("data", entidade.getData()));
		criteria.add(Restrictions.eq("tipoRegistro", entidade.getTipoRegistro()));
		
		return criteria.list();
	}
	
}

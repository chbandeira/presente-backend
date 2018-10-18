package br.com.w2c.model.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.chbandeira.utilitario.Util;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.BaseEntity;
import br.com.w2c.util.ResourceUtil;

/**
 * @author charlles
 * @since 14/09/2013
 */
@Transactional
@Repository
public abstract class BaseDAOImpl<T> implements BaseDAO<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void salvar(T entidade) {
		if (entidade instanceof BaseEntity && ((BaseEntity)entidade).getDataUltimaAtualizacao() == null) {
			((BaseEntity)entidade).setDataUltimaAtualizacao(new Date());
		}
		getSession().saveOrUpdate(entidade);
	}

	@Override
	public void excluir(T entidade) {
		getSession().delete(entidade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T obter(Class<T> clazz, Serializable id) {
		return (T) getSession().get(clazz, id);
	}
/*	
	protected String getQueryORM(String chave) throws AplicacaoException {
		return ResourceUtil.getQueryORM(chave);
	}*/
	
	protected Map<String, Object> criarParametros() {
		return new HashMap<String, Object>();
	}

	@SuppressWarnings("rawtypes")
	protected List obterResultado(String queryName) throws AplicacaoException {
		return getSession().getNamedQuery(queryName).list();
	}
	
	@SuppressWarnings("rawtypes")
	protected List obterResultadoQueryNativa(String sql, Map<String, Object> parametros) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		addParametrosSql(parametros, sqlQuery);
		return sqlQuery.list();
	}

	@SuppressWarnings("rawtypes")
	protected void addParametrosSql(Map<String, Object> parametros, SQLQuery sqlQuery) {
		for (Entry<String, Object> entry : parametros.entrySet()) {
			if (entry.getValue() instanceof Collection) {
				sqlQuery.setParameterList(entry.getKey(), (Collection) entry.getValue());
			} else {
				sqlQuery.setParameter(entry.getKey(), entry.getValue());
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void addParametros(Map<String, Object> parametros, Query query) {
		for (Entry<String, Object> entry : parametros.entrySet()) {
			if (entry.getValue() instanceof Collection) {
				query.setParameterList(entry.getKey(), (Collection) entry.getValue());
			} else {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	protected List obterResultado(String queryName, Map<String, Object> parametros) throws AplicacaoException {
		Query query = getSession().getNamedQuery(queryName);
		addParametros(parametros, query);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	protected List<T> obterResultado(String queryName, int limit) throws AplicacaoException {
		Query query = getSession().getNamedQuery(queryName);
		query.setMaxResults(limit);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> obterResultado(String queryName, Map<String, Object> parametros, int limit) throws AplicacaoException {
		Query query = getSession().getNamedQuery(queryName);
		addParametros(parametros, query);
		query.setMaxResults(limit);
		return query.list();
	}
	
	protected Object obterResultadoUnico(String queryName) throws AplicacaoException {
		Query query = getSession().getNamedQuery(queryName);
		query.setMaxResults(1);
		return query.uniqueResult();
	}
	
	protected Object obterResultadoUnico(String queryName, Map<String, Object> parametros) throws AplicacaoException {
		Query query = getSession().getNamedQuery(queryName);
		addParametros(parametros, query);
		query.setMaxResults(1);
		return query.uniqueResult();
	}
	
	/**
	 * @param string
	 * @return Retorna "%"+string.toUpperCase()+"%" 
	 */
	protected String like(String string) {
		if (Util.isNuloOuVazio(string) || string.length() == 0) {
			return "%";
		} else {
			return "%"+string+"%";
		}
	}

	@Override
	public void flush() {
		getSession().flush();
	}
	
	protected Criteria createCriteria(Class<T> clazz) {
		return getSession().createCriteria(clazz);
	}
	
	protected String getQuerySQL(String key) throws AplicacaoException {
		return ResourceUtil.getQuerySQL(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> obterUltimosAtualizados(Date dataHora, Class<T> clazz) throws AplicacaoException {
		Criteria criteria = getSession().createCriteria(clazz);
		criteria.add(Restrictions.eq("identificador", obterIdentificador()));
		criteria.add(Restrictions.gt("dataUltimaAtualizacao", dataHora));
		return criteria.list();
	}

	private Long obterIdentificador() throws AplicacaoException {
		return (Long) obterResultadoUnico("ConfiguracaoEscola.obterIdentificador");
	}
}

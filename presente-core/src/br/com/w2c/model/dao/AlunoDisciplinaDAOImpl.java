package br.com.w2c.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.AlunoDisciplina;

/**
 * 
 * @author charlles
 * @since 31/01/2016
 */
@Repository
public class AlunoDisciplinaDAOImpl extends BaseDAOImpl<AlunoDisciplina> implements AlunoDisciplinaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<AlunoDisciplina> obterPorCriterioOnline(AlunoDisciplina entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(AlunoDisciplina.class);
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		criteria.add(Restrictions.eq("disciplina", entidade.getAlunoDisciplinaPK().getDisciplina().getId()));
		criteria.add(Restrictions.eq("matricula", entidade.getAlunoDisciplinaPK().getMatricula().getId()));
		return criteria.list();
	}
	
}

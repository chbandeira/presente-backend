package br.com.w2c.model.dao;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Ocorrencia;
import br.com.w2c.model.domain.Usuario;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
@Repository
public class OcorrenciaDAOImpl extends BaseDAOImpl<Ocorrencia> implements OcorrenciaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Ocorrencia> obterListaPorParametros(Ocorrencia ocorrencia) {
		
		Criteria criteria = createCriteria(Ocorrencia.class);
		
		if (!isNuloOuVazio(ocorrencia.getMatricula())) {
			
			if (!isNuloOuVazio(ocorrencia.getMatricula().getAluno()) 
					&& !isNuloOuVazio(ocorrencia.getMatricula().getAluno().getNome())) {
				
				Criteria aluno = criteria.createCriteria("matricula.aluno");
				aluno.add(Restrictions.like("nome", ocorrencia.getMatricula().getAluno().getNome(), MatchMode.ANYWHERE).ignoreCase());
			}
			
			if (!isNuloOuVazio(ocorrencia.getMatricula().getTurma())
					&& !isNuloOuVazio(ocorrencia.getMatricula().getTurma().getDescricao())) {
				Criteria turma = criteria.createCriteria("matricula.turma");
				turma.add(Restrictions.like("descricao", ocorrencia.getMatricula().getTurma().getDescricao(), MatchMode.ANYWHERE).ignoreCase());
			}

		}

		if (!isNuloOuVazio(ocorrencia.getTipo()) && !isNuloOuVazio(ocorrencia.getTipo().getDescricao())) {
			Criteria tipo = criteria.createCriteria("tipo");
			tipo.add(Restrictions.like("descricao", ocorrencia.getTipo().getDescricao(), MatchMode.ANYWHERE).ignoreCase());
		}
		
		criteria.add(Restrictions.eq("ativo", Boolean.TRUE));
		criteria.addOrder(Order.desc("data"));
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ocorrencia> obterPorCriterioOnline(Ocorrencia entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(Ocorrencia.class);
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		criteria.add(Restrictions.eq("descricao", entidade.getDescricao()));
		criteria.add(Restrictions.eq("data", entidade.getData()));
		criteria.add(Restrictions.eq("matricula.id", entidade.getMatricula().getId()));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ocorrencia> obterPorUsuario(Usuario usuario) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("login", usuario.getLogin());
		parametros.put("identificador", usuario.getIdentificador());
		return obterResultado("Ocorrencia.obterPorUsuario", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ocorrencia> obterListaPorParametrosEUsuario(Ocorrencia ocorrencia, Usuario usuario) {
		Criteria criteria = createCriteria(Ocorrencia.class);
		criteria.add(Restrictions.eq("identificador", usuario.getIdentificador()));
		
		Criteria matriculaCriteria = criteria.createCriteria("matricula");
		Criteria responsavelCriteria = matriculaCriteria.createCriteria("responsavel");
		
		Criteria usuarioCriteria = responsavelCriteria.createCriteria("usuario");
		usuarioCriteria.add(Restrictions.eq("login", usuario.getLogin()));
		
		if (!isNuloOuVazio(ocorrencia.getMatricula()) 
				&& !isNuloOuVazio(ocorrencia.getMatricula().getAluno()) 
				&& !isNuloOuVazio(ocorrencia.getMatricula().getAluno().getNome())) {
			
			Criteria aluno = matriculaCriteria.createCriteria("aluno");
			aluno.add(Restrictions.like("nome", ocorrencia.getMatricula().getAluno().getNome(), MatchMode.ANYWHERE).ignoreCase());
		}

		if (!isNuloOuVazio(ocorrencia.getTipo()) && !isNuloOuVazio(ocorrencia.getTipo().getDescricao())) {
			Criteria tipo = criteria.createCriteria("tipo");
			tipo.add(Restrictions.like("descricao", ocorrencia.getTipo().getDescricao(), MatchMode.ANYWHERE).ignoreCase());
		}
		
		criteria.add(Restrictions.eq("ativo", Boolean.TRUE));
		criteria.addOrder(Order.desc("data"));
		
		return criteria.list();
	}

}

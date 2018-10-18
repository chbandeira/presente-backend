package br.com.w2c.controller.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.RelatorioDAO;
import br.com.w2c.model.domain.Relatorio;

/**
 * 
 * @author charlles
 * @since 04/12/2013
 */
@Component
public class RelatorioBO extends BaseBO<Relatorio> {

	@Autowired
	private RelatorioDAO relatorioDAO;
	
	public String obterNome(Long idRelatorio) throws AplicacaoException {
		return relatorioDAO.obterNome(idRelatorio);
	}

	public List<Relatorio> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return relatorioDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, Relatorio.class);
	}

	public void salvarOnline(Relatorio relatorio) throws AplicacaoException, NegocioException {
		Relatorio retorno = obterPorCriterioOnline(relatorio);
		
		if (retorno == null) {
			retorno = new Relatorio();
			retorno.setIdentificador(relatorio.getIdentificador());
			retorno.setNome(relatorio.getNome());
		}
		
		retorno.setDataUltimaAtualizacao(relatorio.getDataUltimaAtualizacao());

		relatorioDAO.salvar(retorno);
	}

	public Relatorio obterPorCriterioOnline(Relatorio relatorio)
			throws AplicacaoException, NegocioException {
		List<Relatorio> lista = relatorioDAO.obterPorCriterioOnline(relatorio);
		String objeto = getLabel("relatorios") + " " + relatorio.getNome();
		Relatorio retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}

	public RelatorioDAO getRelatorioDAO() {
		return relatorioDAO;
	}
	
}

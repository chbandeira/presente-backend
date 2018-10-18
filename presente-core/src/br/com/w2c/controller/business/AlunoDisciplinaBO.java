package br.com.w2c.controller.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.AlunoDisciplinaDAO;
import br.com.w2c.model.domain.AlunoDisciplina;
import br.com.w2c.model.domain.Disciplina;
import br.com.w2c.model.domain.Matricula;

/**
 * 
 * @author Charlles
 *
 */
@Component
public class AlunoDisciplinaBO extends BaseBO<AlunoDisciplina> {

	@Autowired
	private AlunoDisciplinaDAO alunoDisciplinaDAO;
	
	@Autowired
	private DisciplinaBO disciplinaBO;
	@Autowired
	private MatriculaBO matriculaBO;
	
	public List<AlunoDisciplina> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return alunoDisciplinaDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, AlunoDisciplina.class);
	}

	public void salvarOnline(AlunoDisciplina alunoDisciplina) throws AplicacaoException, NegocioException {
		AlunoDisciplina retorno = obterPorCriterioOnline(alunoDisciplina);
		
		if (retorno == null) {
			retorno = new AlunoDisciplina();
			retorno.setIdentificador(alunoDisciplina.getIdentificador());
			retorno.setAlunoDisciplinaPK(alunoDisciplina.getAlunoDisciplinaPK());

			retorno.getAlunoDisciplinaPK().setDisciplina(disciplinaBO.obterPorCriterioOnline(alunoDisciplina.getAlunoDisciplinaPK().getDisciplina()));
			retorno.getAlunoDisciplinaPK().setMatricula(matriculaBO.obterPorCriterioOnline(alunoDisciplina.getAlunoDisciplinaPK().getMatricula()));
		}
		
		retorno.setNotaB1(alunoDisciplina.getNotaB1());
		retorno.setNotaB2(alunoDisciplina.getNotaB2());
		retorno.setNotaB3(alunoDisciplina.getNotaB3());
		retorno.setNotaB4(alunoDisciplina.getNotaB4());
		retorno.setNotaFinal(alunoDisciplina.getNotaFinal());
		retorno.setNumeroFaltaB1(alunoDisciplina.getNumeroFaltaB1());
		retorno.setNumeroFaltaB2(alunoDisciplina.getNumeroFaltaB2());
		retorno.setNumeroFaltaB3(alunoDisciplina.getNumeroFaltaB3());
		retorno.setNumeroFaltaB4(alunoDisciplina.getNumeroFaltaB4());
		retorno.setNumeroFaltaFinal(alunoDisciplina.getNumeroFaltaFinal());
		retorno.setDataUltimaAtualizacao(alunoDisciplina.getDataUltimaAtualizacao());
		
		alunoDisciplinaDAO.salvar(retorno);
	}

	public AlunoDisciplina obterPorCriterioOnline(
			AlunoDisciplina alunoDisciplina) throws AplicacaoException,
			NegocioException {
		Disciplina disciplina = alunoDisciplina.getAlunoDisciplinaPK().getDisciplina();
		Matricula matricula = alunoDisciplina.getAlunoDisciplinaPK().getMatricula();
		
		List<AlunoDisciplina> lista = alunoDisciplinaDAO.obterPorCriterioOnline(alunoDisciplina);
		String objeto = getLabel("disciplina") + " " + disciplina.getNome() + ", " + getLabel("matricula") + " " + matricula.getMatricula();
		AlunoDisciplina retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}

	public AlunoDisciplinaDAO getAlunoDisciplinaDAO() {
		return alunoDisciplinaDAO;
	}

}

package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.OcorrenciaDAO;
import br.com.w2c.model.dao.TipoOcorrenciaDAO;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Ocorrencia;
import br.com.w2c.model.domain.TipoOcorrencia;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.model.wrapper.EmailWrapper;
import br.com.w2c.util.email.EnviarEmail;
import br.com.w2c.util.email.MensagemEmail;

/**
 * 
 * @author charlles
 * @since 19/05/2015
 */
@Component
public class OcorrenciaBO extends BaseBO<Ocorrencia> {

	@Autowired
	private OcorrenciaDAO ocorrenciaDAO;
	@Autowired
	private TipoOcorrenciaDAO tipoOcorrenciaDAO;
	
	@Autowired
	private MatriculaBO matriculaBO;
	@Autowired
	private UsuarioBO usuarioBO;
	@Autowired
	private TipoOcorrenciaBO tipoOcorrenciaBO;
	@Autowired
	private ConfiguracaoEscolaBO configuracaoEscolaBO;
	
	@Override
	public void validarCamposParaExclusao(Ocorrencia ocorrencia) throws NegocioException, AplicacaoException {
		super.validarCamposParaExclusao(ocorrencia);
		if (isNuloOuVazio(ocorrencia.getId())) {
			lancarMensagemSuportePadrao();
		}
	}
	
	public void validarCamposObrigatorios(Ocorrencia ocorrencia) throws NegocioException, AplicacaoException {
		super.validarCamposObrigatorios(ocorrencia);
		if (isNuloOuVazio(ocorrencia.getData())) {
			addMensagemCampo("dataOcorrencia");
		}
		if (isNuloOuVazio(ocorrencia.getMatricula())
				|| isNuloOuVazio(ocorrencia.getMatricula().getAluno()) 
				|| isNuloOuVazio(ocorrencia.getMatricula().getAluno().getId())) {
			
			addMensagemCampo("aluno");
		}
		if (isNuloOuVazio(ocorrencia.getTipo()) || isNuloOuVazio(ocorrencia.getTipo().getId())) {
			addMensagemCampo("tipoOcorrencia");
		}
		if (isNuloOuVazio(ocorrencia.getDescricao())) {
			addMensagemCampo("descricao");
		}
		validarMensagens();
	}
	
	public void salvar(Ocorrencia ocorrencia, boolean enviarEmail) throws NegocioException, AplicacaoException {
		verificarTipoOcorrencia(ocorrencia);
		validarCamposObrigatorios(ocorrencia);
		trimString(ocorrencia.getDescricao());
		validarMensagens();
		
		if (ocorrencia.getId() == null) {
			ocorrencia.setAtivo(true);
		}
		ocorrencia.setDataUltimaAtualizacao(null);
		ocorrenciaDAO.salvar(ocorrencia);
		enviarEmail(ocorrencia);
	}

	private void verificarTipoOcorrencia(Ocorrencia ocorrencia) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(ocorrencia.getTipo()) || isNuloOuVazio(ocorrencia.getTipo().getDescricao())) {
			addMensagemCampo("tipoOcorrencia");
			validarMensagens();
			
		} else if (isNuloOuVazio(ocorrencia.getTipo().getId())) {
			TipoOcorrencia retorno = tipoOcorrenciaDAO.obterPorDescricao(ocorrencia.getTipo());
			if (isNuloOuVazio(retorno)) {
				ocorrencia.getTipo().setDataUltimaAtualizacao(null);
				tipoOcorrenciaDAO.salvar(ocorrencia.getTipo());
				tipoOcorrenciaDAO.flush();
			}
		}
	}

	public List<Ocorrencia> obterListaPorParametros(Ocorrencia ocorrencia) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(ocorrencia)) {
			lancarMensagemSuportePadrao();
		}
		return ocorrenciaDAO.obterListaPorParametros(ocorrencia);
	}

	public void desativar(Ocorrencia ocorrencia) throws NegocioException, AplicacaoException {
		validarCamposParaExclusao(ocorrencia);
		ocorrencia = ocorrenciaDAO.obter(Ocorrencia.class, ocorrencia.getId());
		validarCamposParaExclusao(ocorrencia);
		validarMensagens();
	
		ocorrencia.setAtivo(Boolean.FALSE);
		ocorrencia.setDataUltimaAtualizacao(null);
		ocorrenciaDAO.salvar(ocorrencia);
	}
	
	public List<Ocorrencia> obterListaPorParametrosPesquisa(Ocorrencia ocorrencia) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(ocorrencia)) {
			lancarMensagemSuportePadrao();
		}
		
		return ocorrenciaDAO.obterListaPorParametros(ocorrencia);
	}
	
	/**
	 * 
	 * @param ocorrencia
	 * @param lista Lista de Matricula ou Turma
	 * @param enviarEmail 
	 * @return 
	 * @throws NegocioException
	 * @throws AplicacaoException 
	 */
	@SuppressWarnings("rawtypes")
	public List<Long> salvar(Ocorrencia ocorrencia, List lista, boolean enviarEmail) throws NegocioException, AplicacaoException {
		List<Long> idsOcorrenciasSalvos = new ArrayList<>();
		
		for (Object object : lista) {
			if (object instanceof Matricula) {
				ocorrencia.setId(null);
				ocorrencia.setMatricula((Matricula)object);
				ocorrencia.setDataGeracao(UtilDate.getDataAtualTimestamp());
				salvar(ocorrencia, enviarEmail);
				idsOcorrenciasSalvos.add(ocorrencia.getId());
			} 
			else if (object instanceof Turma) {
				List<Matricula> matriculas = matriculaBO.obterPorTurmaSerieAnoLetivo((Turma)object);
				for (Matricula matricula : matriculas) {
					ocorrencia.setId(null);
					ocorrencia.setMatricula(matricula);
					ocorrencia.setDataGeracao(UtilDate.getDataAtualTimestamp());
					salvar(ocorrencia, enviarEmail);
					idsOcorrenciasSalvos.add(ocorrencia.getId());
				}
			}
		}
		return idsOcorrenciasSalvos;
	}

	private void enviarEmail(Ocorrencia ocorrencia) throws AplicacaoException {
		if (ocorrencia.getMatricula().getResponsavel() != null
				&& ocorrencia.getMatricula().getResponsavel().getEmail() != null
				&& !ocorrencia.getMatricula().getResponsavel().getEmail().equals("")) {
			
			EnviarEmail.enviarThread(new EmailWrapper(
					getLabel("ocorrencia"), 
					MensagemEmail.msgOcorrenciaEmail(ocorrencia, configuracaoEscolaBO.obterNomeEscola()), 
					ocorrencia.getMatricula().getResponsavel().getEmail()));
		}
	}
	
	public List<Ocorrencia> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return ocorrenciaDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, Ocorrencia.class);
	}

	public void salvarOnline(Ocorrencia ocorrencia) throws AplicacaoException, NegocioException {
		Ocorrencia retorno = obterPorCriterioOnline(ocorrencia);
		
		if (retorno == null) {
			retorno = new Ocorrencia();
			retorno.setIdentificador(ocorrencia.getIdentificador());
			retorno.setDescricao(ocorrencia.getDescricao());
			retorno.setData(ocorrencia.getData());
			retorno.setMatricula(matriculaBO.obterPorCriterioOnline(ocorrencia.getMatricula()));
		}
		
		retorno.setAtivo(ocorrencia.getAtivo());
		retorno.setDataGeracao(ocorrencia.getDataGeracao());
		retorno.setQtdDias(ocorrencia.getQtdDias());
		retorno.setTipo(tipoOcorrenciaBO.obterPorCriterioOnline(ocorrencia.getTipo()));
		retorno.setUsuario(usuarioBO.obterPorCriterioOnline(ocorrencia.getUsuario()));
		retorno.setDataUltimaAtualizacao(ocorrencia.getDataUltimaAtualizacao());

		ocorrenciaDAO.salvar(retorno);
	}

	public Ocorrencia obterPorCriterioOnline(Ocorrencia ocorrencia)
			throws AplicacaoException, NegocioException {
		List<Ocorrencia> lista = ocorrenciaDAO.obterPorCriterioOnline(ocorrencia);
		String objeto = getLabel("ocorrencia") + " " + ocorrencia.getDescricao();
		Ocorrencia retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}
	
	public List<Ocorrencia> obterPorUsuario(Usuario usuario) throws AplicacaoException {
		return ocorrenciaDAO.obterPorUsuario(usuario);
	}
	
	public List<Ocorrencia> obterListaPorParametrosPesquisaOnline(Ocorrencia ocorrencia, Usuario usuario) throws AplicacaoException {
		if (isNuloOuVazio(ocorrencia)) {
			return obterPorUsuario(usuario);
		}
		else {
			return ocorrenciaDAO.obterListaPorParametrosEUsuario(ocorrencia, usuario);
		}
	}
	
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public OcorrenciaDAO getOcorrenciaDAO() {
		return ocorrenciaDAO;
	}

	public void setOcorrenciaDAO(OcorrenciaDAO ocorrenciaDAO) {
		this.ocorrenciaDAO = ocorrenciaDAO;
	}

}

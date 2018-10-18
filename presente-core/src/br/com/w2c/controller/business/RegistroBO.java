package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.RegistroDAO;
import br.com.w2c.model.domain.Aluno;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Registro;
import br.com.w2c.model.domain.Responsavel;
import br.com.w2c.model.domain.Sala;
import br.com.w2c.model.domain.Serie;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.model.enums.TipoRegistro;
import br.com.w2c.model.wrapper.EmailWrapper;
import br.com.w2c.model.wrapper.RegistroWrapper;
import br.com.w2c.model.wrapper.SmsEmailWrapper;
import br.com.w2c.util.Constantes;
import br.com.w2c.util.email.EnviarEmail;
import br.com.w2c.util.sms.HttpPostComteleSMS;

/**
 * 
 * @author charlles
 * @since 17/09/2013
 */
@Component
public class RegistroBO extends BaseBO<Registro> {

	private static Logger log = LogManager.getLogger();
	
	@Autowired
	private RegistroDAO registroDAO;
	
	@Autowired
	private MatriculaBO matriculaBO;
	
	@Autowired
	private ConfiguracaoEscolaBO configuracaoEscolaBO;
	
	@Autowired
	private HttpPostComteleSMS comteleSMS;
	
	@SuppressWarnings("unused")
	private void enviarEmailRegistro(Registro registro) throws Exception {
		
		if (registro.getMatricula().getEnviarEmailRegistro() 
				|| registro.getMatricula().getEnviarSmsRegistro()) {
			
			String mensagem = getMensagem(registro);
			
			if (registro.getMatricula().getEnviarEmailRegistro()) {
				EmailWrapper emailWrapper = new EmailWrapper(getAssunto(registro), mensagem, registro.getMatricula().getResponsavel().getEmail());
				try {
					EnviarEmail.enviarEmail(emailWrapper);
					registro.setEmailRegistroEnviado(Boolean.TRUE);
					registro.setDataUltimaAtualizacao(null);
					registroDAO.salvar(registro);
					log.info("Email enviado: Registro " + registro.getId());
				} catch (Exception e) {
					log.error("Email não enviado: Registro " + registro.getId(), e);
					throw e;
				}
			}
		}
	}

	@SuppressWarnings("unused")
	private void enviarSmsRegistro(Registro registro) throws Exception {
		
		if (registro.getMatricula().getEnviarEmailRegistro() 
				|| registro.getMatricula().getEnviarSmsRegistro()) {
			
			String mensagem = getMensagem(registro);
			
			if (registro.getMatricula().getEnviarSmsRegistro()) {
				String celular = registro.getMatricula().getResponsavel().getTelefoneCelular().replace("-", "");
				celular = celular.replace("(", "");
				celular = celular.replace(")", "");
				if (celular.length()==10) {
					celular = Constantes.DDI_LOCAL+celular;
				}
				try {
					comteleSMS.enviarSMS(mensagem, celular);
					registro.setSmsRegistroEnviado(Boolean.TRUE);
					registro.setDataUltimaAtualizacao(null);
					registroDAO.salvar(registro);
					log.info("SMS enviado: Registro " + registro.getId());
				} catch (Exception e) {
					log.error("SMS não enviado: Registro " + registro.getId(), e);
					throw e;
				}
			}
		}
	}

	private String getAssunto(Registro registro) throws AplicacaoException {
		String tipoRegistroMsg034 = registro.getTipoRegistro().equals(TipoRegistro.ENTRADA) ? getLabel("entrada") : getLabel("saida");
		return getMessageParametros("MSG034", new Object[] {tipoRegistroMsg034});
	}

	private String getMensagem(Registro registro) throws AplicacaoException {
		String dataFormatada = UtilDate.getDataFormatada(registro.getData(), Constantes.DD_MM_YYYY_HH_MM_SS);
		
		String tipoRegistroMsg035 = registro.getTipoRegistro().equals(TipoRegistro.ENTRADA) ? getLabel("entrada") : getLabel("saida");
		
		String mensagem = getMessageParametros("MSG035", new Object[] {
				registro.getMatricula().getAluno().getNome(),
				tipoRegistroMsg035,
				configuracaoEscolaBO.obterNomeEscola(),
				dataFormatada		
		});
		return mensagem;
	}

	public void validarCamposObrigatorios(Registro registro) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(registro)) {
			lancarMensagemSuportePadrao();
		} else {
			if (isNuloOuVazio(registro.getMatricula()) || isNuloOuVazio(registro.getMatricula().getMatricula())) {
				addMensagemCampo("matricula");
			}
			validarMensagens();
		} 
	}
	
	/**
	 * 
	 * @param registro
	 * @return RegistroWrapper com dados necessários para informar o usuário quem foi registrado
	 * @throws Exception 
	 */
	public RegistroWrapper salvar(Registro registro) throws Exception  {
		try {
			validarCamposObrigatorios(registro);
			validarExisteMatriculaDoRegistro(registro);
			registro.setData(UtilDate.getDataAtual());
			validarMensagens();
		} catch (NegocioException e) {
			registro.setMatricula(new Matricula());
			throw e;
		}
	
		registro = copiarDadosMatriculaAtualParaRegistro(registro);
		
		registro.setDataUltimaAtualizacao(null);
		registroDAO.salvar(registro);
		RegistroWrapper wrapper = getRegistroWapper(registro);
		
		/*
		 * 18/09/2014
		 * Apenas será enviado por JOB
		final Registro r = registro;
		
		//Envio será feito outra tentativa de envio por JOB em lote para emails e sms que não foram enviados
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					enviarEmailRegistro(r);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					enviarSmsRegistro(r);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		th.start();
		*/
		
		return wrapper;
	}

	/**
	 * Copia os dados da matricula atual para o registro 
	 * @param registro
	 * @return
	 */
	private Registro copiarDadosMatriculaAtualParaRegistro(Registro registro) {
		if (!isNuloOuVazio(registro)) {
			
			Matricula matricula = registro.getMatricula();
			
			if (!isNuloOuVazio(matricula)) {
			
				registro.setMatriculaDesc(matricula.getMatricula());
				registro.setMatriculaAnoLetivo(matricula.getAnoLetivo());
				registro.setMatriculaAtivo(matricula.getAtivo());
				registro.setMatriculaData(matricula.getData());
				registro.setMatriculaEnviarEmailRegistro(matricula.getEnviarEmailRegistro());
				registro.setMatriculaEnviarSmsRegistro(matricula.getEnviarSmsRegistro());
				registro.setMatriculaTurno(matricula.getTurno());
				
				registro.setEmailRegistroEnviado(Boolean.FALSE);
				registro.setSmsRegistroEnviado(Boolean.FALSE);
				
				Responsavel responsavel = matricula.getResponsavel();
				if (!isNuloOuVazio(responsavel)) {
					registro.setResponsavelEmail(responsavel.getEmail());
					registro.setResponsavelTelefoneCelular(responsavel.getTelefoneCelular());
				}
				
				Aluno aluno = matricula.getAluno();
				registro.setIdAluno(aluno.getId());
				registro.setAlunoAtivo(aluno.getAtivo());
				registro.setAlunoNome(aluno.getNome());
				
				Sala sala = matricula.getSala();
				if (!isNuloOuVazio(sala)) {
					registro.setIdSala(sala.getId());
					registro.setSalaAtivo(sala.getAtivo());
					registro.setSalaDescricao(sala.getDescricao());
				}
				
				Serie serie = matricula.getSerie();
				if (!isNuloOuVazio(serie)) {
					registro.setIdSerie(serie.getId());
					registro.setSerieAtivo(serie.getAtivo());
					registro.setSerieDescricao(serie.getDescricao());
				}
				
				Turma turma = matricula.getTurma();
				if (!isNuloOuVazio(turma)) {
					registro.setIdTurma(turma.getId());
					registro.setTurmaAtivo(turma.getAtivo());
					registro.setTurmaDescricao(turma.getDescricao());
				}
			}
			
		}
		return registro;
	}

	private RegistroWrapper getRegistroWapper(Registro registro) {
		Matricula matricula = registro.getMatricula();
		String dataFormatada = UtilDate.getDataFormatada(registro.getData(), Constantes.DD_MM_YYYY_HH_MM_SS);

		RegistroWrapper wrapper = new RegistroWrapper();
		wrapper.setMatricula(matricula.getMatricula());
		wrapper.setNomeAluno(matricula.getAluno().getNome());
		wrapper.setSerie(isNuloOuVazio(matricula.getSerie()) ? "-" : matricula.getSerie().getDescricao());
		wrapper.setTurma(isNuloOuVazio(matricula.getTurma()) ? "-" : matricula.getTurma().getDescricao());
		wrapper.setDataHoraRegistro(dataFormatada);
		wrapper.setFoto(matricula.getAluno().getFoto());
		
		return wrapper;
	}

	public void validarExisteMatriculaDoRegistro(Registro registro) throws NegocioException, AplicacaoException {
		Matricula matricula = matriculaBO.obterPorMatricula(registro.getMatricula());
		if (!isNuloOuVazio(matricula)) {
			registro.setMatricula(matricula);
		} else {
			addMensagem("MSG056");
		}
		validarMensagens();
	}

	/**
	 * Faz verificação de existe um registro dessa matricula, na data e com o mesmo tipo de ENTRADA ou SAIDA, 
	 * pois não pode ser repetido.
	 * @param registro
	 * @throws AplicacaoException 
	 */
	public boolean existeRegistroNaMesmaDataPorTipo(Registro registro) throws AplicacaoException {
		return registroDAO.obterRepetido(registro).size() > 1;
	}

	public List<Registro> obterListaPorParametros(Registro registro) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(registro)) {
			lancarMensagemSuportePadrao();
		}
		return registroDAO.obterListaPorParametros(registro);
	}
	
	public Map<Registro, String> obterSmsNaoEnviado() throws AplicacaoException {
		List<Registro> smsNaoEnviado = registroDAO.obterSmsNaoEnviado();
		Map<Registro, String> map = new HashMap<Registro, String>();
		for (Registro registro : smsNaoEnviado) {
			map.put(registro, getMensagem(registro));
		}
		return map;
	}
	
	public Map<Registro, EmailWrapper> obterEmailNaoEnviado() throws AplicacaoException {
		List<Registro> emailNaoEnviado = registroDAO.obterEmailNaoEnviado();
		Map<Registro, EmailWrapper> map = new HashMap<Registro, EmailWrapper>();
		for (Registro registro : emailNaoEnviado) {
			EmailWrapper wrapper = new EmailWrapper(
					getAssunto(registro),
					getMensagem(registro), 
					registro.getMatricula().getResponsavel().getEmail());
			map.put(registro, wrapper);
		}
		return map;
	}
	
	/**
	 * Salva que os sms foram enviados
	 * @param registros
	 */
	public void salvarEnvioSMS(List<Registro> registros) {
		for (Registro registro : registros) {
			registro.setSmsRegistroEnviado(Boolean.TRUE);
			registro.setDataUltimaAtualizacao(null);
			registroDAO.salvar(registro);
		}
	}
	
	/**
	 * Salva que os emails foram enviados
	 * @param registros
	 */
	public void salvarEnvioEmail(List<Registro> registros) {
		for (Registro registro : registros) {
			registro.setEmailRegistroEnviado(Boolean.TRUE);
			registro.setDataUltimaAtualizacao(null);
			registroDAO.salvar(registro);
		}
	}
	
	public void flush() {
		registroDAO.flush();
	}
	
	public void salvar(List<Registro> registros) {
		for (Registro registro : registros) {
			registro.setDataUltimaAtualizacao(null);
			registroDAO.salvar(registro);
		}
	}
	
	public Map<Registro, SmsEmailWrapper> obterNaoEnviado() throws AplicacaoException {
		List<Registro> naoEnviado = registroDAO.obterNaoEnviado();
		Map<Registro, SmsEmailWrapper> map = new HashMap<Registro, SmsEmailWrapper>();
		for (Registro registro : naoEnviado) {
			
			SmsEmailWrapper wrapper = null;
			
			if (registro.getMatriculaEnviarEmailRegistro()) {
				wrapper = new SmsEmailWrapper(
						getAssunto(registro),
						getMensagem(registro), 
						registro.getMatricula().getResponsavel().getEmail());
				wrapper.setMensagemDetalhes(wrapper.getMensagem().concat(". ").concat(getMessage("MSG062")));
			}
			else if (registro.getMatriculaEnviarSmsRegistro()) {
				wrapper = new SmsEmailWrapper(getMensagem(registro));
			}
			
			if (!isNuloOuVazio(wrapper)) {
				map.put(registro, wrapper);
			}
		}
		return map;
	}
	
	public void salvarEnvio(Registro registro) {
		registro.setDataUltimaAtualizacao(null);
		registroDAO.salvar(registro);
		registroDAO.flush();
	}

	public void atualizarNaoEnviarRepetido(Registro registro) {
		registro.setSmsRegistroEnviado(true);
		registro.setEmailRegistroEnviado(true);
		registro.setDataUltimaAtualizacao(null);
		registroDAO.salvar(registro);
		registroDAO.flush();
	}
	
	public int obterQtdRegistrosEntradaPorUsuario(Matricula matricula, Date periodoInicial, Date periodoFinal, Usuario usuario) throws AplicacaoException {
		return registroDAO.obterQtdRegistrosEntradaPorUsuario(matricula, periodoInicial, periodoFinal, usuario);
	}

	public List<Registro> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return registroDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, Registro.class);
	}
	
	public void salvarOnline(Registro registro) throws AplicacaoException, NegocioException {
		Registro retorno = obterPorCriterioOnline(registro);
		Long id = null;
		
		if (retorno == null) {
			retorno = new Registro();
		}
		else{
			id = retorno.getId();
		}
		
		copiarPropriedades(retorno, registro);
		retorno.setId(id);
		retorno.setMatricula(matriculaBO.obterPorCriterioOnline(retorno.getMatricula()));

		registroDAO.salvar(retorno);
	}

	public Registro obterPorCriterioOnline(Registro registro)
			throws AplicacaoException, NegocioException {
		List<Registro> lista = registroDAO.obterPorCriterioOnline(registro);
		String objeto = getLabel("registro") + " " + registro.getAlunoNome();
		Registro retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}

	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public RegistroDAO getRegistroDAO() {
		return registroDAO;
	}

	public void setRegistroDAO(RegistroDAO registroDAO) {
		this.registroDAO = registroDAO;
	}

}

package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.chbandeira.utilitario.Util;
import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.ParametroGeralDAO;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.ParametroGeral;
import br.com.w2c.model.wrapper.ParametroGeralWrapper;
import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 21/09/2013
 */
@Component
public class ParametroGeralBO extends BaseBO<ParametroGeral> {

	@Autowired
	private ParametroGeralDAO parametroGeralDAO;
	@Autowired
	private CalendarioEscolarBO calendarioEscolarBO;

	@Override
	public void validarCamposObrigatorios(ParametroGeral parametroGeral) throws NegocioException, AplicacaoException {
		super.validarCamposObrigatorios(parametroGeral);
		if (isNuloOuVazio(parametroGeral.getChave())) {
			addMensagemCampo("chave");
		}
		if (isNuloOuVazio(parametroGeral.getValor())) {
			addMensagemCampo("valor");
		}
		validarMensagens();
	}

	public void validarCamposObrigatorios(ParametroGeralWrapper wrapper) throws NegocioException, AplicacaoException {
		validarEntidade(wrapper);
		validarMensagens();
		if (isNuloOuVazio(wrapper.getAnoLetivo())) {
			addMensagemCampo("anoLetivo");
		}
		if (isNuloOuVazio(wrapper.getNomeRemetente())) {
			addMensagemCampo("nomeRemetente");
		}
		if (isNuloOuVazio(wrapper.getSenhaEmailRemetente())) {
			addMensagemCampo("senhaEmailRemetente");
		}
		if (isNuloOuVazio(wrapper.getEmailRemetente())) {
			addMensagemCampo("emailRemetente");
		}
		if (isNuloOuVazio(wrapper.getSmtpEmailServidor())) {
			addMensagemCampo("smtpEmailServidor");
		}
		validarMensagens();
	}
	
	public void setAnoLetivo(Matricula matricula) throws AplicacaoException {
		if (isNuloOuVazio(matricula.getAnoLetivo())) {
			ParametroGeral paramAnoLetivo = parametroGeralDAO.obterPorChave(br.com.w2c.model.enums.ParametroGeral.ANO_LETIVO.getChave());
			matricula.setAnoLetivo(Integer.valueOf(paramAnoLetivo.getValor()));
		}
	}

	public String obterVersaoBaseDados() throws AplicacaoException {
		ParametroGeral parametroGeral = parametroGeralDAO.obterPorChave(Constantes.VERSAO_BASE_DADOS);
		return isNuloOuVazio(parametroGeral.getValor()) ? "" : parametroGeral.getValor();
	}

	public String obterValorPorChave(String chave) throws AplicacaoException {
		ParametroGeral parametroGeral = parametroGeralDAO.obterPorChave(chave);
		return isNuloOuVazio(parametroGeral.getValor()) ? "" : parametroGeral.getValor();
	}

	public ParametroGeralWrapper obterParametrosConfiguracao() throws AplicacaoException {
		List<String> chaves = getStringParametrosConfiguracao();
		List<ParametroGeral> parametrosConfiguracao = parametroGeralDAO.obterPorChaves(chaves);
		return converterParaWraper(parametrosConfiguracao);
	}

	public List<String> getStringParametrosConfiguracao() {
		List<String> chaves = new ArrayList<String>();
		br.com.w2c.model.enums.ParametroGeral[] values = br.com.w2c.model.enums.ParametroGeral.values();
		if (Util.isNuloOuVazio(values)) {
			chaves.add("");
		} else {
			for (br.com.w2c.model.enums.ParametroGeral pg : values) {
				chaves.add(pg.getChave());
			}
		}
		return chaves;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void salvar(ParametroGeralWrapper wrapper) throws NegocioException, AplicacaoException {
		validarCamposObrigatorios(wrapper);
		validarMensagens();
		List<ParametroGeral> parametrosGerais = converterParaParametrosGerais(wrapper);
		for (ParametroGeral parametroGeral : parametrosGerais) {
			validarCamposObrigatorios(parametroGeral);
			parametroGeral.setDataUltimaAtualizacao(null);
			parametroGeralDAO.salvar(parametroGeral);
		}
	}

	public ParametroGeralWrapper converterParaWraper(List<ParametroGeral> parametrosConfiguracao) {
		ParametroGeralWrapper wrapper = new ParametroGeralWrapper();
		for (ParametroGeral pg : parametrosConfiguracao) {
			if (pg.getChave().equalsIgnoreCase(br.com.w2c.model.enums.ParametroGeral.ANO_LETIVO.getChave())) {
				wrapper.setAnoLetivo(Integer.valueOf(pg.getValor()));
			}
			if (pg.getChave().equalsIgnoreCase(br.com.w2c.model.enums.ParametroGeral.NOME_REMETENTE.getChave())) {
				wrapper.setNomeRemetente(pg.getValor());
			}
			if (pg.getChave().equalsIgnoreCase(br.com.w2c.model.enums.ParametroGeral.SENHA_EMAIL_REMETENTE.getChave())) {
				wrapper.setSenhaEmailRemetente(pg.getValor());
			}
			if (pg.getChave().equalsIgnoreCase(br.com.w2c.model.enums.ParametroGeral.EMAIL_REMETENTE.getChave())) {
				wrapper.setEmailRemetente(pg.getValor());
			}
			if (pg.getChave().equalsIgnoreCase(br.com.w2c.model.enums.ParametroGeral.SMTP_EMAIL_SERVIDOR.getChave())) {
				wrapper.setSmtpEmailServidor(pg.getValor());
			}
		}
		return wrapper;
	}

	public List<ParametroGeral> converterParaParametrosGerais(ParametroGeralWrapper wrapper) {
		List<ParametroGeral> parametrosGerais = new ArrayList<ParametroGeral>();
		
		ParametroGeral anoLetivo = new ParametroGeral();
		anoLetivo.setChave(br.com.w2c.model.enums.ParametroGeral.ANO_LETIVO.getChave());
		anoLetivo.setValor(String.valueOf(wrapper.getAnoLetivo()));
		parametrosGerais.add(anoLetivo);
		
		ParametroGeral emailRemetente = new ParametroGeral();
		emailRemetente.setChave(br.com.w2c.model.enums.ParametroGeral.EMAIL_REMETENTE.getChave());
		emailRemetente.setValor(wrapper.getEmailRemetente());
		parametrosGerais.add(emailRemetente);
		
		ParametroGeral nomeRementente = new ParametroGeral();
		nomeRementente.setChave(br.com.w2c.model.enums.ParametroGeral.NOME_REMETENTE.getChave());
		nomeRementente.setValor(wrapper.getNomeRemetente());
		parametrosGerais.add(nomeRementente);
		
		ParametroGeral senhaRementente = new ParametroGeral();
		senhaRementente.setChave(br.com.w2c.model.enums.ParametroGeral.SENHA_EMAIL_REMETENTE.getChave());
		senhaRementente.setValor(wrapper.getSenhaEmailRemetente());
		parametrosGerais.add(senhaRementente);
		
		ParametroGeral smtpServidor = new ParametroGeral();
		smtpServidor.setChave(br.com.w2c.model.enums.ParametroGeral.SMTP_EMAIL_SERVIDOR.getChave());
		smtpServidor.setValor(wrapper.getSmtpEmailServidor());
		parametrosGerais.add(smtpServidor);
		
		return parametrosGerais;
	}

	public List<ParametroGeral> obterParametrosPorChaves(List<String> chaves) throws AplicacaoException {
		return parametroGeralDAO.obterPorChaves(chaves);
	}

	public void salvarAnoLetivo(Integer anoLetivoAtual) throws AplicacaoException {
		ParametroGeral parametroAnoLetivo = obterParametroGeralAnoLetivo();
		parametroAnoLetivo.setValor(anoLetivoAtual.toString());
		parametroAnoLetivo.setDataUltimaAtualizacao(null);
		parametroGeralDAO.salvar(parametroAnoLetivo);
	}
	
	public ParametroGeral obterParametroGeralAnoLetivo() throws AplicacaoException {
		return parametroGeralDAO.obterPorChave(br.com.w2c.model.enums.ParametroGeral.ANO_LETIVO.getChave());
	}

	/**
	 * Atualiza o ano letivo quando desatualizado e atualiza as datas recorrentes do ano anterior, 
	 * criando as novas datas.
	 * @throws NegocioException 
	 * @throws AplicacaoException 
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void atualizarAnoLetivo() throws NegocioException, AplicacaoException {
		String anoLetivo = obterValorPorChave(br.com.w2c.model.enums.ParametroGeral.ANO_LETIVO.getChave());
		Integer anoLetivoAtual = UtilDate.getAnoAtual();
		if (isNuloOuVazio(anoLetivo) || anoLetivoAtual.compareTo(Integer.valueOf(anoLetivo)) != 0) {
			salvarAnoLetivo(anoLetivoAtual);
			calendarioEscolarBO.atualizarDatasRecorrentes(anoLetivoAtual);
		}
	}

	public String obterNomeEmpresa() throws AplicacaoException {
		ParametroGeral parametroGeral = parametroGeralDAO.obterPorChave(Constantes.NOME_EMPRESA);
		return isNuloOuVazio(parametroGeral.getValor()) ? "" : parametroGeral.getValor();
	}

	public String obterEmailContatoAdm() throws AplicacaoException {
		ParametroGeral parametroGeral = parametroGeralDAO.obterPorChave(Constantes.EMAIL_CONTATO_ADM);
		return isNuloOuVazio(parametroGeral.getValor()) ? "" : parametroGeral.getValor();
	}

	public ParametroGeral obterPorChave(String chave) throws AplicacaoException {
		return parametroGeralDAO.obterPorChave(chave);
	}

	public ParametroGeral obterPorChaveIdentificador(String chave, Long identificador) throws AplicacaoException {
		return parametroGeralDAO.obterPorChaveIdentificador(chave, identificador);
	}

	public List<ParametroGeral> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return parametroGeralDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, ParametroGeral.class);
	}

	public void salvar(ParametroGeral parametroGeral) {
		parametroGeral.setDataUltimaAtualizacao(null);
		parametroGeralDAO.salvar(parametroGeral);
	}

	public void salvarOnline(ParametroGeral parametroGeral) throws AplicacaoException, NegocioException {
		ParametroGeral retorno = obterPorCriterioOnline(parametroGeral);
		
		if (retorno == null) {
			retorno = new ParametroGeral();
			retorno.setIdentificador(parametroGeral.getIdentificador());
			retorno.setChave(parametroGeral.getChave());
		}
		
		retorno.setValor(parametroGeral.getValor());
		retorno.setDataUltimaAtualizacao(parametroGeral.getDataUltimaAtualizacao());

		parametroGeralDAO.salvar(retorno);
	}

	public ParametroGeral obterPorCriterioOnline(ParametroGeral parametroGeral)
			throws AplicacaoException, NegocioException {
		List<ParametroGeral> lista = parametroGeralDAO.obterPorCriterioOnline(parametroGeral);
		String objeto = getLabel("parametrosGerais") + " " + parametroGeral.getIdentificador();
		ParametroGeral retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}
	
	public String obterUltimaSincronizacaoWeb() throws AplicacaoException {
		ParametroGeral parametroGeral = parametroGeralDAO.obterPorChave(Constantes.ULTIMA_ATUALIZACAO_WEB);
		return isNuloOuVazio(parametroGeral.getValor()) ? "" : parametroGeral.getValor();
	}

	public ParametroGeralDAO getParametroGeralDAO() {
		return parametroGeralDAO;
	}
		
}

package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.ConfiguracaoEscolaDAO;
import br.com.w2c.model.domain.ConfiguracaoEscola;

/**
 * 
 * @author charlles
 * @since 21/09/2013
 */
@Component
public class ConfiguracaoEscolaBO extends BaseBO<ConfiguracaoEscola> {

	@Autowired
	private ConfiguracaoEscolaDAO configuracaoEscolaDAO;
	@Autowired
	private CalendarioEscolarBO calendarioEscolarBO;

	@Override
	public void validarCamposObrigatorios(ConfiguracaoEscola configuracaoEscola) throws NegocioException, AplicacaoException {
		super.validarCamposObrigatorios(configuracaoEscola);
		if (isNuloOuVazio(configuracaoEscola.getNome())) {
			addMensagemCampo("nome");
		}
		if (isNuloOuVazio(configuracaoEscola.getInicioAnoLetivo())) {
			addMensagemCampo("inicioAnoLetivo");
		}
		if (isNuloOuVazio(configuracaoEscola.getFimAnoLetivo())) {
			addMensagemCampo("fimAnoLetivo");
		}
		validarMensagens();
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void salvar(ConfiguracaoEscola configuracaoEscola) throws NegocioException, AplicacaoException {
		validarCamposObrigatorios(configuracaoEscola);
		verificarDatasAnoLetivo(configuracaoEscola);
		validarMensagens();
		configuracaoEscola.setDataUltimaAtualizacao(null);
		configuracaoEscolaDAO.salvar(configuracaoEscola);
		calendarioEscolarBO.gerarCalendarioEscolar(configuracaoEscola);
	}

	private void verificarDatasAnoLetivo(ConfiguracaoEscola configuracaoEscola) throws AplicacaoException {
		if (configuracaoEscola.getFimAnoLetivo().compareTo(configuracaoEscola.getInicioAnoLetivo())==0
				|| configuracaoEscola.getFimAnoLetivo().before(configuracaoEscola.getInicioAnoLetivo())) {
			
			addMensagem("MSG039");
		}
	}

	public String obterNomeEscola() throws AplicacaoException {
		return configuracaoEscolaDAO.obterNomeEscola();
	}

	public ConfiguracaoEscola obterConfiguracaoEscola() throws AplicacaoException {
		ConfiguracaoEscola configuracoesEscola = configuracaoEscolaDAO.obterConfiguracoesEscola();
		if (isNuloOuVazio(configuracoesEscola)) {
			configuracoesEscola = new ConfiguracaoEscola();
		}
		return configuracoesEscola;
	}
	
	public void atualizarConfiguracaoEscola(ConfiguracaoEscola configuracaoEscola) throws NegocioException, ParseException, AplicacaoException {
		boolean atualizar = false;
		if (isNuloOuVazio(configuracaoEscola.getNome())) {
			configuracaoEscola.setNome("Escola");
			atualizar = true;
		}
		if (isNuloOuVazio(configuracaoEscola.getInicioAnoLetivo())) {
			configuracaoEscola.setInicioAnoLetivo(UtilDate.getInicioAnoApenasData().getTime());
			atualizar = true;
		}
		if (isNuloOuVazio(configuracaoEscola.getFimAnoLetivo())) {
			configuracaoEscola.setFimAnoLetivo(UtilDate.getFimAnoApenasData().getTime());
			atualizar = true;
		}
		if (atualizar) {
			salvar(configuracaoEscola);
		}
	}

	public List<ConfiguracaoEscola> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return configuracaoEscolaDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, ConfiguracaoEscola.class);
	}

	public void salvarOnline(ConfiguracaoEscola configuracao) throws AplicacaoException, NegocioException {
		ConfiguracaoEscola retorno = obterPorCriterioOnline(configuracao);
		
		if (retorno == null) {
			retorno = new ConfiguracaoEscola();
			retorno.setIdentificador(configuracao.getIdentificador());
		}
		
		retorno.setCodigo(configuracao.getCodigo());
		retorno.setFimAnoLetivo(configuracao.getFimAnoLetivo());
		retorno.setInicioAnoLetivo(configuracao.getInicioAnoLetivo());
		retorno.setUa(configuracao.getUa());
		retorno.setNome(configuracao.getNome());
		retorno.setDataUltimaAtualizacao(configuracao.getDataUltimaAtualizacao());
		
		configuracaoEscolaDAO.salvar(retorno);
	}

	public ConfiguracaoEscola obterPorCriterioOnline(
			ConfiguracaoEscola configuracao) throws AplicacaoException,
			NegocioException {
		List<ConfiguracaoEscola> lista = configuracaoEscolaDAO.obterPorCriterioOnline(configuracao);
		String objeto = getLabel("escola") + " " + configuracao.getNome();
		ConfiguracaoEscola retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}

	public ConfiguracaoEscolaDAO getConfiguracaoEscolaDAO() {
		return configuracaoEscolaDAO;
	}
	
}

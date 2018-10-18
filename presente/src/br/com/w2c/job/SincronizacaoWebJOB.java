package br.com.w2c.job;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.controller.business.ParametroGeralBO;
import br.com.w2c.controller.business.SincronizacaoBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.ParametroGeral;
import br.com.w2c.model.wrapper.ConfirmacaoSincronizacaoWrapper;
import br.com.w2c.sinc.SincronizacaoClient;
import br.com.w2c.sinc.SincronizacaoService;
import br.com.w2c.util.SpringUtil;

/**
 * 
 * @author Charlles
 * @since 20/01/2016
 */
public class SincronizacaoWebJOB implements Job {

	private static Logger log = LogManager.getLogger();

	private SincronizacaoBO sincronizacaoBO = SpringUtil.getBean("sincronizacaoBO");
	
	private ParametroGeralBO parametroGeralBO = SpringUtil.getBean("parametroGeralBO");
	
				
	@Override
	public void execute(JobExecutionContext jobExec) throws JobExecutionException {
		
		ConfirmacaoSincronizacaoWrapper confirmacaoSincronizacao = new ConfirmacaoSincronizacaoWrapper();
		try {
			
			if (isJobSincronizacaoLigado()) {
				ParametroGeral parametroUltimaAtualizacaoWEB = parametroGeralBO.obterPorChave(br.com.w2c.model.enums.ParametroGeral.ULTIMA_ATUALIZACAO_WEB.getChave());
				
				confirmacaoSincronizacao.setDataHoraUltimaAtualizacao(UtilDate.getDataFormatada(parametroUltimaAtualizacaoWEB.getDataUltimaAtualizacao(), UtilDate.DD_MM_YYYY_HH_MM_SS));
				confirmacaoSincronizacao.setDataHoraNovaAtualizacao(UtilDate.getDataAtualFormatada(UtilDate.DD_MM_YYYY_HH_MM_SS));
				confirmacaoSincronizacao.setIdentificador(parametroUltimaAtualizacaoWEB.getIdentificador());
				
				sincronizacaoBO.iniciarSincronizacao(confirmacaoSincronizacao);
				
				String json = sincronizacaoBO.obterDadosParaSincronizacao(confirmacaoSincronizacao);
				SincronizacaoService sinc = SincronizacaoClient.criar();
				String retorno = sinc.enviarPacote(json);
				if (retorno.equals("erro")) { 
					throw new AplicacaoException("Erro na sincronizacao");
				}
				sincronizacaoBO.finalizarSincronizacao(confirmacaoSincronizacao, retorno);
			}
		} catch (Exception e) {
			sincronizacaoBO.erroSincronizacao(confirmacaoSincronizacao);
			log.error("Erro ao sincronizar dados com WEB", e);
		}
	}

	private static Boolean isJobSincronizacaoLigado() throws AplicacaoException {
		ParametroGeralBO parametroGeralBO = SpringUtil.getBean("parametroGeralBO");
		ParametroGeral paramSincronizacaoLigado = parametroGeralBO.obterPorChave(br.com.w2c.model.enums.ParametroGeral.JOB_SINCRONIZACAO_LIGADO.getChave());
		
		log.info("Job de sincronizacao ligado: "+paramSincronizacaoLigado.getValor());
		
		return Boolean.valueOf(paramSincronizacaoLigado.getValor());
	}
}

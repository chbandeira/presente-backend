package br.com.w2c.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import br.com.w2c.controller.business.CalendarioEscolarBO;
import br.com.w2c.controller.business.ConfiguracaoEscolaBO;
import br.com.w2c.model.domain.ConfiguracaoEscola;
import br.com.w2c.util.SpringUtil;

/**
 * 
 * @author charlles
 * @since Roda alguns métodos ao iniciar o servidor
 */
@Component
public class InitServer {
	
	private static Logger logger = LogManager.getLogger();

	static {
		atualizarConfiguracaoEscolar();
		gerarCalendarioEscolar();
	}

	private static void gerarCalendarioEscolar() {
		try {
			CalendarioEscolarBO calendarioEscolarBO = SpringUtil.getBean("calendarioEscolarBO");
			ConfiguracaoEscolaBO configuracaoEscolaBO = SpringUtil.getBean("configuracaoEscolaBO");;
			
			ConfiguracaoEscola configuracaoEscola = configuracaoEscolaBO.obterConfiguracaoEscola();
			
			calendarioEscolarBO.gerarCalendarioEscolar(configuracaoEscola);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private static void atualizarConfiguracaoEscolar() {
		try {
			ConfiguracaoEscolaBO configuracaoEscolaBO = SpringUtil.getBean("configuracaoEscolaBO");;
			
			ConfiguracaoEscola configuracaoEscola = configuracaoEscolaBO.obterConfiguracaoEscola();
			configuracaoEscolaBO.atualizarConfiguracaoEscola(configuracaoEscola);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
}

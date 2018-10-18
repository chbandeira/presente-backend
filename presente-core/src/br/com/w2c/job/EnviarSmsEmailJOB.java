package br.com.w2c.job;

import static br.com.chbandeira.utilitario.UtilEmail.isEmailValido;
import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.w2c.controller.business.RegistroBO;
import br.com.w2c.model.domain.Registro;
import br.com.w2c.model.wrapper.SmsEmailWrapper;
import br.com.w2c.util.Constantes;
import br.com.w2c.util.SpringUtil;
import br.com.w2c.util.email.EnviarEmail;
import br.com.w2c.util.sms.HttpPostComteleSMS;

/**
 * 
 * @author Charlles
 * @since 05/05/2014
 */
public class EnviarSmsEmailJOB implements Job {

	private static final int QTD_CARACTERES_TELEFONE_COM_DDI = 13;
	private static final int QTD_CARACTERES_TELEFONE_SEM_DDI = 11;

	private static Logger log = LogManager.getLogger();
	
	private RegistroBO registroBO;
	
	private HttpPostComteleSMS comteleSMS = new HttpPostComteleSMS();
	
	private Map<Registro, SmsEmailWrapper> map;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			
			registroBO = SpringUtil.getBean("registroBO");
			
			log.info("Iniciando envio de SMS e EMAIL em lote");
			
			map = registroBO.obterNaoEnviado();
			
			if (!isNuloOuVazio(map)) {
			
				registroBO = SpringUtil.getBean("registroBO");
				
				for (Entry<Registro, SmsEmailWrapper> entry : map.entrySet()) {
					String email = entry.getValue().getDestinatario();
					if (entry.getKey().getMatriculaEnviarEmailRegistro() 
							&& !entry.getKey().getEmailRegistroEnviado() 
							&& !isNuloOuVazio(email) 
							&& isEmailValido(email)) {
						
						entry.getValue().setEnviarEmail(true);
						entry.getKey().setEmailRegistroEnviado(Boolean.TRUE);
						log.info("Salvando envio EMAIL");
						registroBO.salvarEnvio(entry.getKey());
					}
					
					String telefoneCelular = entry.getKey().getResponsavelTelefoneCelular();
					if (entry.getKey().getMatriculaEnviarSmsRegistro()
							&& !entry.getKey().getSmsRegistroEnviado() 
							&& !isNuloOuVazio(telefoneCelular)) {
						
						String celular = obterTelefoneCelularComDDI(telefoneCelular);
						if (isCelularValido(celular)) {
							entry.getValue().setEnviarSMS(true);
							entry.getKey().setSmsRegistroEnviado(Boolean.TRUE);
							log.info("Salvando envio SMS");
							registroBO.salvarEnvio(entry.getKey());
						}
					}
				}
				
				for (Entry<Registro, SmsEmailWrapper> entry : map.entrySet()) {
					enviarSMS(entry);
					enviarEmail(entry);
				}
			}
			
			log.info("Finalizando envio de SMS e EMAIL em lote");
			
		} catch (Exception e) {
			log.error("Erro ao enviar SMS e EMAIL em lote", e);
		}
	}

	private String obterTelefoneCelularComDDI(String telefoneCelular) {
		String celular = telefoneCelular.replace("-", "");
		celular = celular.replace("(", "");
		celular = celular.replace(")", "");
		if (celular.length()==QTD_CARACTERES_TELEFONE_SEM_DDI) {
			celular = Constantes.DDI_LOCAL+celular;
		}
		return celular;
	}

	private void enviarEmail(Entry<Registro, SmsEmailWrapper> entry) {
		String email = entry.getValue().getDestinatario();
		if (entry.getKey().getMatriculaEnviarEmailRegistro() 
				&& entry.getValue().isEnviarEmail()
				&& !isNuloOuVazio(email) 
				&& isEmailValido(email)) {
			try {
				EnviarEmail.enviarEmail(entry.getValue().getEmailWrapper());
				
			} catch (Exception e) {
				log.error("Erro ao enviar email para: "+email, e);
			}
		}
	}

	private void enviarSMS(Entry<Registro, SmsEmailWrapper> entry) {
		String telefoneCelular = entry.getKey().getResponsavelTelefoneCelular();
		if (entry.getKey().getMatriculaEnviarSmsRegistro()
				&& entry.getValue().isEnviarSMS()
				&& !isNuloOuVazio(telefoneCelular)) {
			
			String celular = obterTelefoneCelularComDDI(telefoneCelular);
			if (isCelularValido(celular)) {
				try {
					comteleSMS.enviarSMS(entry.getValue().getMensagem(), celular);
					
				} catch (Exception e) {
					log.error("Erro ao enviar SMS para: "+celular, e);
				}
			}
		}
	}

	private boolean isCelularValido(String celular) {
		return celular.length()==QTD_CARACTERES_TELEFONE_COM_DDI;
	}

}

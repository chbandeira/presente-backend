package br.com.w2c.controller.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.w2c.controller.business.ConfiguracaoEscolaBO;
import br.com.w2c.controller.business.ParametroGeralBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.ConfiguracaoEscola;
import br.com.w2c.util.Constantes;
import br.com.w2c.util.SpringUtil;

/**
 * 
 * @author charlles
 * @since 27/11/2013
 */
public class ConfiguracoesFilter implements Filter {
	
	private ParametroGeralBO parametroGeralBO = SpringUtil.getBean("parametroGeralBO");
	private ConfiguracaoEscolaBO configuracaoEscolaBO = SpringUtil.getBean("configuracaoEscolaBO");
	
	private void setVersaoSistema(ServletRequest request, HttpSession session) throws MalformedURLException, FileNotFoundException, IOException {
		ServletContext servletContext = request.getServletContext();
		String projeto = servletContext.getContextPath();
		String path = servletContext.getRealPath("").concat(Constantes.MAVEN_POM_LOCAL + projeto + "/" + Constantes.MAVEN_POM);
		File file = new File(path);
		if(file.exists()){
			InputStream is = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(is);
			String versionPom = properties.getProperty("version");
			session.setAttribute(Constantes.SESSION_VERSAO_SISTEMA, versionPom);
		}
	}
	
	private void setVersaoBaseDados(HttpSession session) throws AplicacaoException {
		String versaoBaseDados = parametroGeralBO.obterVersaoBaseDados();
		session.setAttribute(Constantes.SESSION_VERSAO_BASE_DADOS, versaoBaseDados);
	}
	
	private void setConfiguracoesEscola(HttpSession session) throws AplicacaoException {
		ConfiguracaoEscola configuracaoEscola = configuracaoEscolaBO.obterConfiguracaoEscola();
		session.setAttribute(Constantes.SESSION_CONFIGURACAO_ESCOLA, configuracaoEscola);
	}
	
	private void setLicenca(HttpSession session) {
//		session.setAttribute(Constantes.SESSION_LICENCA, "Copyright ".concat(ChavePrivada.ANO));
		session.setAttribute(Constantes.SESSION_LICENCA, "Copyright "+getAnoAtual());
	}
	
	private static int getAnoAtual() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.YEAR);
	}
	
	private void setNomeEmpresa(HttpSession session) throws AplicacaoException {
		String nomeEmpresa = parametroGeralBO.obterNomeEmpresa();
		session.setAttribute(Constantes.SESSION_NOME_EMPRESA, nomeEmpresa);
	}
	
	private void setEmailContatoAdm(HttpSession session) throws AplicacaoException {
		String emailContatoAdm = parametroGeralBO.obterEmailContatoAdm();
		session.setAttribute(Constantes.SESSION_EMAIL_CONTATO_ADM, emailContatoAdm);
	}
	
	private void setVersionJava(HttpSession session) {
		session.setAttribute(Constantes.SESSION_JRE_VERSION, System.getProperty("java.version"));
		session.setAttribute(Constantes.SESSION_JRE_IMPL_VERSION, System.getProperty("java.vm.version"));
	}

	private void setVersionSO(HttpSession session) {
		String os = System.getProperty("os.name") + " / " + System.getProperty("os.arch") + " / " + System.getProperty("os.version");
		session.setAttribute(Constantes.SESSION_OS, os);
	}
	
	private void setQtdLimiteLicenca(HttpSession session) {
		//TODO limitar se possivel
		session.setAttribute(Constantes.SESSION_QTD_LIMITE_LICENCA, Constantes.QUANTIDADE_MAX_LICENCA);
	}
	
	private void setSessionUltimaSincronizacaoWeb(HttpSession session) throws AplicacaoException {
		String valor = parametroGeralBO.obterUltimaSincronizacaoWeb();
		session.setAttribute(Constantes.SESSION_ULTIMA_SINCRONIZACAO_WEB, valor);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		if (session != null) {
			setVersaoSistema(request, session);
			try {
				setVersaoBaseDados(session);
				setConfiguracoesEscola(session);
				setLicenca(session);
				setNomeEmpresa(session);
				setEmailContatoAdm(session);
				setVersionSO(session);
				setVersionJava(session);
				setQtdLimiteLicenca(session);
				setNomeChaveEscola(session);
				setSessionUltimaSincronizacaoWeb(session);
			} catch (AplicacaoException e) {
				e.printStackTrace();
			}
		}
		
		chain.doFilter(request, response);
	}

	private void setNomeChaveEscola(HttpSession session) {
		session.setAttribute(Constantes.SESSION_NOME_CHAVE_ESCOLA, "-"/*ChavePrivada.ESCOLA*/);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}

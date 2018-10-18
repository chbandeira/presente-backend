package br.com.w2c.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.w2c.controller.business.ParametroGeralBO;
import br.com.w2c.util.SpringUtil;

/**
 * 
 * @author charlles
 * @since 27/11/2013
 */
public class AnoLetivoFilter implements Filter {

	private Logger logger = LogManager.getLogger();
	private ParametroGeralBO parametroGeralBO = SpringUtil.getBean("parametroGeralBO");
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			parametroGeralBO.atualizarAnoLetivo();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}

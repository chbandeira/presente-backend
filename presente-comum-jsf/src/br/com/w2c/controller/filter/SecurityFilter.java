package br.com.w2c.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import br.com.w2c.controller.business.UsuarioBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.util.Constantes;
import br.com.w2c.util.SpringUtil;

/**
 * 
 * @author charlles
 * @since 12/10/2013
 */
public class SecurityFilter implements Filter {

	private Usuario usuario = new Usuario();
	
	private UsuarioBO usuarioBO = SpringUtil.getBean("usuarioBO");
	
	@Override
	public void init(FilterConfig config) throws ServletException {
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);

		if (session != null) {
			validarLicenca();
			try {
				setUsuarioSession(session);
			} catch (AplicacaoException e) {
				e.printStackTrace();
			}
		}
		
		chain.doFilter(request, response);
	}

	private void validarLicenca() {
		//TODO não validar
		/*if (!ValidarChave.isValido()) {
			throw new ValidacaoLicencaException();
		}*/
	}

	private void setUsuarioSession(HttpSession session) throws AplicacaoException {
		SecurityContext context = SecurityContextHolder.getContext();

		if (context instanceof SecurityContext) {
			Authentication authentication = context.getAuthentication();
			if (authentication instanceof Authentication) {
				if (!authentication.getPrincipal().equals(Constantes.ANONYMOUS_USER)) {
					this.usuario.setLogin(((User) authentication.getPrincipal()).getUsername());
					Usuario usuario = usuarioBO.obterPorLogin(this.usuario.getLogin());
					if (usuario != null && usuario.getLogin() != null) {
						this.usuario = usuario;
						session.setAttribute(Constantes.SESSION_USUARIO, usuario);
					}
				}
			}
		}
	}

	@Override
	public void destroy() {
	}

}

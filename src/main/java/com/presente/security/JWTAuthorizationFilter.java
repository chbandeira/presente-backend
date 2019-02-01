package com.presente.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.presente.services.JWTService;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTService jwtUtil;
	private UserDetailsService userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTService jwtUtil,
			UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken auth = this.getAuthentication(header.substring(7));
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (jwtUtil.tokenValido(token)) {
			String username = this.jwtUtil.getUsername(token);
			UserDetails user = this.userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}
}

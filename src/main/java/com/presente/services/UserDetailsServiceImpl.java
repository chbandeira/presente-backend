package com.presente.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.presente.domains.Responsavel;
import com.presente.repositories.ResponsavelRepository;
import com.presente.security.UserAuth;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ResponsavelRepository responsavelRepository;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Responsavel> responsavel = this.responsavelRepository.findByEmailAndAtivo(email, true);
		if (responsavel.isEmpty()) {
			responsavel = this.responsavelRepository.findByEmail2AndAtivo(email, true);
			if (responsavel.isEmpty()) {
				throw new UsernameNotFoundException(email);
			}
		}
		return new UserAuth(responsavel.get().getId(), responsavel.get().getEmail(), responsavel.get().getSenha(),
				responsavel.get().getPerfis());
	}

}

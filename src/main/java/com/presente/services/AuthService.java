package com.presente.services;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.presente.domains.Responsavel;
import com.presente.exceptions.ObjectNotFoundException;
import com.presente.repositories.ResponsavelRepository;

@Service
public class AuthService {
	
	@Autowired
	private ResponsavelRepository responsavelRepository;
	@Autowired 
	private BCryptPasswordEncoder pe;
	@Autowired
	private EmailService emailService;
	
	private Random random = new Random(); 

	@Transactional
	public void sendNewPassword(String email) {
		Optional<Responsavel> responsavel = this.responsavelRepository.findByEmailAndAtivo(email, true);
		if (responsavel.isEmpty()) {
			responsavel = this.responsavelRepository.findByEmail2AndAtivo(email, true);
			if (responsavel.isEmpty()) {
				throw new ObjectNotFoundException("Email não encontrado");
			}
		}
		String newPass = this.newPassword();
		responsavel.get().setSenha(pe.encode(newPass));
		this.responsavelRepository.save(responsavel.get());
		this.emailService.sendNewPasswordEmail(responsavel.get(), newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = this.randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = this.random.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (this.random.nextInt(10) + 48);
		} else if (opt == 1) { // gera letra maiuscula
			return (char) (this.random.nextInt(26) + 65);
		} else { // gera letra minuscula
			return (char) (this.random.nextInt(26) + 97);
		}
	}
}

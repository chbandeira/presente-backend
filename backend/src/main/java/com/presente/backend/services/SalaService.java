package com.presente.backend.services;

import org.springframework.stereotype.Service;

import com.presente.backend.domains.Sala;

@Service
public class SalaService {

	public Sala setDescricao(String descricao, Sala sala) {
		if (descricao == null || descricao.isBlank()) {
			return null;
		}
		if (sala == null) {			
			return new Sala(descricao);
		} else {
			sala.setDescricao(descricao);
			return sala;
		}
	}
}

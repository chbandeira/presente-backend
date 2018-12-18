package com.presente.backend.services;

import org.springframework.stereotype.Service;

import com.presente.backend.domains.Turma;

@Service
public class TurmaService {
	
	public Turma setDescricao(String descricao, Turma turma) {
		if (descricao == null || descricao.isBlank()) {
			return null;
		}
		if (turma == null) {			
			return new Turma(descricao);
		} else {
			turma.setDescricao(descricao);
			return turma;
		}
	}
}

package com.presente.backend.services;

import org.springframework.stereotype.Service;

import com.presente.backend.domains.Serie;

@Service
public class SerieService {
	
	public Serie setDescricao(String descricao, Serie serie) {
		if (descricao == null || descricao.isBlank()) {
			return null;
		}
		if (serie == null) {			
			return new Serie(descricao);
		} else {
			serie.setDescricao(descricao);
			return serie;
		}
	}
}

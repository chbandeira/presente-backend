package com.presente.services;

import org.springframework.stereotype.Service;

import com.presente.domains.Telefone;
import com.presente.dto.TelefoneDTO;

@Service
public class TelefoneService {
	
	public Telefone fromDTO(TelefoneDTO dto) {
		return new Telefone(dto.getTipo(), dto.getTelefone(), dto.getDescricao());
	}

}

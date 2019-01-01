package com.presente.backend.services;

import org.springframework.stereotype.Service;

import com.presente.backend.domains.Responsavel;
import com.presente.backend.dto.AlunoCadastroDTO;

/**
 * @author Charlles Bandeira
 *
 */
@Service
public class ResponsavelService {

	public Responsavel fromAlunoCadastroDto(AlunoCadastroDTO dto, Responsavel responsavel) {
		if (dto.getNomeResponsavel() == null || dto.getNomeResponsavel().isBlank()) {
			return null;
		}
		if (responsavel == null) {
			responsavel = new Responsavel();
			responsavel.setAtivo(true);
		}
		responsavel.setNome(dto.getNomeResponsavel());
		responsavel.setCpf(dto.getCpf());
		responsavel.setEmail(dto.getEmail());
		responsavel.setEmail2(dto.getEmail2());
		responsavel.setTelefoneFixo(dto.getTelefoneFixo());
		responsavel.setTelefoneCelular(dto.getTelefoneCelular());
		// TODO login responsavel
		//responsavel.setLogin(dto.getlogin());
		return responsavel;

	}

}

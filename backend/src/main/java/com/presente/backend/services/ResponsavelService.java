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

	public Responsavel fromAlunoCadastroDTO(AlunoCadastroDTO dto) {
		Responsavel responsavel = new Responsavel();
		responsavel.setNome(dto.getNomeResponsavel());
		responsavel.setCpf(dto.getCpf());
		responsavel.setEmail(dto.getEmail());
		responsavel.setTelefoneFixo(dto.getTelefoneFixo());
		responsavel.setTelefoneCelular(dto.getTelefoneCelular());
		// TODO set usuario no responsavel
//		responsavel.setUsuario(usuario);
		return responsavel;
	}

}

package com.presente.services;

import org.springframework.stereotype.Service;

import com.presente.domains.Responsavel;
import com.presente.dto.AlunoCadastroDTO;
import com.presente.exceptions.StandardValidationException;

/**
 * @author Charlles Bandeira
 *
 */
@Service
public class ResponsavelService {

	public Responsavel fromAlunoCadastroDto(AlunoCadastroDTO dto, Responsavel responsavel) {
		if (responsavel == null) {
			responsavel = new Responsavel();
		}
		responsavel.setNome(dto.getNomeResponsavel());
		responsavel.setCpf(dto.getCpf());
		responsavel.setEmail(dto.getEmail());
		responsavel.setEmail2(dto.getEmail2());
		responsavel.setTelefoneFixo(dto.getTelefoneFixo());
		responsavel.setTelefoneCelular(dto.getTelefoneCelular());
		if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
			responsavel.setSenha(dto.getSenha());
		}

		if (isResponsavelFilled(responsavel) || dto.isEnviarEmail() || dto.isEnviarMensagem()) {
			if (responsavel.getNome() == null || responsavel.getNome().isBlank()) {
				throw new StandardValidationException("Necessário informar o nome do responsável!");
			} else {
				return responsavel;
			}
		}

		return null;
	}

	private boolean isResponsavelFilled(Responsavel responsavel) {
		if (responsavel.getNome() != null && !responsavel.getNome().isBlank()) {
			return true;
		}
		if (responsavel.getCpf() != null && !responsavel.getCpf().isBlank()) {
			return true;
		}
		if (responsavel.getEmail() != null && !responsavel.getEmail().isBlank()) {
			return true;
		}
		if (responsavel.getEmail2() != null && !responsavel.getEmail2().isBlank()) {
			return true;
		}
		if (responsavel.getTelefoneFixo() != null && !responsavel.getTelefoneFixo().isBlank()) {
			return true;
		}
		if (responsavel.getTelefoneCelular() != null && !responsavel.getTelefoneCelular().isBlank()) {
			return true;
		}
		return false;
	}

}

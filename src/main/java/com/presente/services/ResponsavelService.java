package com.presente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.presente.domains.Responsavel;
import com.presente.dto.AlunoCadastroDTO;
import com.presente.repositories.ResponsavelRepository;

/**
 * @author Charlles Bandeira
 *
 */
@Service
public class ResponsavelService {
	
	@Autowired
	private ResponsavelRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

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
		if (responsavel.getId() == null) {
			responsavel.setSenha(this.encoder.encode(dto.getMatricula()));
		}
		return responsavel;
	}

	public void disable(Responsavel responsavel) {
		responsavel.setAtivo(false);
		this.repository.save(responsavel);
	}

}

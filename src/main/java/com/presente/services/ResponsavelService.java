package com.presente.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.presente.domains.Responsavel;
import com.presente.domains.Telefone;
import com.presente.dto.AlunoCadastroDTO;
import com.presente.dto.ResponsavelDTO;
import com.presente.dto.TelefoneDTO;
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
	private TelefoneService telefoneService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Responsavel fromAlunoCadastroDto(AlunoCadastroDTO dto, Responsavel responsavel) {
		if (responsavel == null) {
			responsavel = new Responsavel();
			responsavel.setId(dto.getIdResponsavel());
		}
		responsavel.setNome(dto.getNomeResponsavel());
		responsavel.setCpf(dto.getCpf());
		responsavel.setEmail(dto.getEmail());
		responsavel.setEmail2(dto.getEmail2());
		responsavel.setEnviarEmailRegistro(dto.isEnviarEmail());
		responsavel.setEnviarMensagem(dto.isEnviarMensagem());
		responsavel.getTelefones().clear();
		if (dto.getTelefones() != null) {			
			for (TelefoneDTO tel : dto.getTelefones()) {			
				Telefone telefone = this.telefoneService.fromDTO(tel);
				responsavel.addTelefone(telefone);
			}
		}
		if (responsavel.getId() == null && dto.getMatricula() != null && !dto.getMatricula().isBlank()) {
			responsavel.setSenha(this.encoder.encode(dto.getMatricula()));
		}
		return responsavel;
	}

	public void disable(Responsavel responsavel) {
		responsavel.setAtivo(false);
		this.repository.save(responsavel);
	}
	

	public Responsavel save(Responsavel responsavel) {
		return this.repository.save(responsavel);
	}

	public Page<ResponsavelDTO> searchByTerm(String term) {
		PageRequest pageable = PageRequest.of(0, 10, Direction.ASC, "nome");
		return repository.findAllByAtivoAndNomeContainsOrCpfContainsOrEmailContainsOrEmail2Contains(
				true, term, term, term, term, pageable).map(r -> new ResponsavelDTO(r));
	}

	public Optional<Responsavel> findById(Integer idResponsavel) {
		return this.repository.findById(idResponsavel);
	}

}

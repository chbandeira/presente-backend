package com.presente.services.validations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.presente.domains.Responsavel;
import com.presente.dto.AlunoCadastroDTO;
import com.presente.exceptions.FieldMessage;
import com.presente.repositories.AlunoRepository;
import com.presente.repositories.ResponsavelRepository;
import com.presente.services.ResponsavelService;

public abstract class AlunoAbstractValidator {
	
	@Autowired
	protected AlunoRepository repository;

	@Autowired
	protected ResponsavelRepository responsavelRepository;
	
	@Autowired
	protected ResponsavelService responsavelService;

	protected void validateEmails(List<FieldMessage> list, String email, String email2) {
		if (isEmailAdicionalValid(email, email2)) {
			addMessageEmailAdicionalInvalid(list);
		}
		if (areEmailsEqual(email, email2)) {
			addMessageEmailsEqual(list);
		}
	}

	protected void validateEmail(List<FieldMessage> list, String email, String field) {
		Optional<Responsavel> responsavelFound = this.responsavelRepository.findByEmailAndAtivo(email, true);
		if (responsavelFound.isPresent()) {
			addMessageEmailExists(list, field);
		} else {
			responsavelFound = this.responsavelRepository.findByEmail2AndAtivo(email, true);
			if (responsavelFound.isPresent()) {
				addMessageEmailExists(list, field);
			}
		}
	}

	private void addMessageEmailAdicionalInvalid(List<FieldMessage> list) {
		list.add(new FieldMessage("email", "Informe neste campo ao invés do adicional"));
	}

	private void addMessageEmailsEqual(List<FieldMessage> list) {
		list.add(new FieldMessage("email2", "Email idêntico ao primeiro"));
	}

	protected void addMessageMatriculaExists(List<FieldMessage> list) {
		list.add(new FieldMessage("matricula", "Matrícula já existe"));
	}

	protected void addMessageNomeExists(List<FieldMessage> list) {
		list.add(new FieldMessage("nome", "Nome já existe"));
	}

	protected void addMessageResponsavelInvalid(List<FieldMessage> list) {
		list.add(new FieldMessage("nomeResponsavel", "Necessário informar o nome do responsável!"));
	}

	protected void addMessageEmailExists(List<FieldMessage> list, String field) {
		list.add(new FieldMessage(field, "Email já existe"));
	}

	protected boolean isResponsavelFilled(Responsavel responsavel) {
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
		if (!responsavel.getTelefones().isEmpty()) {
			return true;
		}
		return false;
	}

	protected boolean isResponsavelValid(AlunoCadastroDTO dto) {
		Responsavel responsavel = this.responsavelService.fromAlunoCadastroDto(dto, null);
		if (isResponsavelFilled(responsavel) || dto.isEnviarEmail() || dto.isEnviarMensagem()) {
			if (responsavel.getNome() == null || responsavel.getNome().isBlank()) {
				return false;
			}
		}
		return true;
	}

	protected boolean isEmailAdicionalValid(String email, String email2) {
		return (email == null || email.isBlank()) && (email2 != null && !email2.isBlank());
	}

	protected boolean areEmailsEqual(String email, String email2) {
		return email != null && !email.isBlank() && email2 != null && !email2.isBlank() && email.equals(email2);
	}
}

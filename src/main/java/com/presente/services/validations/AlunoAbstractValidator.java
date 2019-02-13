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
		list.add(new FieldMessage("matricula", "Matrícula já utilizado"));
	}

	protected void addMessageNomeExists(List<FieldMessage> list) {
		list.add(new FieldMessage("nome", "Nome já utilizado"));
	}

	protected void addMessageResponsavelInvalid(List<FieldMessage> list) {
		list.add(new FieldMessage("nomeResponsavel", "Necessário informar o nome do responsável!"));
	}

	protected void addMessageEmailExists(List<FieldMessage> list, String field) {
		list.add(new FieldMessage(field, "Email já utilizado"));
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

	protected void addMessageCpfExists(List<FieldMessage> list) {
		list.add(new FieldMessage("cpf", "CPF já utilizado. Selecione o responsável existente ou informe outro CPF."));
	}
	
	protected void validadeCpf(List<FieldMessage> list, String cpf) {
		if (cpf != null && !cpf.isBlank()) {			
			Optional<Responsavel> responsavelFound = this.responsavelRepository.findByCpfAndAtivo(cpf, true);
			if (responsavelFound.isPresent()) {
				this.addMessageCpfExists(list);
			}
		}
	}

	protected void validadeNomeResponsavel(List<FieldMessage> list, String nomeResponsavel) {
		if (nomeResponsavel != null && !nomeResponsavel.isBlank()) {	
			Optional<Responsavel> responsavelFound = this.responsavelRepository.findByNomeAndAtivo(nomeResponsavel, true);
			if (responsavelFound.isPresent()) {
				this.addMessageNomeResponsavelExists(list);
			}
		}
	}

	protected void addMessageNomeResponsavelExists(List<FieldMessage> list) {
		list.add(new FieldMessage("nomeResponsavel", "Nome do Responsável já utilizado"));
	}
	
	protected void validadeResponsavel(AlunoCadastroDTO value, List<FieldMessage> list) {
		if (value.getIdResponsavel() == null) {
			if (!isResponsavelValid(value)) {
				addMessageResponsavelInvalid(list);
			}
			validateEmails(list, value.getEmail(), value.getEmail2());
			if (value.getEmail() != null && !value.getEmail().isBlank()) {
				validateEmail(list, value.getEmail(), "email");
				if (value.getEmail2() != null && !value.getEmail2().isBlank()) {
					validateEmail(list, value.getEmail2(), "email2");
				}
			}
			validadeCpf(list, value.getCpf());
			validadeNomeResponsavel(list, value.getNomeResponsavel());
		}
	}
}

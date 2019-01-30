package com.presente.services.validations;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.presente.domains.Aluno;
import com.presente.domains.Responsavel;
import com.presente.dto.AlunoUpdateDTO;
import com.presente.exceptions.FieldMessage;
import com.presente.repositories.AlunoRepository;
import com.presente.repositories.ResponsavelRepository;

public class AlunoUpdateValidator implements ConstraintValidator<AlunoUpdate, AlunoUpdateDTO> {
	
	@Autowired
	private AlunoRepository repository;

	@Autowired
	private ResponsavelRepository responsavelRepository;

	@Override
	public void initialize(AlunoUpdate constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(AlunoUpdateDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		this.validateMatricula(list, value.getMatricula(), value.getId());
		this.validateNome(list, value.getNome(), value.getId());
		this.validateEmails(list, value.getEmail(), value.getEmail2(), value.getId());

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessageString()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

	private void validateNome(List<FieldMessage> list, String nome, Integer id) {
		Optional<Aluno> alunoFound = this.repository.findByNomeAndAnoLetivoAndIdNot(nome, Year.now().getValue(), id);
		if (alunoFound.isPresent()) {
			list.add(new FieldMessage("nome", "Nome já existe"));
		}
	}

	private void validateMatricula(List<FieldMessage> list, String matricula, Integer id) {
		Optional<Aluno> alunoFound = this.repository.findByMatriculaAndAnoLetivoAndIdNot(matricula, Year.now().getValue(), id);
		if (alunoFound.isPresent()) {
			list.add(new FieldMessage("matricula", "Matrícula já existe"));
		}
	}

	private void validateEmails(List<FieldMessage> list, String email, String email2, Integer id) {
		if ((email == null || email.isBlank()) && (email2 != null && !email2.isBlank())) {
			list.add(new FieldMessage("email", "Informe neste campo ao invés do adicional"));
		}
		if (email != null && !email.isBlank() && email2 != null && !email2.isBlank() && email.equals(email2)) {
			list.add(new FieldMessage("email2", "Email idêntico ao primeiro"));
		}
		if (email != null && !email.isBlank()) {
			this.validateEmail(list, email, "email", id);
			if (email2 != null && !email2.isBlank()) {
				this.validateEmail(list, email2, "email2", id);
			}
		}
	}

	private void validateEmail(List<FieldMessage> list, String email, String field, Integer id) {
		Optional<Responsavel> responsavelFound = this.responsavelRepository.findByEmailAndIdNot(email, id);
		if (responsavelFound.isPresent()) {
			list.add(new FieldMessage(field, "Email já existe"));
		} else {
			responsavelFound = this.responsavelRepository.findByEmail2AndIdNot(email, id);
			if (responsavelFound.isPresent()) {
				list.add(new FieldMessage(field, "Email já existe"));
			}
		}
	}
}

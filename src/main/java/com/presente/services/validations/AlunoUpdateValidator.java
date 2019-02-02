package com.presente.services.validations;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.presente.domains.Aluno;
import com.presente.domains.Responsavel;
import com.presente.dto.AlunoUpdateDTO;
import com.presente.exceptions.FieldMessage;

public class AlunoUpdateValidator extends AlunoAbstractValidator implements ConstraintValidator<AlunoUpdate, AlunoUpdateDTO> {

	@Override
	public void initialize(AlunoUpdate constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(AlunoUpdateDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		this.validateMatricula(value, list);
		this.validadeNome(value, list);
		this.validadeResponsavel(value, list);
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessageString()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

	private void validadeResponsavel(AlunoUpdateDTO value, List<FieldMessage> list) {
		if (!super.isResponsavelValid(value)) {
			super.addMessageResponsavelInvalid(list);
		}
		super.validateEmails(list, value.getEmail(), value.getEmail2());
		Optional<Aluno> alunoFound = this.repository.findById(value.getId());
		if (value.getEmail() != null && !value.getEmail().isBlank()) {
			if (value.getEmail() != null && !value.getEmail().isBlank()) {
				this.validateEmail(list, value.getEmail(), "email", alunoFound);
				if (value.getEmail2() != null && !value.getEmail2().isBlank()) {
					this.validateEmail(list, value.getEmail2(), "email2", alunoFound);
				}
			}
		}
	}

	private void validadeNome(AlunoUpdateDTO value, List<FieldMessage> list) {
		Optional<Aluno> alunoFound = this.repository.findByNomeAndAnoLetivoAndAtivoAndIdNot(value.getNome(), Year.now().getValue(), true, value.getId());
		if (alunoFound.isPresent()) {
			super.addMessageNomeExists(list);
		}
	}

	private void validateMatricula(AlunoUpdateDTO value, List<FieldMessage> list) {
		Optional<Aluno> alunoFound = this.repository.findByMatriculaAndAnoLetivoAndAtivoAndIdNot(value.getMatricula(), Year.now().getValue(), true, value.getId());
		if (alunoFound.isPresent()) {
			super.addMessageMatriculaExists(list);
		}
	}
	
	private void validateEmail(List<FieldMessage> list, String email, String field, Optional<Aluno> aluno) {
		if (aluno.isPresent() && aluno.get().getResponsavel() != null) {
			Optional<Responsavel> responsavelFound = this.responsavelRepository.findByEmailAndAtivoAndIdNot(email, true, aluno.get().getResponsavel().getId());
			if (responsavelFound.isPresent()) {
				addMessageEmailExists(list, field);
			} else {
				responsavelFound = this.responsavelRepository.findByEmail2AndAtivoAndIdNot(email, true, aluno.get().getResponsavel().getId());
				if (responsavelFound.isPresent()) {
					addMessageEmailExists(list, field);
				}
			}
		} else {
			super.validateEmail(list, email, field);
		}
	}
	
}

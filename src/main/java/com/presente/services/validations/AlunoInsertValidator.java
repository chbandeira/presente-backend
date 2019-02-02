package com.presente.services.validations;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.presente.domains.Aluno;
import com.presente.dto.AlunoInsertDTO;
import com.presente.exceptions.FieldMessage;

public class AlunoInsertValidator extends AlunoAbstractValidator implements ConstraintValidator<AlunoInsert, AlunoInsertDTO> {

	@Override
	public void initialize(AlunoInsert constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(AlunoInsertDTO value, ConstraintValidatorContext context) {
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

	private void validadeResponsavel(AlunoInsertDTO value, List<FieldMessage> list) {
		if (!super.isResponsavelValid(value)) {
			super.addMessageResponsavelInvalid(list);
		}
		super.validateEmails(list, value.getEmail(), value.getEmail2());
		if (value.getEmail() != null && !value.getEmail().isBlank()) {
			super.validateEmail(list, value.getEmail(), "email");
			if (value.getEmail2() != null && !value.getEmail2().isBlank()) {
				super.validateEmail(list, value.getEmail2(), "email2");
			}
		}
	}

	private void validadeNome(AlunoInsertDTO value, List<FieldMessage> list) {
		Optional<Aluno> alunoFound = this.repository.findByNomeAndAnoLetivoAndAtivo(value.getNome(), Year.now().getValue(), true);
		if (alunoFound.isPresent()) {
			super.addMessageNomeExists(list);
		}
	}

	private void validateMatricula(AlunoInsertDTO value, List<FieldMessage> list) {
		Optional<Aluno> alunoFound = this.repository.findByMatriculaAndAnoLetivoAndAtivo(value.getMatricula(), Year.now().getValue(), true);
		if (alunoFound.isPresent()) {
			super.addMessageMatriculaExists(list);
		}
	}

}

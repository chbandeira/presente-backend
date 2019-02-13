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
		super.validadeResponsavel(value, list);
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessageString()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
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

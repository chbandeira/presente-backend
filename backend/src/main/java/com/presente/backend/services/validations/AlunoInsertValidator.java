package com.presente.backend.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.presente.backend.dto.AlunoCadastroDTO;
import com.presente.backend.exceptions.FieldMessage;

public class AlunoInsertValidator implements ConstraintValidator<AlunoInsert, AlunoCadastroDTO> {
	
	@Override
	public void initialize(AlunoInsert constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(AlunoCadastroDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessageString())
				.addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}

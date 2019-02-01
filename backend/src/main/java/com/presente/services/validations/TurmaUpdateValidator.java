package com.presente.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.presente.domains.Turma;
import com.presente.dto.TurmaUpdateDTO;
import com.presente.exceptions.FieldMessage;
import com.presente.services.TurmaService;

public class TurmaUpdateValidator implements ConstraintValidator<TurmaUpdate, TurmaUpdateDTO> {
	
	@Autowired
	private TurmaService service;

	@Override
	public void initialize(TurmaUpdate constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(TurmaUpdateDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		this.validate(list, value);
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessageString()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
	
	private void validateAlreadyInserted(List<FieldMessage> list, TurmaUpdateDTO dto) {
		Optional<Turma> turmaFound = this.service.findOne(dto);
		if (turmaFound.isPresent() && !turmaFound.get().getId().equals(dto.getId())) {
			list.add(new FieldMessage("descricao", "Já existe uma turma igual cadastrada!"));
		}
	}

	private void validate(List<FieldMessage> list, TurmaUpdateDTO dto) {
		if ((dto.getDescricao() == null || dto.getDescricao().isBlank())
				&& (dto.getSerie() == null || dto.getSerie().isBlank())
				&& (dto.getSala() == null || dto.getSala().isBlank())) {
			list.add(new FieldMessage("descricao", "Informe ao menos Turma, Série e/ou Sala!"));
		}
		if (list.isEmpty()) {
			this.validateAlreadyInserted(list, dto);
		}
	}
}

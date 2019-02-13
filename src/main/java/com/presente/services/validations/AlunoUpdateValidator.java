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
		super.validadeResponsavel(value, list);
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessageString())
				.addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}

	// TODO será reaproveitado para cadastro de responsavel
	private void validadeResponsavel(AlunoUpdateDTO value, List<FieldMessage> list) {
		if (value.getIdResponsavel() == null) {
			if (!super.isResponsavelValid(value)) {
				super.addMessageResponsavelInvalid(list);
			}
			Optional<Aluno> alunoFound = this.repository.findById(value.getId());
			super.validateEmails(list, value.getEmail(), value.getEmail2());
			if (value.getEmail() != null && !value.getEmail().isBlank()) {
				if (value.getEmail() != null && !value.getEmail().isBlank()) {
					this.validateEmail(list, value.getEmail(), "email", alunoFound);
					if (value.getEmail2() != null && !value.getEmail2().isBlank()) {
						this.validateEmail(list, value.getEmail2(), "email2", alunoFound);
					}
				}
			}
			this.validadeCpf(list, value, alunoFound);
			this.validadeNomeResponsavel(list, value, alunoFound);
		}
	}

	private void validadeNome(AlunoUpdateDTO value, List<FieldMessage> list) {
		Optional<Aluno> alunoFound = this.repository.findByNomeAndAnoLetivoAndAtivoAndIdNot(value.getNome(), Year.now().getValue(), true, value.getId());
		if (alunoFound.isPresent()) {
			super.addMessageNomeExists(list);
		}
	}

	private void validateMatricula(AlunoUpdateDTO value, List<FieldMessage> list) {
		Optional<Aluno> alunoFound = this.repository
				.findByMatriculaAndAnoLetivoAndAtivoAndIdNot(value.getMatricula(), Year.now().getValue(), true, value.getId());
		if (alunoFound.isPresent()) {
			super.addMessageMatriculaExists(list);
		}
	}
	
	private void validateEmail(List<FieldMessage> list, String email, String field, Optional<Aluno> aluno) {
		if (aluno.isPresent() && aluno.get().getResponsavel() != null) {
			Optional<Responsavel> responsavelFound = this.responsavelRepository
					.findByEmailAndAtivoAndIdNot(email, true, aluno.get().getResponsavel().getId());
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

	private void validadeCpf(List<FieldMessage> list, AlunoUpdateDTO dto, Optional<Aluno> aluno) {
		if (dto.getCpf() != null && !dto.getCpf().isBlank()) {						
			if (aluno.isPresent() && aluno.get().getResponsavel() != null) {			
				Optional<Responsavel> responsavelFound = this.responsavelRepository
						.findByCpfAndAtivoAndIdNot(dto.getCpf(), true, aluno.get().getResponsavel().getId());
				if (responsavelFound.isPresent()) {
					super.addMessageCpfExists(list);
				}
			} else {
				super.validadeCpf(list, dto.getCpf());
			}
		}
	}
	
	protected void validadeNomeResponsavel(List<FieldMessage> list, AlunoUpdateDTO dto, Optional<Aluno> aluno) {
		if (dto.getNomeResponsavel() != null && !dto.getNomeResponsavel().isBlank()) {	
			if (aluno.isPresent() && aluno.get().getResponsavel() != null) {					
				Optional<Responsavel> responsavelFound = this.responsavelRepository
						.findByNomeAndAtivoAndIdNot(dto.getNomeResponsavel(), true, aluno.get().getResponsavel().getId());
				if (responsavelFound.isPresent()) {
					this.addMessageNomeResponsavelExists(list);
				}
			} else {
				super.validadeNomeResponsavel(list, dto.getNomeResponsavel());
			}
		}
	}
}

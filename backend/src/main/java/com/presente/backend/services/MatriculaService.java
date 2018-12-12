package com.presente.backend.services;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.presente.backend.domains.Matricula;
import com.presente.backend.domains.Sala;
import com.presente.backend.domains.Serie;
import com.presente.backend.domains.Turma;
import com.presente.backend.domains.enums.Turno;
import com.presente.backend.dto.AlunoCadastroDTO;

@Service
public class MatriculaService {
	
	@Autowired
	private ResponsavelService responsavelService;

	public Matricula fromAlunoCadastroDTO(AlunoCadastroDTO dto) {
		Matricula matricula = new Matricula(dto.getMatricula());
		matricula.setAtivo(true);
		if (dto.getSala() != null && !dto.getSala().isBlank()) {			
			matricula.setSala(new Sala(dto.getSala()));
		}
		if (dto.getSerie() != null && !dto.getSerie().isBlank()) {			
			matricula.setSerie(new Serie(dto.getSerie()));
		}
		if (dto.getTurma() != null && !dto.getTurma().isBlank()) {			
			matricula.setTurma(new Turma(dto.getTurma()));
		}
		if (dto.getTurno() != null && !dto.getTurno().isBlank()) {
			matricula.setTurno(Turno.toEnum(dto.getTurno()));
		}
		matricula.setBolsista(dto.isAlunoBolsista());
		if (dto.getNomeResponsavel() != null && !dto.getNomeResponsavel().isBlank()) {			
			matricula.setResponsavel(this.responsavelService.fromAlunoCadastroDTO(dto));
		}
		// TODO set anoletivo conforme data cadastrada
		matricula.setAnoLetivo(Year.now().getValue());
		matricula.setEnviarEmailRegistro(dto.isEnviarEmail());
		matricula.setEnviarSmsRegistro(dto.isEnviarSMS());
		return matricula;
	}

}

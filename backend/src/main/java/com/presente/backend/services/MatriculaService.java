package com.presente.backend.services;

import java.time.Year;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.presente.backend.domains.Matricula;
import com.presente.backend.domains.enums.Turno;
import com.presente.backend.dto.AlunoCadastroDTO;
import com.presente.backend.exceptions.ObjectNotFoundException;
import com.presente.backend.repositories.MatriculaRepository;

@Service
public class MatriculaService {

	@Autowired
	private MatriculaRepository repository;

	@Autowired
	private ResponsavelService responsavelService;
	@Autowired
	private SalaService salaService;
	@Autowired
	private SerieService serieService;
	@Autowired
	private TurmaService turmaService;
	@Autowired
	private AlunoService alunoService;

	public Matricula fromAlunoCadastroDTO(AlunoCadastroDTO dto, Matricula matricula) {
		if (matricula == null) {
			matricula = new Matricula();
			matricula.setAtivo(true);
			// TODO set anoletivo conforme data cadastrada
			matricula.setAnoLetivo(Year.now().getValue());
		}
		matricula.setMatricula(dto.getMatricula());
		matricula.setAluno(this.alunoService.fromDTO(dto, matricula.getAluno()));
		matricula.setSala(this.salaService.setDescricao(dto.getSala(), matricula.getSala()));
		matricula.setSerie(this.serieService.setDescricao(dto.getSerie(), matricula.getSerie()));
		matricula.setTurma(this.turmaService.setDescricao(dto.getTurma(), matricula.getTurma()));
		if (dto.getTurno() != null) {
			matricula.setTurno(Turno.toEnum(dto.getTurno()));
		}
		matricula.setBolsista(dto.isAlunoBolsista());
		matricula.setResponsavel(this.responsavelService.fromAlunoCadastroDTO(dto, matricula.getResponsavel()));
		matricula.setEnviarEmailRegistro(dto.isEnviarEmail());
		matricula.setEnviarSmsRegistro(dto.isEnviarSMS());
		return matricula;
	}

	public AlunoCadastroDTO findByIdMatricula(Long idMatricula) {
		Optional<Matricula> mat = this.repository.findById(idMatricula);
		if (mat.isEmpty()) {
			throw new ObjectNotFoundException("Matricula não encontrada");
		}
		return new AlunoCadastroDTO(mat.get());
	}
	
	public void disableByIdMatricula(Long idMatricula) {
		Optional<Matricula> mat = this.repository.findById(idMatricula);
		if (mat.isEmpty()) {
			throw new ObjectNotFoundException("Matricula não encontrada");
		}
		mat.get().setAtivo(false);
		this.repository.save(mat.get()).getAtivo();
	}

}

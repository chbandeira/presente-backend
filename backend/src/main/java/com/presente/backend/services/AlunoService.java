package com.presente.backend.services;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

import java.time.Year;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.presente.backend.domains.Aluno;
import com.presente.backend.domains.Matricula;
import com.presente.backend.dto.AlunoCadastroDTO;
import com.presente.backend.dto.AlunoDTO;
import com.presente.backend.exceptions.ObjectNotFoundException;
import com.presente.backend.repositories.AlunoRepository;
import com.presente.backend.repositories.MatriculaRepository;
import com.presente.backend.repositories.ResponsavelRepository;
import com.presente.backend.repositories.SalaRepository;
import com.presente.backend.repositories.SerieRepository;
import com.presente.backend.repositories.TurmaRepository;
import com.presente.backend.services.utils.URL;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private SalaRepository salaRepository;
	@Autowired
	private SerieRepository serieRepository;
	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired
	private ResponsavelRepository responsavelRepository;
	
	@Autowired
	private MatriculaService matriculaService;
	@Autowired
	private HistoricoAlteracaoService historicoAlteracaoService;

	@Transactional
	public Long save(AlunoCadastroDTO dto) {
		Aluno aluno = this.saveAluno(dto);
		Matricula matricula = this.matriculaService.fromAlunoCadastroDTO(dto);
		matricula.setAluno(aluno);
		this.saveSala(matricula);
		this.saveSerie(matricula);
		this.saveTurma(matricula);
		this.saveResponsavel(matricula);
		this.matriculaRepository.save(matricula);
		this.historicoAlteracaoService.saveFromMatricula(matricula);
		return matricula.getId();
	}

	private Aluno saveAluno(AlunoCadastroDTO dto) {
		return this.repository.save(this.fromDTO(dto));
	}

	private void saveResponsavel(Matricula matricula) {
		if (matricula.getResponsavel() != null) {
			matricula.setResponsavel(this.responsavelRepository.save(matricula.getResponsavel()));
		}
	}

	private void saveTurma(Matricula matricula) {
		if (matricula.getTurma() != null) {			
			matricula.setTurma(this.turmaRepository.save(matricula.getTurma()));
		}
	}

	private void saveSerie(Matricula matricula) {
		if (matricula.getSerie() != null) {			
			matricula.setSerie(this.serieRepository.save(matricula.getSerie()));
		}
	}

	private void saveSala(Matricula matricula) {
		if (matricula.getSala() != null) {			
			matricula.setSala(this.salaRepository.save(matricula.getSala()));
		}
	}

	public Aluno fromDTO(AlunoCadastroDTO dto) {
		Aluno aluno = new Aluno(dto.getNome());
		aluno.setDataNascimento(dto.getDataNascimento());
		aluno.setFoto(dto.getUriFoto());
		return aluno;
	}

	public Page<AlunoDTO> search(String nome, String codMatricula, Integer anoLetivo, Integer page, Integer size, String direction) {

		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), "aluno.nome", "matricula", "anoLetivo");

		Matricula matricula = new Matricula();

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		if (!nome.isBlank()) {
			matricula.setAluno(new Aluno(URL.decodeParam(nome)));
			matcher = matcher.withMatcher("aluno.nome", contains());
		}
		if (!codMatricula.isBlank()) {
			matricula.setMatricula(codMatricula);
			matcher = matcher.withMatcher("matricula", exact());
		}
		if (anoLetivo != null) {
			matricula.setAnoLetivo(anoLetivo);
		} else {
			matricula.setAnoLetivo(Year.now().getValue());
		}
		matcher = matcher.withMatcher("anoLetivo", exact());
		Example<Matricula> example = Example.of(matricula, matcher);

		return this.matriculaRepository.findAll(example, pageRequest).map(mat -> new AlunoDTO(mat));
	}

	public AlunoCadastroDTO findByIdMatricula(Long idMatricula) {
		Optional<Matricula> mat = this.matriculaRepository.findById(idMatricula);
		if (mat.isEmpty()) {
			throw new ObjectNotFoundException("Aluno não encontrado com esse id de matrícula");
		}
		return new AlunoCadastroDTO(mat.get());
	}

}

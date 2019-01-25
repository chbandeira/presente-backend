package com.presente.backend.services;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.presente.backend.domains.Turma;
import com.presente.backend.domains.enums.Turno;
import com.presente.backend.dto.AlunoCadastroDTO;
import com.presente.backend.dto.TurmaDTO;
import com.presente.backend.exceptions.ObjectNotFoundException;
import com.presente.backend.exceptions.StandardValidationException;
import com.presente.backend.repositories.TurmaRepository;


/**
 * @author Charlles Bandeira
 *
 */
@Service
public class TurmaService {

	@Autowired
	private TurmaRepository repository;

	public Turma fromAlunoCadastroDto(AlunoCadastroDTO dto) {
		Turma turma = null;
		if ((dto.getTurma() == null || dto.getTurma().isBlank()) && (dto.getSerie() == null || dto.getSerie().isBlank())
				&& (dto.getSala() == null || dto.getSala().isBlank()) && (dto.getTurno() == null)) {
			return null;
		}
		Example<Turma> example = this
				.getExampleExactly(new TurmaDTO(dto.getTurma(), dto.getSerie(), dto.getSala(), dto.getTurno()));
		Optional<Turma> turmaFound = this.repository.findOne(example);
		if (turmaFound.isPresent()) {
			turma = turmaFound.get();
		} else {
			turma = new Turma();
		}
		if (dto.getTurma() != null && !dto.getTurma().isBlank()) {
			turma.setDescricao(dto.getTurma());
		}
		if (dto.getSerie() != null && !dto.getSerie().isBlank()) {
			turma.setSerie(dto.getSerie());
		}
		if (dto.getSala() != null && !dto.getSala().isBlank()) {
			turma.setSala(dto.getSala());
		}
		if (dto.getTurno() != null) {
			turma.setTurno(Turno.toEnum(dto.getTurno()));
		}
		turma.setDataUltimaAtualizacao(new Date());
		return turma;
	}

	public Turma fromDto(TurmaDTO dto, Turma turma) {
		if (turma == null) {
			turma = new Turma();
		}
		if (dto.getDescricao() != null) {
			turma.setDescricao(dto.getDescricao());
		}
		if (dto.getSerie() != null) {
			turma.setSerie(dto.getSerie());
		}
		if (dto.getSala() != null) {
			turma.setSala(dto.getSala());
		}
		if (dto.getTurno() != null) {
			turma.setTurno(Turno.toEnum(dto.getTurno()));
		}
		turma.setDataUltimaAtualizacao(new Date());
		return turma;
	}

	public Page<TurmaDTO> search(TurmaDTO dto, Integer page, Integer size, String direction, String orderBy) {
		PageRequest pageable = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		Example<Turma> example = this.getExampleToSearch(dto);
		return repository.findAll(example, pageable).map(t -> new TurmaDTO(t));
	}

	private Example<Turma> getExampleToSearch(TurmaDTO dto) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Turma turma = new Turma();
		if (dto.getDescricao() != null && !dto.getDescricao().isBlank()) {
			turma.setDescricao(dto.getDescricao());
			matcher = matcher.withMatcher("descricao", contains());
		}
		if (dto.getSerie() != null && !dto.getSerie().isBlank()) {
			turma.setSerie(dto.getSerie());
			matcher = matcher.withMatcher("serie", contains());
		}
		if (dto.getSala() != null && !dto.getSala().isBlank()) {
			turma.setSala(dto.getSala());
			matcher = matcher.withMatcher("sala", contains());
		}
		if (dto.getTurno() != null) {
			turma.setTurno(Turno.toEnum(dto.getTurno()));
			matcher = matcher.withMatcher("turno", exact());
		}
		matcher = matcher.withMatcher("ativo", exact());
		return Example.of(turma, matcher);
	}

	private Example<Turma> getExampleExactly(TurmaDTO dto) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues();
		Turma turma = new Turma();
		if (dto.getDescricao() != null && !dto.getDescricao().isBlank()) {
			turma.setDescricao(dto.getDescricao());
		}
		if (dto.getSerie() != null && !dto.getSerie().isBlank()) {
			turma.setSerie(dto.getSerie());
		}
		if (dto.getSala() != null && !dto.getSala().isBlank()) {
			turma.setSala(dto.getSala());
		}
		if (dto.getTurno() != null) {
			turma.setTurno(Turno.toEnum(dto.getTurno()));
		}
		matcher = matcher.withMatcher("descricao", exact());
		matcher = matcher.withMatcher("serie", exact());
		matcher = matcher.withMatcher("sala", exact());
		matcher = matcher.withMatcher("turno", exact());
		matcher = matcher.withMatcher("ativo", exact());
		matcher = matcher.withIgnorePaths("id", "dataUltimaAtualizacao");
		return Example.of(turma, matcher);
	}

	public Integer save(TurmaDTO dto) {
		this.validate(dto);
		Turma turma = null;
		if (dto.getId() != null) {
			Optional<Turma> turmaFound = this.repository.findById(dto.getId());
			if (turmaFound.isPresent()) {
				turma = turmaFound.get();
			}
		}
		turma = this.fromDto(dto, turma);
		this.repository.save(turma);
		return turma.getId();
	}

	private void validate(TurmaDTO dto) {
		if ((dto.getDescricao() == null || dto.getDescricao().isBlank())
				&& (dto.getSerie() == null || dto.getSerie().isBlank())
				&& (dto.getSala() == null || dto.getSala().isBlank())) {
			throw new StandardValidationException("Informe ao menos Turma, Série e/ou Sala!");
		}
	}

	public TurmaDTO findById(Integer id) {
		Optional<Turma> turma = this.repository.findById(id);
		if (turma.isEmpty()) {
			throw new ObjectNotFoundException("Turma não encontrada!");
		}
		return new TurmaDTO(turma.get());
	}

	public void disableById(Integer id) {
		Optional<Turma> turma = this.repository.findById(id);
		if (turma.isEmpty()) {
			throw new ObjectNotFoundException("Turma não encontrada!");
		}
		turma.get().setAtivo(false);
		this.repository.save(turma.get());
	}
}

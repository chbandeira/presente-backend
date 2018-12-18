package com.presente.backend.services;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

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
import com.presente.backend.repositories.MatriculaRepository;
import com.presente.backend.services.utils.URL;

@Service
public class AlunoService {

	@Autowired
	private MatriculaRepository matriculaRepository;

	@Autowired
	private MatriculaService matriculaService;
	@Autowired
	private HistoricoAlteracaoService historicoAlteracaoService;

	@Transactional
	public Long save(AlunoCadastroDTO dto) {
		Matricula matricula = null;
		boolean inclusao = true;
		if (dto.getIdMatricula() != null) {
			Optional<Matricula> matriculaFound = matriculaRepository.findById(dto.getIdMatricula());
			if (matriculaFound.isPresent()) {
				matricula = matriculaFound.get();
				inclusao = false;
			}
		}
		matricula = this.matriculaService.fromAlunoCadastroDTO(dto, matricula);
		this.matriculaRepository.save(matricula);
		this.historicoAlteracaoService.saveFromMatricula(matricula, inclusao);
		return matricula.getId();
	}

	public Aluno fromDTO(AlunoCadastroDTO dto, Aluno aluno) {
		if (aluno == null) {
			aluno = new Aluno();
		}
		aluno.setNome(dto.getNome());
		aluno.setDataNascimento(dto.getDataNascimento());
		aluno.setFoto(dto.getUriFoto());
		return aluno;
	}

	public Page<AlunoDTO> search(String nome, String codMatricula, Integer anoLetivo, Integer page, Integer size,
			String direction) {

		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), "aluno.nome", "matricula",
				"anoLetivo");

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
			matcher = matcher.withMatcher("anoLetivo", exact());
		}
		matricula.setAtivo(true);
		matcher = matcher.withMatcher("ativo", exact());
		
		Example<Matricula> example = Example.of(matricula, matcher);

		return this.matriculaRepository.findAll(example, pageRequest).map(mat -> new AlunoDTO(mat));
	}

}

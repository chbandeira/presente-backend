package com.presente.backend.services;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

import java.time.Year;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.presente.backend.domains.Aluno;
import com.presente.backend.dto.AlunoCadastroDTO;
import com.presente.backend.dto.AlunoDTO;
import com.presente.backend.exceptions.ObjectNotFoundException;
import com.presente.backend.exceptions.ValidationException;
import com.presente.backend.repositories.AlunoRepository;
import com.presente.backend.services.utils.URL;

@Service
public class AlunoService {

	@Autowired
	private ResponsavelService responsavelService;
	@Autowired
	private TurmaService turmaService;
	@Autowired
	private HistoricoAlteracaoAlunoService historicoAlteracaoService;

	@Autowired
	private AlunoRepository repository;

	@Transactional
	public Integer save(AlunoCadastroDTO dto) {
		Aluno aluno = null;
		boolean inclusao = true;
		if (dto.getId() != null) {
			this.validateMatricula(dto.getMatricula(), dto.getId());
			Optional<Aluno> alunoFound = this.repository.findById(dto.getId());
			if (alunoFound.isPresent()) {
				aluno = alunoFound.get();
				inclusao = false;
			}
		} else {			
			this.validateMatricula(dto.getMatricula());
		}
		aluno = this.fromAlunoCadastroDTO(dto, aluno);
		if (inclusao) {			
			aluno.setAtivo(true);
			aluno.setDataMatricula(new Date());
		}
		this.repository.save(aluno);
		this.historicoAlteracaoService.save(aluno, inclusao);
		return aluno.getId();
	}

	private void validateMatricula(String matricula) {
		Optional<Aluno> alunoFound = this.repository.findByMatriculaAndAnoLetivo(matricula, Year.now().getValue());
		if (alunoFound.isPresent()) {
			throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "matricula", "Matrícula já existe");
		}
	}

	private void validateMatricula(String matricula, Integer id) { 
		Optional<Aluno> alunoFound = this.repository.findByMatriculaAndAnoLetivoAndIdNot(matricula, Year.now().getValue(), id);
		if (alunoFound.isPresent()) {
			throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "matricula", "Matrícula já existe");
		}
	}

	public Aluno fromDTO(AlunoCadastroDTO dto, Aluno aluno) {
		if (aluno == null) {
			aluno = new Aluno();
		}
		aluno.setNome(dto.getNome());
		aluno.setDataNascimento(dto.getDataNascimento());
		aluno.setUrlFoto(dto.getUrlFoto());
		return aluno;
	}

	public Page<AlunoDTO> search(String nome, String codMatricula, Integer anoLetivo, Integer page, Integer size,
			String direction) {

		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), "nome", "matricula",
				"anoLetivo");

		Aluno aluno = new Aluno();

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		if (!nome.isBlank()) {
			aluno.setNome(URL.decodeParam(nome));
			matcher = matcher.withMatcher("nome", contains());
		}
		if (!codMatricula.isBlank()) {
			aluno.setMatricula(codMatricula);
			matcher = matcher.withMatcher("matricula", exact());
		}
		if (anoLetivo != null) {
			aluno.setAnoLetivo(anoLetivo);
			matcher = matcher.withMatcher("anoLetivo", exact());
		}
		aluno.setAtivo(true);
		matcher = matcher.withMatcher("ativo", exact());

		Example<Aluno> example = Example.of(aluno, matcher);

		return this.repository.findAll(example, pageRequest).map(mat -> new AlunoDTO(mat));
	}

	public Aluno fromAlunoCadastroDTO(AlunoCadastroDTO dto, Aluno aluno) {
		if (aluno == null) {
			aluno = new Aluno();
			// TODO set anoletivo conforme data cadastrada
			aluno.setAnoLetivo(Year.now().getValue());
		}
		aluno.setId(dto.getId());
		aluno.setBolsista(dto.isAlunoBolsista());
		aluno.setDataMatricula(new Date());
		aluno.setDataNascimento(dto.getDataNascimento());
		aluno.setDataUltimaAtualizacao(new Date());
		aluno.setEnviarEmailRegistro(dto.isEnviarEmail());
		aluno.setEnviarMensagem(dto.isEnviarMensagem());
		aluno.setMatricula(dto.getMatricula());
		aluno.setNome(dto.getNome());
		aluno.setUrlFoto(dto.getUrlFoto());
		aluno.setTurma(this.turmaService.fromAlunoCadastroDto(dto));
		aluno.setResponsavel(this.responsavelService.fromAlunoCadastroDto(dto, aluno.getResponsavel()));
		return aluno;
	}

	public AlunoCadastroDTO findById(Integer id) {
		Optional<Aluno> aluno = this.repository.findById(id);
		if (aluno.isEmpty()) {
			throw new ObjectNotFoundException("Aluno não encontrado");
		}
		return new AlunoCadastroDTO(aluno.get());
	}

	@Transactional
	public void disableById(Integer id) {
		Optional<Aluno> aluno = this.repository.findById(id);
		if (aluno.isEmpty()) {
			throw new ObjectNotFoundException("Aluno não encontrado");
		}
		aluno.get().setAtivo(false);
		this.repository.save(aluno.get());
	}
}

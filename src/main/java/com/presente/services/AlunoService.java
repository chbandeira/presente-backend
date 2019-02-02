package com.presente.services;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.presente.domains.Aluno;
import com.presente.dto.AlunoCadastroDTO;
import com.presente.dto.AlunoDTO;
import com.presente.exceptions.ObjectNotFoundException;
import com.presente.repositories.AlunoRepository;
import com.presente.services.utils.DateTime;
import com.presente.services.utils.URL;

/**
 * @author Charlles Bandeira
 *
 */
@Service
public class AlunoService {

	@Autowired
	private ResponsavelService responsavelService;
	@Autowired
	private TurmaService turmaService;
	@Autowired
	private LogAlteracaoAlunoService historicoAlteracaoService;

	@Autowired
	private AlunoRepository repository;

	@Transactional
	public Integer save(AlunoCadastroDTO dto) {
		Aluno aluno = null;
		if (dto.getId() != null) {
			Optional<Aluno> alunoFound = this.repository.findById(dto.getId());
			if (alunoFound.isPresent()) {
				aluno = alunoFound.get();
				if (!hasResponsavel(dto)) {
					long count = this.repository.countByResponsavel(aluno.getResponsavel());
					if (count == 1) {
						this.responsavelService.disable(aluno.getResponsavel());
					}
				}
			}
		}
		aluno = this.fromAlunoCadastroDTO(dto, aluno, hasResponsavel(dto));
		this.repository.save(aluno);
		this.historicoAlteracaoService.save(aluno, false);
		return aluno.getId();
	}

	private boolean hasResponsavel(AlunoCadastroDTO dto) {
		return dto.getNomeResponsavel() != null && !dto.getNomeResponsavel().isBlank();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
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
		matcher = matcher.withMatcher("ativo", exact());
		matcher = matcher.withIgnorePaths("bolsista", "enviarEmailRegistro", "enviarMensagem");

		Example<Aluno> example = Example.of(aluno, matcher);

		return this.repository.findAll(example, pageRequest).map(mat -> new AlunoDTO(mat));
	}

	public Aluno fromAlunoCadastroDTO(AlunoCadastroDTO dto, Aluno aluno, boolean hasResponsavel) {
		if (aluno == null) {
			aluno = new Aluno();
			aluno.setAnoLetivo(Year.now().getValue());
			aluno.setDataMatricula(DateTime.getDataAtual());
		}
		aluno.setId(dto.getId());
		aluno.setBolsista(dto.isAlunoBolsista());
		aluno.setDataNascimento(dto.getDataNascimento());
		aluno.setDataUltimaAtualizacao(DateTime.getDataAtual());
		aluno.setEnviarEmailRegistro(dto.isEnviarEmail());
		aluno.setEnviarMensagem(dto.isEnviarMensagem());
		aluno.setMatricula(dto.getMatricula());
		aluno.setNome(dto.getNome());
		aluno.setUrlFoto(dto.getUrlFoto());
		aluno.setTurma(this.turmaService.fromAlunoCadastroDto(dto));
		if (hasResponsavel) {			
			aluno.setResponsavel(this.responsavelService.fromAlunoCadastroDto(dto, aluno.getResponsavel()));
		} else {
			aluno.setResponsavel(null);
		}
		return aluno;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
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
		long count = this.repository.countByResponsavel(aluno.get().getResponsavel());
		if (count == 1) {
			aluno.get().getResponsavel().setAtivo(false);
		}
		this.repository.save(aluno.get());
		this.historicoAlteracaoService.save(aluno.get(), false);
	}
}

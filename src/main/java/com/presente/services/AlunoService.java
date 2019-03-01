package com.presente.services;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.time.Year;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	private ImageService imageService;
	@Autowired
	private S3Service s3Service;

	@Autowired
	private AlunoRepository repository;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	@Value("${img.profile.size}")
	private Integer size;
	@Value("${s3.urlFoto}")
	private String s3UrlFoto;

	@Transactional
	public AlunoCadastroDTO save(AlunoCadastroDTO dto, MultipartFile foto) {
		Aluno aluno = null;
		if (dto.getId() != null) {
			Optional<Aluno> alunoFound = this.repository.findById(dto.getId());
			if (alunoFound.isPresent()) {
				aluno = alunoFound.get();
			}
		}
		if (foto != null) {			
			dto.setUrlFoto(this.uploadProfilePicture(foto, dto.getMatricula()).toString());
		} else {
			dto.setUrlFoto(null);
		}
		aluno = this.fromAlunoCadastroDTO(dto, aluno);
		this.repository.save(aluno);
		this.historicoAlteracaoService.save(aluno, false);
		dto.setId(aluno.getId());
		return dto;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<AlunoDTO> search(String nome, String codMatricula, Integer anoLetivo, Integer page, Integer size,
			String direction) {

		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), "nome", "matricula", "anoLetivo");
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
		matcher = matcher.withIgnorePaths("bolsista", "enviarEmailRegistro", "enviarMensagem", "foto");

		Example<Aluno> example = Example.of(aluno, matcher);
		return this.repository.findAll(example, pageRequest).map(mat -> new AlunoDTO(mat));
	}

	public Aluno fromAlunoCadastroDTO(AlunoCadastroDTO dto, Aluno aluno) {
		if (aluno == null) {
			aluno = new Aluno();
			aluno.setAnoLetivo(Year.now().getValue());
			aluno.setDataMatricula(DateTime.getDataAtual());
			aluno.setId(dto.getId());
		}
		aluno.setBolsista(dto.isAlunoBolsista());
		aluno.setDataNascimento(dto.getDataNascimento());
		aluno.setDataUltimaAtualizacao(DateTime.getDataAtual());
		aluno.setMatricula(dto.getMatricula());
		aluno.setNome(dto.getNome());
		aluno.setTurma(this.turmaService.fromAlunoCadastroDto(dto));
		aluno.setFoto(dto.getUrlFoto() != null);
		if (this.hasNewResponsavel(dto)) {			
			aluno.setResponsavel(this.responsavelService.fromAlunoCadastroDto(dto, aluno.getResponsavel()));
		} else if (!this.hasResponsavel(dto)) {
			aluno.setResponsavel(null);
		} else if (this.hasOtherResponsavel(dto, aluno)) {
			aluno.setResponsavel(this.responsavelService.findById(dto.getIdResponsavel()).get());
		}
		return aluno;
	}

	private boolean hasNewResponsavel(AlunoCadastroDTO dto) {
		return dto.getIdResponsavel() == null && dto.getNomeResponsavel() != null && !dto.getNomeResponsavel().isBlank();
	}

	private boolean hasResponsavel(AlunoCadastroDTO dto) {
		return dto.getIdResponsavel() != null 
				|| (dto.getNomeResponsavel() != null && !dto.getNomeResponsavel().isBlank());
	}

	private boolean hasOtherResponsavel(AlunoCadastroDTO dto, Aluno aluno) {
		return 	(dto.getIdResponsavel() != null
					&& aluno.getResponsavel() != null 
					&& aluno.getResponsavel().getId() != null
					&& !dto.getIdResponsavel().equals(aluno.getResponsavel().getId())) 
				||
				(dto.getIdResponsavel() != null && aluno.getResponsavel() == null);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public AlunoCadastroDTO findById(Integer id) {
		Optional<Aluno> aluno = this.repository.findById(id);
		if (aluno.isEmpty()) {
			throw new ObjectNotFoundException("Aluno não encontrado");
		}
		AlunoCadastroDTO dto = new AlunoCadastroDTO(aluno.get());
		if (aluno.get().hasFoto()) {			
			dto.setUrlFoto(this.getUrlFoto(dto.getMatricula()));
		}
		return dto;
	}

	public String getUrlFoto(String matricula) {
		return this.s3UrlFoto + this.getFullnameImage(matricula);
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
	
	public URI uploadProfilePicture(MultipartFile multipartFile, String fileName) {
		BufferedImage jpgImage = this.imageService.getJpgImageFromFile(multipartFile);
		jpgImage = this.imageService.cropSquare(jpgImage);
		jpgImage = this.imageService.resize(jpgImage, size);
		String fullFileName = getFullnameImage(fileName);
		return this.s3Service.uploadFile(this.imageService.getInputStream(jpgImage, "jpg"), fullFileName, "image");
	}

	private String getFullnameImage(String fileName) {
		return this.prefix + fileName + ".jpg";
	}

	public boolean isAtivo(Integer idAluno) {
		return this.repository.findById(idAluno).get().isAtivo();
	}
}

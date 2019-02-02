package com.presente.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.presente.domains.Aluno;
import com.presente.domains.LogAlteracaoAluno;
import com.presente.domains.Registro;
import com.presente.domains.enums.TipoRegistro;
import com.presente.dto.RegistroDTO;
import com.presente.exceptions.StandardValidationException;
import com.presente.repositories.AlunoRepository;
import com.presente.repositories.RegistroRepository;
import com.presente.services.utils.DateTime;


/**
 * @author Charlles Bandeira
 *
 */
@Service
public class RegistroService {

	private static final Logger LOG = LoggerFactory.getLogger(RegistroService.class);

	@Autowired
	private RegistroRepository repository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private LogAlteracaoAlunoService logAlteracaoAlunoService;

	@Autowired
	private LogEnvioEmailService logEnvioEmailService;

	@Autowired
	private AlunoRepository alunoRepository;

	@Transactional
	public RegistroDTO registrar(RegistroDTO dto) {
		Optional<LogAlteracaoAluno> logAlteracaoAluno = this.logAlteracaoAlunoService.findByMatriculaAtiva(dto.getMatricula());
		if (logAlteracaoAluno.isPresent()) {
			Optional<Aluno> aluno = this.alunoRepository.findById(logAlteracaoAluno.get().getIdAluno());
			if (aluno.get().isAtivo()) {
				Registro registro = new Registro(
						logAlteracaoAluno.get(), 
						TipoRegistro.toEnum(dto.getTipoRegistro()), 
						DateTime.getDataAtual());
				this.repository.save(registro);
				this.sendEmail(logAlteracaoAluno.get(), registro);
				dto.setMessageRetorno(this.getMessageRetornoRegistro(logAlteracaoAluno.get()));
			} else {
				throw new StandardValidationException("Matrícula excluída anteriormente!");
			}
		} else {
			throw new StandardValidationException("Matrícula não encontrada!");
		}
		return dto;
	}

	private String getMessageRetornoRegistro(LogAlteracaoAluno logAlteracaoAluno) {
		StringBuilder sb = new StringBuilder();
		sb.append(logAlteracaoAluno.getNome()).append(", Matrícula ").append(logAlteracaoAluno.getMatricula());
		sb.append(" registrada com sucesso!");
		if (logAlteracaoAluno.getEnviarEmailRegistro()) {			
			sb.append(" Um email será enviado para o responsável.");
		}
		if (logAlteracaoAluno.getEnviarMensagem()) {			
			sb.append(" Uma mensagem será enviada para o responsável.");
		}
		return sb.toString();
	}

	private void sendEmail(LogAlteracaoAluno historico, Registro registro) {
		if (historico.getEnviarEmailRegistro()) {
			new Thread(() -> {
				try {
					this.emailService.sendOrderConfirmationEmail(registro);
				} catch (Exception e) {
					this.logEnvioEmailService.save(registro, e.getMessage());
					LOG.error(e.getMessage());
				} finally {
					this.repository.save(registro);
				}
			}).start();
		}
	}

}

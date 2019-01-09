package com.presente.backend.services;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.presente.backend.domains.Aluno;
import com.presente.backend.domains.LogAlteracaoAluno;
import com.presente.backend.domains.Registro;
import com.presente.backend.domains.enums.TipoRegistro;
import com.presente.backend.exceptions.StandardValidationException;
import com.presente.backend.repositories.AlunoRepository;
import com.presente.backend.repositories.RegistroRepository;

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

	public void registrar(String matricula, TipoRegistro tipo) {
		Optional<LogAlteracaoAluno> logAlteracaoAluno = this.logAlteracaoAlunoService.findByMatriculaAtiva(matricula);
		Optional<Aluno> aluno = this.alunoRepository.findById(logAlteracaoAluno.get().getIdAluno());
		if (aluno.get().isAtivo() && logAlteracaoAluno.isPresent()) {
			Registro registro = new Registro(logAlteracaoAluno.get(), tipo, new Date());
			this.repository.save(registro);
			this.sendEmail(logAlteracaoAluno.get(), registro);
		} else {
			throw new StandardValidationException("Matrícula não encontrada");
		}
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

package com.presente.services;

import java.time.Year;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.presente.domains.Aluno;
import com.presente.domains.LogAlteracaoAluno;
import com.presente.domains.enums.AlteracaoAluno;
import com.presente.repositories.LogAlteracaoAlunoRepository;
import com.presente.services.utils.DateTime;

/**
 * @author Charlles Bandeira
 *
 */
@Service
public class LogAlteracaoAlunoService {

	@Autowired
	private LogAlteracaoAlunoRepository repository;

	private AlteracaoAluno getTipo(boolean inclusao) {
		if (inclusao) {
			return AlteracaoAluno.INCLUSAO;
		} 
		return AlteracaoAluno.ALTERACAO;
	}

	@Transactional
	public void save(Aluno aluno, boolean inclusao) {
		LogAlteracaoAluno logAlteracaoAluno = new LogAlteracaoAluno();
		logAlteracaoAluno.setIdAluno(aluno.getId());
		logAlteracaoAluno.setAlteracaoAluno(this.getTipo(inclusao));
		logAlteracaoAluno.setAnoLetivo(aluno.getAnoLetivo());
		logAlteracaoAluno.setBolsista(aluno.isBolsista());
		logAlteracaoAluno.setDataMatricula(aluno.getDataMatricula());
		logAlteracaoAluno.setDataNascimento(aluno.getDataNascimento());
		logAlteracaoAluno.setDataUltimaAtualizacao(DateTime.getDataAtual());
		logAlteracaoAluno.setMatricula(aluno.getMatricula());
		logAlteracaoAluno.setNome(aluno.getNome());
		logAlteracaoAluno.setUrlFoto(aluno.getUrlFoto());
		logAlteracaoAluno.setEnviarEmailRegistro(aluno.isEnviarEmailRegistro());
		logAlteracaoAluno.setEnviarMensagem(aluno.isEnviarMensagem());
		logAlteracaoAluno.setAtivo(aluno.isAtivo());
		if (aluno.getResponsavel() != null) {
			logAlteracaoAluno.setCpfResponsavel(aluno.getResponsavel().getCpf());
			logAlteracaoAluno.setEmailResponsavel(aluno.getResponsavel().getEmail());
			logAlteracaoAluno.setEmailResponsavel2(aluno.getResponsavel().getEmail2());
			logAlteracaoAluno.setIdResponsavel(aluno.getResponsavel().getId());
			logAlteracaoAluno.setTelefoneCelularResponsavel(aluno.getResponsavel().getTelefoneCelular());
			logAlteracaoAluno.setTelefoneFixoResponsavel(aluno.getResponsavel().getTelefoneFixo());
			logAlteracaoAluno.setNomeResponsavel(aluno.getResponsavel().getNome());
		}
		if (aluno.getTurma() != null) {
			logAlteracaoAluno.setIdTurma(aluno.getTurma().getId());
			logAlteracaoAluno.setSala(aluno.getTurma().getSala());
			logAlteracaoAluno.setSerie(aluno.getTurma().getSerie());
			logAlteracaoAluno.setTurma(aluno.getTurma().getDescricao());
		}
		this.repository.save(logAlteracaoAluno);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Optional<LogAlteracaoAluno> findByMatriculaAtiva(String matricula) {
		return repository.findFirstByMatriculaAndAnoLetivoAndAtivoOrderByDataUltimaAtualizacaoDesc(
				matricula, Year.now().getValue(), true);
	}

}

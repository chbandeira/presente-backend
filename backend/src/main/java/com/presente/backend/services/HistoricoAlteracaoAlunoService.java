package com.presente.backend.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.presente.backend.domains.Aluno;
import com.presente.backend.domains.HistoricoAlteracaoAluno;
import com.presente.backend.domains.enums.AlteracaoAluno;
import com.presente.backend.repositories.HistoricoAlteracaoAlunoRepository;

/**
 * @author Charlles Bandeira
 *
 */
@Service
public class HistoricoAlteracaoAlunoService {

	@Autowired
	private HistoricoAlteracaoAlunoRepository repository;

	private AlteracaoAluno getTipo(boolean inclusao) {
		if (inclusao) {
			return AlteracaoAluno.INCLUSAO;
		} 
		return AlteracaoAluno.ALTERACAO;
	}

	public void save(Aluno aluno, boolean inclusao) {
		HistoricoAlteracaoAluno historico = new HistoricoAlteracaoAluno();
		historico.setId(aluno.getId());
		historico.setAlteracaoAluno(this.getTipo(inclusao));
		historico.setAnoLetivo(aluno.getAnoLetivo());
		historico.setBolsista(aluno.getBolsista());
		historico.setDataMatricula(aluno.getDataMatricula());
		historico.setDataNascimento(aluno.getDataNascimento());
		historico.setDataUltimaAtualizacao(new Date());
		historico.setMatricula(aluno.getMatricula());
		historico.setNome(aluno.getNome());
		historico.setUrlFoto(aluno.getUrlFoto());
		historico.setEnviarEmailRegistro(aluno.getEnviarEmailRegistro());
		historico.setEnviarMensagem(aluno.getEnviarMensagem());
		if (aluno.getResponsavel() != null) {
			historico.setCpfResponsavel(aluno.getResponsavel().getCpf());
			historico.setEmailResponsavel(aluno.getResponsavel().getEmail());
			historico.setEmailResponsavel2(aluno.getResponsavel().getEmail2());
			historico.setIdResponsavel(aluno.getResponsavel().getId());
			historico.setLoginResponsavel(aluno.getResponsavel().getLogin());
			historico.setTelefoneCelularResponsavel(aluno.getResponsavel().getTelefoneCelular());
			historico.setTelefoneFixoResponsavel(aluno.getResponsavel().getTelefoneFixo());
			historico.setNomeResponsavel(aluno.getResponsavel().getNome());
		}
		if (aluno.getTurma() != null) {
			historico.setIdTurma(aluno.getTurma().getId());
			historico.setSala(aluno.getTurma().getSala());
			historico.setSerie(aluno.getTurma().getSerie());
			historico.setTurma(aluno.getTurma().getDescricao());
		}
		this.repository.save(historico);
	}

}

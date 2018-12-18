package com.presente.backend.services;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.presente.backend.domains.Aluno;
import com.presente.backend.domains.HistoricoAlteracao;
import com.presente.backend.domains.Matricula;
import com.presente.backend.domains.Responsavel;
import com.presente.backend.domains.Sala;
import com.presente.backend.domains.Serie;
import com.presente.backend.domains.Turma;
import com.presente.backend.domains.enums.TipoHistoricoAlteracao;
import com.presente.backend.repositories.HistoricoAlteracaoRepository;



/**
 * @author Charlles Bandeira
 *
 */
@Service
public class HistoricoAlteracaoService {
	
	@Autowired
	private HistoricoAlteracaoRepository repository;

	private void save(HistoricoAlteracao historicoAlteracao) {
		historicoAlteracao.setUltimaAlteracao(new Date());
		historicoAlteracao.setDataUltimaAtualizacao(null);
		repository.save(historicoAlteracao);
	}
	
	private void setAluno(Aluno aluno, HistoricoAlteracao historicoAlteracao) {
		historicoAlteracao.setIdAluno(aluno.getId());
		historicoAlteracao.setAlunoAtivo(aluno.getAtivo());
		historicoAlteracao.setAlunoDataNascimento(aluno.getDataNascimento());
		historicoAlteracao.setAlunoNome(aluno.getNome());
		historicoAlteracao.setIdAluno(aluno.getId());
		historicoAlteracao.setDataUltimaAtualizacao(aluno.getDataUltimaAtualizacao());
	}

	private void setMatricula(Matricula matricula, HistoricoAlteracao historicoAlteracao) {
		historicoAlteracao.setIdMatricula(matricula.getId());
		historicoAlteracao.setMatricula(matricula.getMatricula());
		historicoAlteracao.setMatriculaAnoLetivo(matricula.getAnoLetivo());
		historicoAlteracao.setMatriculaAtivo(matricula.getAtivo());
		historicoAlteracao.setMatriculaData(matricula.getData());
		historicoAlteracao.setMatriculaEnviarEmailRegistro(matricula.getEnviarEmailRegistro());
		historicoAlteracao.setMatriculaTurno(matricula.getTurno());
	}

	private void setResponsavel(Responsavel responsavel, HistoricoAlteracao historicoAlteracao) {
		if (responsavel != null) {
			historicoAlteracao.setIdResponsavel(responsavel.getId());
			historicoAlteracao.setResponsavelEmail(responsavel.getEmail());
			historicoAlteracao.setResponsavelNome(responsavel.getNome());
			historicoAlteracao.setResponsavelTelefoneCelular(responsavel.getTelefoneCelular());
			historicoAlteracao.setResponsavelTelefoneFixo(responsavel.getTelefoneFixo());
		}
	}

	private void setSala(Sala sala, HistoricoAlteracao historicoAlteracao) {
		if (sala != null) {
			historicoAlteracao.setIdSala(sala.getId());
			historicoAlteracao.setSalaAtivo(sala.getAtivo());
			historicoAlteracao.setSalaDescricao(sala.getDescricao());
		}
	}
	
	private void setSerie(Serie serie, HistoricoAlteracao historicoAlteracao) {
		if (serie != null) {
			historicoAlteracao.setIdSerie(serie.getId());
			historicoAlteracao.setSerieAtivo(serie.getAtivo());
			historicoAlteracao.setSerieDescricao(serie.getDescricao());
		}
	}

	private void setTurma(Turma turma, HistoricoAlteracao historicoAlteracao) {
		if (turma != null) {
			historicoAlteracao.setIdTurma(turma.getId());
			historicoAlteracao.setTurmaAtivo(turma.getAtivo());
			historicoAlteracao.setTurmaDescricao(turma.getDescricao());
		}
	}
	
	public void saveFromMatricula(Matricula matricula, boolean inclusao) {
		HistoricoAlteracao historicoAlteracao = new HistoricoAlteracao();
		historicoAlteracao.setEntidadeOrigem(matricula.getClass().getName());
		historicoAlteracao.setTipoHistoricoAlteracao(this.getTipo(inclusao));
		this.setMatricula(matricula, historicoAlteracao);
		this.setAluno(matricula.getAluno(), historicoAlteracao);
		this.setResponsavel(matricula.getResponsavel(), historicoAlteracao);
		this.setSala(matricula.getSala(), historicoAlteracao);
		this.setSerie(matricula.getSerie(), historicoAlteracao);
		this.setTurma(matricula.getTurma(), historicoAlteracao);
		this.save(historicoAlteracao);
	}

	public void saveFromAluno(Aluno aluno, boolean inclusao) {
		HistoricoAlteracao historicoAlteracao = new HistoricoAlteracao();
		historicoAlteracao.setEntidadeOrigem(aluno.getClass().getName());
		historicoAlteracao.setTipoHistoricoAlteracao(this.getTipo(inclusao));
		this.setAluno(aluno, historicoAlteracao);
		this.save(historicoAlteracao);
	}

	public void saveFromSala(Sala sala, boolean inclusao) {
		HistoricoAlteracao historicoAlteracao = new HistoricoAlteracao();
		historicoAlteracao.setEntidadeOrigem(sala.getClass().getName());
		historicoAlteracao.setTipoHistoricoAlteracao(this.getTipo(inclusao));
		this.setSala(sala, historicoAlteracao);
		this.save(historicoAlteracao);
	}
	
	public void saveFromSerie(Serie serie, boolean inclusao) {
		HistoricoAlteracao historicoAlteracao = new HistoricoAlteracao();
		historicoAlteracao.setEntidadeOrigem(serie.getClass().getName());
		historicoAlteracao.setTipoHistoricoAlteracao(this.getTipo(inclusao));
		this.setSerie(serie, historicoAlteracao);
		this.save(historicoAlteracao);
	}
	
	public void saveFromTurma(Turma turma, boolean inclusao) {
		HistoricoAlteracao historicoAlteracao = new HistoricoAlteracao();
		historicoAlteracao.setEntidadeOrigem(turma.getClass().getName());
		historicoAlteracao.setTipoHistoricoAlteracao(this.getTipo(inclusao));
		this.setTurma(turma, historicoAlteracao);
		this.save(historicoAlteracao);
	}

	private TipoHistoricoAlteracao getTipo(boolean inclusao) {
		TipoHistoricoAlteracao tipo;
		if (inclusao) {
			tipo = TipoHistoricoAlteracao.INCLUSAO;
		} else {
			tipo = TipoHistoricoAlteracao.ALTERACAO;
		}
		return tipo;
	}

}

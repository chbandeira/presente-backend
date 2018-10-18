package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.chbandeira.utilitario.Util;
import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.dao.HistoricoAlteracaoDAO;
import br.com.w2c.model.domain.Aluno;
import br.com.w2c.model.domain.HistoricoAlteracao;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Responsavel;
import br.com.w2c.model.domain.Sala;
import br.com.w2c.model.domain.Serie;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.model.enums.TipoHistoricoAlteracao;

/**
 * 
 * @author Charlles
 * @since 09/02/2014
 */
@SuppressWarnings("rawtypes")
@Component
public class HistoricoAlteracaoBO extends BaseBO {

	@Autowired
	private HistoricoAlteracaoDAO historicoAlteracaoDAO;
	
	private void salvar(HistoricoAlteracao historicoAlteracao) {
		if (historicoAlteracao.getEntidadeOrigem() != null
				&& !historicoAlteracao.getEntidadeOrigem().trim().equals("")) {
			
			historicoAlteracao.setUltimaAlteracao(UtilDate.getDataAtual());
			historicoAlteracao.setDataUltimaAtualizacao(null);
			historicoAlteracaoDAO.salvar(historicoAlteracao);
		}
	}
	
	private void extrairAluno(Aluno aluno, HistoricoAlteracao historicoAlteracao) {
		if (!isNuloOuVazio(aluno)) {
			historicoAlteracao.setIdAluno(aluno.getId());
			historicoAlteracao.setAlunoAtivo(aluno.getAtivo());
			historicoAlteracao.setAlunoDataNascimento(aluno.getDataNascimento());
			historicoAlteracao.setAlunoNome(aluno.getNome());
			historicoAlteracao.setIdAluno(aluno.getId());
			historicoAlteracao.setDataUltimaAtualizacao(aluno.getDataUltimaAtualizacao());
		}
	}

	private void extrairMatricula(Matricula matricula, HistoricoAlteracao historicoAlteracao) {
		if (!isNuloOuVazio(matricula)) {
			historicoAlteracao.setIdMatricula(matricula.getId());
			historicoAlteracao.setMatricula(matricula.getMatricula());
			historicoAlteracao.setMatriculaAnoLetivo(matricula.getAnoLetivo());
			historicoAlteracao.setMatriculaAtivo(matricula.getAtivo());
			historicoAlteracao.setMatriculaData(matricula.getData());
			historicoAlteracao.setMatriculaEnviarEmailRegistro(matricula.getEnviarEmailRegistro());
			historicoAlteracao.setMatriculaTurno(matricula.getTurno());
		}
	}

	private void extrairResponsavel(Responsavel responsavel, HistoricoAlteracao historicoAlteracao) {
		if (!isNuloOuVazio(responsavel)) {
			historicoAlteracao.setIdResponsavel(responsavel.getId());
			historicoAlteracao.setResponsavelEmail(responsavel.getEmail());
			historicoAlteracao.setResponsavelNome(responsavel.getNome());
			historicoAlteracao.setResponsavelTelefoneCelular(responsavel.getTelefoneCelular());
			historicoAlteracao.setResponsavelTelefoneFixo(responsavel.getTelefoneFixo());
		}
	}

	private void extrairSala(Sala sala, HistoricoAlteracao historicoAlteracao) {
		if (!isNuloOuVazio(sala)) {
			historicoAlteracao.setIdSala(sala.getId());
			historicoAlteracao.setSalaAtivo(sala.getAtivo());
			historicoAlteracao.setSalaDescricao(sala.getDescricao());
		}
	}
	
	private void extrairSerie(Serie serie, HistoricoAlteracao historicoAlteracao) {
		if (!isNuloOuVazio(serie)) {
			historicoAlteracao.setIdSerie(serie.getId());
			historicoAlteracao.setSerieAtivo(serie.getAtivo());
			historicoAlteracao.setSerieDescricao(serie.getDescricao());
		}
	}

	private void extrairTurma(Turma turma, HistoricoAlteracao historicoAlteracao) {
		if (!isNuloOuVazio(turma)) {
			historicoAlteracao.setIdTurma(turma.getId());
			historicoAlteracao.setTurmaAtivo(turma.getAtivo());
			historicoAlteracao.setTurmaDescricao(turma.getDescricao());
		}
	}

	public void salvar(Aluno aluno, TipoHistoricoAlteracao tipoHistoricoAlteracao) {
		HistoricoAlteracao historicoAlteracao = new HistoricoAlteracao();
		if (!isNuloOuVazio(aluno)) {
			historicoAlteracao.setEntidadeOrigem(aluno.getClass().getName());
			extrairAluno(aluno, historicoAlteracao);
			historicoAlteracao.setTipoHistoricoAlteracao(tipoHistoricoAlteracao);
			salvar(historicoAlteracao);
		}
	}

	public void salvar(Matricula matricula, TipoHistoricoAlteracao tipoHistoricoAlteracao) {
		HistoricoAlteracao historicoAlteracao = new HistoricoAlteracao();
		if (!isNuloOuVazio(matricula)) {
			historicoAlteracao.setEntidadeOrigem(matricula.getClass().getName());
			extrairMatricula(matricula, historicoAlteracao);
			extrairAluno(matricula.getAluno(), historicoAlteracao);
			extrairResponsavel(matricula.getResponsavel(), historicoAlteracao);
			extrairSala(matricula.getSala(), historicoAlteracao);
			extrairSerie(matricula.getSerie(), historicoAlteracao);
			extrairTurma(matricula.getTurma(), historicoAlteracao);
			historicoAlteracao.setTipoHistoricoAlteracao(tipoHistoricoAlteracao);
			salvar(historicoAlteracao);
		}
	}
	
	public void salvar(Sala sala, TipoHistoricoAlteracao tipoHistoricoAlteracao) {
		HistoricoAlteracao historicoAlteracao = new HistoricoAlteracao();
		if (!isNuloOuVazio(sala)) {
			historicoAlteracao.setEntidadeOrigem(sala.getClass().getName());
			extrairSala(sala, historicoAlteracao);
			historicoAlteracao.setTipoHistoricoAlteracao(tipoHistoricoAlteracao);
			salvar(historicoAlteracao);
		}
	}
	
	public void salvar(Serie serie, TipoHistoricoAlteracao tipoHistoricoAlteracao) {
		HistoricoAlteracao historicoAlteracao = new HistoricoAlteracao();
		if (!isNuloOuVazio(serie)) {
			historicoAlteracao.setEntidadeOrigem(serie.getClass().getName());
			extrairSerie(serie, historicoAlteracao);
			historicoAlteracao.setTipoHistoricoAlteracao(tipoHistoricoAlteracao);
			salvar(historicoAlteracao);
		}
	}
	
	public void salvar(Turma turma, TipoHistoricoAlteracao tipoHistoricoAlteracao) {
		HistoricoAlteracao historicoAlteracao = new HistoricoAlteracao();
		if (!isNuloOuVazio(turma)) {
			historicoAlteracao.setEntidadeOrigem(turma.getClass().getName());
			extrairTurma(turma, historicoAlteracao);
			historicoAlteracao.setTipoHistoricoAlteracao(tipoHistoricoAlteracao);
			salvar(historicoAlteracao);
		}
	}

	public TipoHistoricoAlteracao getTipo(Long id) {
		TipoHistoricoAlteracao tipo;
		if (Util.isNulo(id)) {
			tipo = TipoHistoricoAlteracao.INCLUSAO;
		} else {
			tipo = TipoHistoricoAlteracao.ALTERACAO;
		}
		return tipo;
	}

	public List<HistoricoAlteracao> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return historicoAlteracaoDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, HistoricoAlteracao.class);
	}
	
}

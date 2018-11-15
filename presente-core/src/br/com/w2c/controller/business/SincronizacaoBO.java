package br.com.w2c.controller.business;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.chbandeira.utilitario.gson.GsonUtil;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.domain.Aluno;
import br.com.w2c.model.domain.AlunoDisciplina;
import br.com.w2c.model.domain.CalendarioEscolar;
import br.com.w2c.model.domain.ConfiguracaoEscola;
import br.com.w2c.model.domain.Disciplina;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Ocorrencia;
import br.com.w2c.model.domain.ParametroGeral;
import br.com.w2c.model.domain.Perfil;
import br.com.w2c.model.domain.Registro;
import br.com.w2c.model.domain.Relatorio;
import br.com.w2c.model.domain.Responsavel;
import br.com.w2c.model.domain.Sala;
import br.com.w2c.model.domain.Serie;
import br.com.w2c.model.domain.TipoOcorrencia;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.model.enums.StatusAtualizacaoWeb;
import br.com.w2c.model.wrapper.ConfirmacaoSincronizacaoWrapper;
import br.com.w2c.model.wrapper.SincronizacaoWrapper;
import br.com.w2c.util.SpringUtil;

/**
 * 
 * @author Charlles
 * @since 31/01/2016
 */
@Component
@SuppressWarnings("rawtypes")
public class SincronizacaoBO extends BaseBO {

	private AlunoBO alunoBO;
	private AlunoDisciplinaBO alunoDisciplinaBO;
	private CalendarioEscolarBO calendarioEscolarBO;
	private ConfiguracaoEscolaBO configuracaoEscolaBO;
	private DisciplinaBO disciplinaBO;
//	private HistoricoAlteracaoBO historicoAlteracaoBO;
	private MatriculaBO matriculaBO;
	private OcorrenciaBO ocorrenciaBO;
//	private OcorrenciaImportacaoBO ocorrenciaImportacaoBO;
	private ParametroGeralBO parametroGeralBO;
	private PerfilBO perfilBO;
	private RegistroBO registroBO;
	private RelatorioBO relatorioBO;
	private ResponsavelBO responsavelBO;
	private SalaBO salaBO;
	private SerieBO serieBO;
	private TipoOcorrenciaBO tipoOcorrenciaBO;
	private TurmaBO turmaBO;
	private UsuarioBO usuarioBO;
	
	public void initInstances() {
		alunoBO = SpringUtil.getBean("alunoBO");
		alunoDisciplinaBO = SpringUtil.getBean("alunoDisciplinaBO");
		calendarioEscolarBO = SpringUtil.getBean("calendarioEscolarBO");
		configuracaoEscolaBO = SpringUtil.getBean("configuracaoEscolaBO");
		disciplinaBO = SpringUtil.getBean("disciplinaBO");
//		historicoAlteracaoBO = SpringUtil.getBean("historicoAlteracaoBO");
		matriculaBO = SpringUtil.getBean("matriculaBO");
		ocorrenciaBO = SpringUtil.getBean("ocorrenciaBO");
//		ocorrenciaImportacaoBO = SpringUtil.getBean("ocorrenciaImportacaoBO");
		parametroGeralBO = SpringUtil.getBean("parametroGeralBO");
		perfilBO = SpringUtil.getBean("perfilBO");
		registroBO = SpringUtil.getBean("registroBO");
		relatorioBO = SpringUtil.getBean("relatorioBO");
		responsavelBO = SpringUtil.getBean("responsavelBO");
		salaBO = SpringUtil.getBean("salaBO");
		serieBO = SpringUtil.getBean("serieBO");
		tipoOcorrenciaBO = SpringUtil.getBean("tipoOcorrenciaBO");
		turmaBO = SpringUtil.getBean("turmaBO");
		usuarioBO = SpringUtil.getBean("usuarioBO");
	}
	
	
	public String obterDadosParaSincronizacao(ConfirmacaoSincronizacaoWrapper confirmacaoSincronizacao) 
			throws AplicacaoException {
		
		initInstances();
		
		SincronizacaoWrapper wrapper = new SincronizacaoWrapper();
		String json;
		try {
			
			Date dataHoraUltimaAtualizacao = obterDataHoraUltimaAtualizacao(confirmacaoSincronizacao);
			wrapper.setConfirmacaoSincronizacao(confirmacaoSincronizacao);
			wrapper.setListaAluno(obterUltimosAlunosAtualizadosSemFoto(dataHoraUltimaAtualizacao));
			wrapper.setListaAlunoDisciplina(alunoDisciplinaBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			wrapper.setListaCalendarioEscolar(calendarioEscolarBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			wrapper.setListaConfiguracaoEscola(configuracaoEscolaBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			wrapper.setListaDisciplina(disciplinaBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			//TODO não é necessário online
//			wrapper.setListaHistoricoAlteracao(historicoAlteracaoBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			wrapper.setListaMatricula(matriculaBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			wrapper.setListaOcorrencia(ocorrenciaBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			//TODO não é necessário online
//			wrapper.setListaOcorrenciaImportacao(ocorrenciaImportacaoBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			wrapper.setListaParametroGeral(parametroGeralBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			wrapper.setListaPerfil(perfilBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			wrapper.setListaRegistro(registroBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			wrapper.setListaRelatorio(relatorioBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			wrapper.setListaResponsavel(responsavelBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			wrapper.setListaSala(salaBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			wrapper.setListaSerie(serieBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			wrapper.setListaTipoOcorrencia(tipoOcorrenciaBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			wrapper.setListaTurma(turmaBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			wrapper.setListaUsuario(usuarioBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao));
			
			json = GsonUtil.serializar(wrapper);
		} catch (Exception e) {
			throw new AplicacaoException(e);
		}
		
		return json;
	}

	private List<Aluno> obterUltimosAlunosAtualizadosSemFoto(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		List<Aluno> alunos = alunoBO.obterUltimosAtualizados(dataHoraUltimaAtualizacao);
		List<Aluno> alunosRetornar = new ArrayList<Aluno>();
		for (Aluno aluno : alunos) {
			aluno.setFoto(null);
			alunosRetornar.add(aluno);
		}
		return alunosRetornar;
	}

	public void iniciarSincronizacao(ConfirmacaoSincronizacaoWrapper confirmacaoSincronizacao) throws AplicacaoException, ParseException {
		
		log.info(">>Iniciando sincronizacao de dados com WEB...");
		
		ParametroGeralBO parametroGeralBO = SpringUtil.getBean("parametroGeralBO");
		
		Date dataHoraNovaAtualizacao = obterDataHoraNovaAtualizacao(confirmacaoSincronizacao);
		
		ParametroGeral parametroStatus = parametroGeralBO.obterPorChaveIdentificador(
						br.com.w2c.model.enums.ParametroGeral.STATUS_ATUALIZACAO_WEB.getChave(), 
						confirmacaoSincronizacao.getIdentificador());
		parametroStatus.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
		parametroStatus.setValor(StatusAtualizacaoWeb.ATUALIZANDO.getValor());
		parametroGeralBO.salvar(parametroStatus);
	}

	public void finalizarSincronizacao(ConfirmacaoSincronizacaoWrapper confirmacaoSincronizacao, String retorno) throws ParseException, AplicacaoException {
		
		log.info(">>Finalizando sincronizacao de dados com WEB.");
		
		SincronizacaoWrapper wrapper = GsonUtil.deserializar(retorno, SincronizacaoWrapper.class);
		ConfirmacaoSincronizacaoWrapper confirmacaoSincronizacaoRetorno = wrapper.getConfirmacaoSincronizacao();
		
		if (confirmacaoSincronizacaoRetorno.isAtualizacaoSucesso()
				&& confirmacaoSincronizacaoRetorno.getIdentificador().equals(confirmacaoSincronizacao.getIdentificador())) {
			
			atualizarParametrosSincronizacao(confirmacaoSincronizacaoRetorno);
		}
	}

	private void atualizarParametrosSincronizacao(ConfirmacaoSincronizacaoWrapper confirmacaoSincronizacao)
			throws ParseException, AplicacaoException {
		
		ParametroGeralBO parametroGeralBO = SpringUtil.getBean("parametroGeralBO");
		
		Date dataHoraNovaAtualizacao = obterDataHoraNovaAtualizacao(confirmacaoSincronizacao);
		
		ParametroGeral parametroUltimaAtualizacaoWEB = parametroGeralBO.obterPorChaveIdentificador(
						br.com.w2c.model.enums.ParametroGeral.ULTIMA_ATUALIZACAO_WEB.getChave(), 
						confirmacaoSincronizacao.getIdentificador());
		parametroUltimaAtualizacaoWEB.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
		parametroUltimaAtualizacaoWEB.setValor(confirmacaoSincronizacao.getDataHoraNovaAtualizacao());
		parametroGeralBO.salvar(parametroUltimaAtualizacaoWEB);

		ParametroGeral parametroStatus = parametroGeralBO.obterPorChaveIdentificador(
				br.com.w2c.model.enums.ParametroGeral.STATUS_ATUALIZACAO_WEB.getChave(), 
				confirmacaoSincronizacao.getIdentificador());
		parametroStatus.setValor(StatusAtualizacaoWeb.ATUALIZADO.getValor());
		parametroStatus.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
		parametroGeralBO.salvar(parametroStatus);
	}


	private Date obterDataHoraNovaAtualizacao(
			ConfirmacaoSincronizacaoWrapper confirmacaoSincronizacao)
			throws ParseException {
		Date dataHoraNovaAtualizacao = UtilDate.getStringToDate(confirmacaoSincronizacao.getDataHoraNovaAtualizacao(), UtilDate.DD_MM_YYYY_HH_MM_SS);
		return dataHoraNovaAtualizacao;
	}

	public void erroSincronizacao(ConfirmacaoSincronizacaoWrapper confirmacaoSincronizacao) {

		ParametroGeralBO parametroGeralBO = SpringUtil.getBean("parametroGeralBO");
		
		try {
			ParametroGeral parametroStatus = parametroGeralBO.obterPorChaveIdentificador(
					br.com.w2c.model.enums.ParametroGeral.STATUS_ATUALIZACAO_WEB.getChave(), 
					confirmacaoSincronizacao.getIdentificador());
			
			parametroStatus.setValor(StatusAtualizacaoWeb.ERRO.getValor());
			
			parametroGeralBO.salvar(parametroStatus);
		} catch (AplicacaoException e) {
			log.error(e);
		}
	}

	@Transactional
	public String sincronizarWeb(String pacote) {
		String retorno = "erro";
		ConfirmacaoSincronizacaoWrapper confirmacaoSincronizacao = null;
		try {
			SincronizacaoWrapper wrapper = GsonUtil.deserializar(pacote, SincronizacaoWrapper.class);
			confirmacaoSincronizacao = wrapper.getConfirmacaoSincronizacao();
			
			iniciarSincronizacao(confirmacaoSincronizacao);
			
			salvarDadosOnline(wrapper);
			
			atualizarParametrosSincronizacao(confirmacaoSincronizacao);
			
			wrapper.getConfirmacaoSincronizacao().setAtualizacaoSucesso(true);
			
			retorno = GsonUtil.serializar(wrapper);
		} catch (Exception e) {
			log.error(e);
			if (confirmacaoSincronizacao != null) {
				erroSincronizacao(confirmacaoSincronizacao);
			}
		}
		return retorno;
	}

	private void salvarDadosOnline(SincronizacaoWrapper wrapper) throws ParseException, NegocioException, AplicacaoException {
		
		initInstances();
		
		Date dataHoraNovaAtualizacao = obterDataHoraNovaAtualizacao(wrapper);
		
		salvarListaAlunoOnline(wrapper, dataHoraNovaAtualizacao);
		salvarListaCalendarioEscolarOnline(wrapper, dataHoraNovaAtualizacao);
		salvarListaConfiguracaoEscolaOnline(wrapper, dataHoraNovaAtualizacao);
		salvarListaDisciplinaOnline(wrapper, dataHoraNovaAtualizacao);
		salvarListaSalaOnline(wrapper, dataHoraNovaAtualizacao);
		salvarListaSerieOnline(wrapper, dataHoraNovaAtualizacao);
		salvarListaTurmaOnline(wrapper, dataHoraNovaAtualizacao);
		salvarListaPerfilOnline(wrapper, dataHoraNovaAtualizacao);
		salvarListaUsuarioOnline(wrapper, dataHoraNovaAtualizacao);
		//TODO ver sobre perfil do usuário
		salvarListaResponsavelOnline(wrapper, dataHoraNovaAtualizacao);
		salvarListaMatriculaOnline(wrapper, dataHoraNovaAtualizacao);
		salvarListaTipoOcorrenciaOnline(wrapper, dataHoraNovaAtualizacao);
		salvarListaOcorrenciaOnline(wrapper, dataHoraNovaAtualizacao);
		salvarListaParametroGeralOnline(wrapper, dataHoraNovaAtualizacao);
		salvarListaRegistroOnline(wrapper, dataHoraNovaAtualizacao);
		salvarListaRelatorioOnline(wrapper, dataHoraNovaAtualizacao);
		salvarListaAlunoDisciplinaOnline(wrapper, dataHoraNovaAtualizacao);
		
		//TODO não é necessário online
//		for (HistoricoAlteracao historico : wrapper.getListaHistoricoAlteracao()) {
//			historico.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
//			historicoAlteracaoBO.salvarOnline(historico);
//		}
		//TODO não é necessário online
//		wrapper.setListaOcorrenciaImportacao(ocorrenciaImportacaoDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, OcorrenciaImportacao.class));
	}


	private void salvarListaAlunoDisciplinaOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (AlunoDisciplina alunoDisciplina : wrapper.getListaAlunoDisciplina()) {
			alunoDisciplina.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			alunoDisciplinaBO.salvarOnline(alunoDisciplina);
		}
	}


	private void salvarListaRelatorioOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (Relatorio relatorio : wrapper.getListaRelatorio()) {
			relatorio.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			relatorioBO.salvarOnline(relatorio);
		}
	}


	private void salvarListaRegistroOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (Registro registro : wrapper.getListaRegistro()) {
			registro.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			registroBO.salvarOnline(registro);
		}
	}


	private void salvarListaParametroGeralOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (ParametroGeral parametroGeral : wrapper.getListaParametroGeral()) {
			parametroGeral.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			parametroGeralBO.salvarOnline(parametroGeral);
		}
	}


	private void salvarListaOcorrenciaOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (Ocorrencia ocorrencia : wrapper.getListaOcorrencia()) {
			ocorrencia.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			ocorrenciaBO.salvarOnline(ocorrencia);
		}
	}


	private void salvarListaTipoOcorrenciaOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (TipoOcorrencia tipoOcorrencia : wrapper.getListaTipoOcorrencia()) {
			tipoOcorrencia.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			tipoOcorrenciaBO.salvarOnline(tipoOcorrencia);
		}
	}


	private void salvarListaMatriculaOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (Matricula matricula : wrapper.getListaMatricula()) {
			matricula.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			matriculaBO.salvarOnline(matricula);
		}
	}


	private void salvarListaResponsavelOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (Responsavel responsavel : wrapper.getListaResponsavel()) {
			responsavel.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			responsavelBO.salvarOnline(responsavel);
		}
	}


	private void salvarListaUsuarioOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (Usuario usuario : wrapper.getListaUsuario()) {
			usuario.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			usuarioBO.salvarOnline(usuario);
		}
	}


	private void salvarListaPerfilOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (Perfil perfil : wrapper.getListaPerfil()) {
			perfil.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			perfilBO.salvarOnline(perfil);
		}
	}


	private void salvarListaTurmaOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (Turma turma : wrapper.getListaTurma()) {
			turma.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			turmaBO.salvarOnline(turma);
		}
	}


	private void salvarListaSerieOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (Serie serie : wrapper.getListaSerie()) {
			serie.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			serieBO.salvarOnline(serie);
		}
	}


	private void salvarListaSalaOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (Sala sala : wrapper.getListaSala()) {
			sala.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			salaBO.salvarOnline(sala);
		}
	}


	private void salvarListaDisciplinaOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (Disciplina disciplina : wrapper.getListaDisciplina()) {
			disciplina.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			disciplinaBO.salvarOnline(disciplina);
		}
	}


	private void salvarListaConfiguracaoEscolaOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (ConfiguracaoEscola configuracao : wrapper.getListaConfiguracaoEscola()) {
			configuracao.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			configuracaoEscolaBO.salvarOnline(configuracao);
		}
	}


	private void salvarListaCalendarioEscolarOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (CalendarioEscolar calendario : wrapper.getListaCalendarioEscolar()) {
			calendario.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			calendarioEscolarBO.salvarOnline(calendario);
		}
	}


	private void salvarListaAlunoOnline(SincronizacaoWrapper wrapper,
			Date dataHoraNovaAtualizacao) throws AplicacaoException,
			NegocioException {
		for (Aluno aluno : wrapper.getListaAluno()) {
			aluno.setDataUltimaAtualizacao(dataHoraNovaAtualizacao);
			alunoBO.salvarOnline(aluno);
		}
	}


	private Date obterDataHoraNovaAtualizacao(SincronizacaoWrapper wrapper)throws ParseException {
		return UtilDate.getStringToDate(wrapper.getConfirmacaoSincronizacao().getDataHoraNovaAtualizacao(), UtilDate.DD_MM_YYYY_HH_MM_SS);
	}

	private Date obterDataHoraUltimaAtualizacao(ConfirmacaoSincronizacaoWrapper confirmacaoSincronizacao) throws ParseException {
		return UtilDate.getStringToDate(confirmacaoSincronizacao.getDataHoraUltimaAtualizacao(), UtilDate.DD_MM_YYYY_HH_MM_SS);
	}
}

package br.com.w2c.model.wrapper;

import java.util.List;

import br.com.w2c.model.domain.Aluno;
import br.com.w2c.model.domain.AlunoDisciplina;
import br.com.w2c.model.domain.CalendarioEscolar;
import br.com.w2c.model.domain.ConfiguracaoEscola;
import br.com.w2c.model.domain.Disciplina;
import br.com.w2c.model.domain.HistoricoAlteracao;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Ocorrencia;
import br.com.w2c.model.domain.OcorrenciaImportacao;
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

/**
 * 
 * @author Charlles
 * @since 31/01/2016
 */
public class SincronizacaoWrapper {

	private ConfirmacaoSincronizacaoWrapper confirmacaoSincronizacao;
	private List<Aluno> listaAluno;               
	private List<AlunoDisciplina> listaAlunoDisciplina;     
	private List<CalendarioEscolar> listaCalendarioEscolar;   
	private List<ConfiguracaoEscola> listaConfiguracaoEscola;  
	private List<Disciplina> listaDisciplina;          
	private List<HistoricoAlteracao> listaHistoricoAlteracao;  
	private List<Matricula> listaMatricula;           
	private List<Ocorrencia> listaOcorrencia;          
	private List<OcorrenciaImportacao> listaOcorrenciaImportacao;
	private List<ParametroGeral> listaParametroGeral;      
	private List<Perfil> listaPerfil;              
	private List<Registro> listaRegistro;            
	private List<Relatorio> listaRelatorio;           
	private List<Responsavel> listaResponsavel;         
	private List<Sala> listaSala;                
	private List<Serie> listaSerie;               
	private List<TipoOcorrencia> listaTipoOcorrencia;      
	private List<Turma> listaTurma;               
	private List<Usuario> listaUsuario;
	
	public List<Aluno> getListaAluno() {
		return listaAluno;
	}
	public void setListaAluno(List<Aluno> listaAluno) {
		this.listaAluno = listaAluno;
	}
	public List<AlunoDisciplina> getListaAlunoDisciplina() {
		return listaAlunoDisciplina;
	}
	public void setListaAlunoDisciplina(List<AlunoDisciplina> listaAlunoDisciplina) {
		this.listaAlunoDisciplina = listaAlunoDisciplina;
	}
	public List<CalendarioEscolar> getListaCalendarioEscolar() {
		return listaCalendarioEscolar;
	}
	public void setListaCalendarioEscolar(
			List<CalendarioEscolar> listaCalendarioEscolar) {
		this.listaCalendarioEscolar = listaCalendarioEscolar;
	}
	public List<ConfiguracaoEscola> getListaConfiguracaoEscola() {
		return listaConfiguracaoEscola;
	}
	public void setListaConfiguracaoEscola(
			List<ConfiguracaoEscola> listaConfiguracaoEscola) {
		this.listaConfiguracaoEscola = listaConfiguracaoEscola;
	}
	public List<Disciplina> getListaDisciplina() {
		return listaDisciplina;
	}
	public void setListaDisciplina(List<Disciplina> listaDisciplina) {
		this.listaDisciplina = listaDisciplina;
	}
	public List<HistoricoAlteracao> getListaHistoricoAlteracao() {
		return listaHistoricoAlteracao;
	}
	public void setListaHistoricoAlteracao(
			List<HistoricoAlteracao> listaHistoricoAlteracao) {
		this.listaHistoricoAlteracao = listaHistoricoAlteracao;
	}
	public List<Matricula> getListaMatricula() {
		return listaMatricula;
	}
	public void setListaMatricula(List<Matricula> listaMatricula) {
		this.listaMatricula = listaMatricula;
	}
	public List<Ocorrencia> getListaOcorrencia() {
		return listaOcorrencia;
	}
	public void setListaOcorrencia(List<Ocorrencia> listaOcorrencia) {
		this.listaOcorrencia = listaOcorrencia;
	}
	public List<OcorrenciaImportacao> getListaOcorrenciaImportacao() {
		return listaOcorrenciaImportacao;
	}
	public void setListaOcorrenciaImportacao(
			List<OcorrenciaImportacao> listaOcorrenciaImportacao) {
		this.listaOcorrenciaImportacao = listaOcorrenciaImportacao;
	}
	public List<ParametroGeral> getListaParametroGeral() {
		return listaParametroGeral;
	}
	public void setListaParametroGeral(List<ParametroGeral> listaParametroGeral) {
		this.listaParametroGeral = listaParametroGeral;
	}
	public List<Perfil> getListaPerfil() {
		return listaPerfil;
	}
	public void setListaPerfil(List<Perfil> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}
	public List<Registro> getListaRegistro() {
		return listaRegistro;
	}
	public void setListaRegistro(List<Registro> listaRegistro) {
		this.listaRegistro = listaRegistro;
	}
	public List<Relatorio> getListaRelatorio() {
		return listaRelatorio;
	}
	public void setListaRelatorio(List<Relatorio> listaRelatorio) {
		this.listaRelatorio = listaRelatorio;
	}
	public List<Responsavel> getListaResponsavel() {
		return listaResponsavel;
	}
	public void setListaResponsavel(List<Responsavel> listaResponsavel) {
		this.listaResponsavel = listaResponsavel;
	}
	public List<Sala> getListaSala() {
		return listaSala;
	}
	public void setListaSala(List<Sala> listaSala) {
		this.listaSala = listaSala;
	}
	public List<Serie> getListaSerie() {
		return listaSerie;
	}
	public void setListaSerie(List<Serie> listaSerie) {
		this.listaSerie = listaSerie;
	}
	public List<TipoOcorrencia> getListaTipoOcorrencia() {
		return listaTipoOcorrencia;
	}
	public void setListaTipoOcorrencia(List<TipoOcorrencia> listaTipoOcorrencia) {
		this.listaTipoOcorrencia = listaTipoOcorrencia;
	}
	public List<Turma> getListaTurma() {
		return listaTurma;
	}
	public void setListaTurma(List<Turma> listaTurma) {
		this.listaTurma = listaTurma;
	}
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	public ConfirmacaoSincronizacaoWrapper getConfirmacaoSincronizacao() {
		return confirmacaoSincronizacao;
	}
	public void setConfirmacaoSincronizacao(ConfirmacaoSincronizacaoWrapper confirmacaoSincronizacao) {
		this.confirmacaoSincronizacao = confirmacaoSincronizacao;
	}            
	
}

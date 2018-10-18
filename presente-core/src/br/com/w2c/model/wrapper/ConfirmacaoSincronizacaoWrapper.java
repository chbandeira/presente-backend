package br.com.w2c.model.wrapper;


/**
 * 
 * @author Charlles
 * @since 31/01/2016
 */
public class ConfirmacaoSincronizacaoWrapper {

	private Long identificador;
	private boolean atualizacaoSucesso;
	private String dataHoraUltimaAtualizacao;
	private String dataHoraNovaAtualizacao;
	
	public Long getIdentificador() {
		return identificador;
	}
	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}
	public boolean isAtualizacaoSucesso() {
		return atualizacaoSucesso;
	}
	public void setAtualizacaoSucesso(boolean atualizacaoSucesso) {
		this.atualizacaoSucesso = atualizacaoSucesso;
	}
	public String getDataHoraUltimaAtualizacao() {
		return dataHoraUltimaAtualizacao;
	}
	public void setDataHoraUltimaAtualizacao(String dataHoraUltimaAtualizacao) {
		this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
	}
	public String getDataHoraNovaAtualizacao() {
		return dataHoraNovaAtualizacao;
	}
	public void setDataHoraNovaAtualizacao(String dataHoraNovaAtualizacao) {
		this.dataHoraNovaAtualizacao = dataHoraNovaAtualizacao;
	}
	
}

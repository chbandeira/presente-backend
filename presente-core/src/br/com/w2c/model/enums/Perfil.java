package br.com.w2c.model.enums;

/**
 * 
 * @author Charlles
 * @since 2016/05/14
 */
public enum Perfil {

	ADMINISTRADOR("admin","Administrador"),
	COMUM("comum","Usuário Comum"),
	COORDENADOR("coord","Coordenador"),
	DIRETOR("diret","Diretor"),
	RESPONSAVEL("respo","Responsável");

	private String perfil;
	private String descricao;

	private Perfil(String perfil, String descricao) {
		this.perfil = perfil;
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getPerfil() {
		return perfil;
	}
}

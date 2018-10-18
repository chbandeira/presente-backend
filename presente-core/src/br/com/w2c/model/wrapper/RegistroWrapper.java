package br.com.w2c.model.wrapper;

/**
 * 
 * @author Charlles
 * @since 31/12/2013
 */
public class RegistroWrapper {

	private String dataHoraRegistro;
	private String nomeAluno;
	private String matricula;
	private String serie;
	private String turma;
	private byte[] foto;
	
	public String getDataHoraRegistro() {
		return dataHoraRegistro;
	}
	public void setDataHoraRegistro(String dataHoraRegistro) {
		this.dataHoraRegistro = dataHoraRegistro;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	@Override
	public String toString() {
		return "RegistroWrapper [dataHoraRegistro=" + dataHoraRegistro
				+ ", nomeAluno=" + nomeAluno + ", matricula=" + matricula
				+ ", serie=" + serie + ", turma=" + turma + "]";
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
}

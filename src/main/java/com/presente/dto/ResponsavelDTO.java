package com.presente.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.presente.domains.Responsavel;
import com.presente.domains.Telefone;

/**
 * @author Charlles Bandeira
 *
 */
public class ResponsavelDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String nome;
	
	private String cpf;

	private String email;
	
	private String email2;
	
	private Set<TelefoneDTO> telefones = new HashSet<>();
	
	private boolean enviarEmail;
	
	private boolean enviarMensagem;
	
	public ResponsavelDTO() {
	}

	public ResponsavelDTO(Responsavel resp) {
		this.id = resp.getId();
		this.nome = resp.getNome();
		this.enviarEmail = resp.isEnviarEmailRegistro();
		this.enviarMensagem = resp.isEnviarMensagem();
		this.cpf = resp.getCpf();
		this.email = resp.getEmail();
		this.email2 = resp.getEmail2();
		for (Telefone tel : resp.getTelefones()) {				
			this.telefones.add(new TelefoneDTO(tel));
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnviarEmail() {
		return enviarEmail;
	}

	public void setEnviarEmail(boolean enviarEmail) {
		this.enviarEmail = enviarEmail;
	}

	public boolean isEnviarMensagem() {
		return enviarMensagem;
	}

	public void setEnviarMensagem(boolean enviarMensagem) {
		this.enviarMensagem = enviarMensagem;
	}

	public Integer getId() {
		return this.id;
	}

	public void setIdMatricula(Integer id) {
		this.id = id;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public Set<TelefoneDTO> getTelefones() {
		return telefones;
	}

	public void addTelefone(Telefone telefone) {
		this.telefones.add(new TelefoneDTO(telefone));
	}

}

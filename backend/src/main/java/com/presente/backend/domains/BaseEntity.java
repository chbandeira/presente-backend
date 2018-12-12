package com.presente.backend.domains;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Identidicador da instituicao/escola
	 */
	@Column(nullable=false, updatable=false, insertable=false, columnDefinition="bigint DEFAULT 1234")
	private Long identificador;
	
	/**
	 * Id da tabela local
	 */
	@Column(name="id_local", updatable=false, insertable=true)
	private Long idLocal;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_ultima_atualizacao", columnDefinition="timestamp DEFAULT now()", insertable=false, updatable=true, nullable=false)
	private Date dataUltimaAtualizacao;

	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	public Long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(Long idLocal) {
		this.idLocal = idLocal;
	}

}

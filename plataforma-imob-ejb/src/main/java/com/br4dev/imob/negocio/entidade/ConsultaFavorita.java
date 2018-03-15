package com.br4dev.imob.negocio.entidade;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Daniel.Matos
 */
@Entity
public class ConsultaFavorita implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String requisicaoJson;

	@ManyToOne
	private Pessoa pessoa;

	@Enumerated(EnumType.ORDINAL)
	private TipoConsulta tipoConsulta;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoConsulta getTipoConsulta() {
		return this.tipoConsulta;
	}

	public void setTipoConsulta(TipoConsulta tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public String getRequisicaoJson() {
		return requisicaoJson;
	}

	public void setRequisicaoJson(String requisicaoJson) {
		this.requisicaoJson = requisicaoJson;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}

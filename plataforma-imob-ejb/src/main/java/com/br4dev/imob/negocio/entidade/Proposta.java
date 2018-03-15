package com.br4dev.imob.negocio.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Daniel.Matos
 */
@Entity
public class Proposta implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Imovel imovel;

	@ManyToOne
	private Pessoa pessoa;

	@Enumerated(EnumType.ORDINAL)
	private StatusProposta statusProposta;

	private float valorProposta;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataProposta;

	private String descricaoProposta;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public StatusProposta getStatusProposta() {
		return this.statusProposta;
	}

	public void setStatusProposta(StatusProposta statusProposta) {
		this.statusProposta = statusProposta;
	}

	public float getValorProposta() {
		return valorProposta;
	}

	public void setValorProposta(float valorProposta) {
		this.valorProposta = valorProposta;
	}

	public Date getDataProposta() {
		return dataProposta;
	}

	public void setDataProposta(Date dataProposta) {
		this.dataProposta = dataProposta;
	}

	public String getDescricaoProposta() {
		return descricaoProposta;
	}

	public void setDescricaoProposta(String descricaoProposta) {
		this.descricaoProposta = descricaoProposta;
	}

}

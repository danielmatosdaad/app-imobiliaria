package com.br4dev.imob.negocio.entidade;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 * @author Daniel.Matos
 */
@Entity
public class Transacao implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private float valor;

	@Lob
	private String xml;

	@Enumerated
	private TipoTransacao tipoTransacao;

	@Enumerated(EnumType.ORDINAL)
	private StatusTransacao statusTransacao;

	@ManyToOne
	private DebitoContrato debitosContrato;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DebitoContrato getDebitosContrato() {
		return debitosContrato;
	}

	public void setDebitosContrato(DebitoContrato debitosContrato) {
		this.debitosContrato = debitosContrato;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public StatusTransacao getStatusTransacao() {
		return statusTransacao;
	}

	public void setStatusTransacao(StatusTransacao statusTransacao) {
		this.statusTransacao = statusTransacao;
	}

}

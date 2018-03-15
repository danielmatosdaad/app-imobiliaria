package com.br4dev.imob.negocio.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

/**
 * @author Daniel.Matos
 */
@Entity
public class Contrato implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long idClienteContratante;

	private Long idClienteContratada;

	private Date dataInclusao;

	private Date dataValidade;

	@Lob
	private String conteudoxml;

	@OneToMany(fetch = FetchType.EAGER)
	private List<AnexoContrato> anexoContratos;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "contrato")
	private List<DebitoContrato> debitoContrato;

	@Enumerated(EnumType.ORDINAL)
	private TipoContrato tipoContrato;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoContrato getTipoContrato() {
		return this.tipoContrato;
	}

	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public List<AnexoContrato> getAnexoContratos() {
		return anexoContratos;
	}

	public void setAnexoContratos(List<AnexoContrato> anexoContratos) {
		this.anexoContratos = anexoContratos;
	}

	public Long getIdClienteContratante() {
		return idClienteContratante;
	}

	public void setIdClienteContratante(Long idClienteContratante) {
		this.idClienteContratante = idClienteContratante;
	}

	public Long getIdClienteContratada() {
		return idClienteContratada;
	}

	public void setIdClienteContratada(Long idClienteContratada) {
		this.idClienteContratada = idClienteContratada;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public List<DebitoContrato> getDebitoContrato() {
		return debitoContrato;
	}

	public void setDebitoContrato(List<DebitoContrato> debitoContrato) {
		this.debitoContrato = debitoContrato;
	}

	public String getConteudoxml() {
		return conteudoxml;
	}

	public void setConteudoxml(String conteudoxml) {
		this.conteudoxml = conteudoxml;
	}

}

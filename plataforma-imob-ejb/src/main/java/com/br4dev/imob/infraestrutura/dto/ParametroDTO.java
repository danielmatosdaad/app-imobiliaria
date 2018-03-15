package com.br4dev.imob.infraestrutura.dto;

import com.br4dev.imob.negocio.dto.DTO;

public class ParametroDTO implements DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private String nome;

	private String valor;

	private String descricao;

	private TipoParametroDTO tipoParametro;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoParametroDTO getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(TipoParametroDTO tipoParametro) {
		this.tipoParametro = tipoParametro;
	}
	

}

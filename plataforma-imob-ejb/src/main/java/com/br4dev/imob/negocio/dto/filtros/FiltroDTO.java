package com.br4dev.imob.negocio.dto.filtros;

import java.util.Map;

import com.br4dev.imob.infraestrutura.dto.EnvioDTO;
import com.br4dev.imob.negocio.dto.DTO;

public class FiltroDTO extends EnvioDTO implements DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, String> campoValores;

	private TipoOperacaoDTO tipoOperacao;

	public FiltroDTO() {

	}

	public FiltroDTO(Map<String, String> campoValores, TipoOperacaoDTO tipoOperacao) {

		this.campoValores = campoValores;
		this.tipoOperacao = tipoOperacao;
	}

	public Map<String, String> getCampoValores() {
		return campoValores;
	}

	public TipoOperacaoDTO getTipoOperacao() {
		return tipoOperacao;
	}

}

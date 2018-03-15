package com.br4dev.imob.negocio.dto.filtros;

import java.util.EnumSet;
import java.util.Iterator;

public enum TipoOperacaoDTO {

	IGUAL("igual"), DIFERENTE("diferente"), INICIA_COM("iniciacom"), TERMINA_COM("terminacom"), CONTEM("contem"), ENTRE(
			"entre");

	private TipoOperacaoDTO(String value) {

		this.value = value;
	}

	private String value;

	public static TipoOperacaoDTO getOperacao(String operacao) {

		if (operacao == null) {
			return IGUAL;
		}
		EnumSet<TipoOperacaoDTO> enums = EnumSet.allOf(TipoOperacaoDTO.class);

		Iterator<TipoOperacaoDTO> iteratorEnum = enums.iterator();
		while (iteratorEnum.hasNext()) {
			TipoOperacaoDTO tipoOperacaoDTO = iteratorEnum.next();
			if (tipoOperacaoDTO.getValue().equals(operacao)) {

				return tipoOperacaoDTO;
			}
		}
		return IGUAL;
	}

	public String getValue() {
		return value;
	}

}

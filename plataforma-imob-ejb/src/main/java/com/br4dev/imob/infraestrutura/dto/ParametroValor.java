package com.br4dev.imob.infraestrutura.dto;

public enum ParametroValor {

	GEOLOCALIZACAO_PROXY_ATIVO(1L),
	ENDERECO_BASE_IMAGEM(2L),
	ENDERECO_BASE_AUDIO(3L),
	ENDERECO_BASE_VIDEO(4L),
	ENDERECO_BASE_DOCUMENTO(5L),
	LOCAL_GRAVACAO_IMAGEM_IMOVEL(6L), 
	LOCAL_GRAVACAO_IMAGEM_PESSOA(7L);
	
	
	private ParametroValor(long value) {
		this.value = value;
	}

	private long value;

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
}

package com.br4dev.imob.infraestrutura.execao;

public enum Mensagem {

	SUCESSO(1), ERRO_NEGOCIO(0), ERRO_INFRA(-1);

	int value;

	private Mensagem(int codigo) {

		this.value = codigo;

	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}

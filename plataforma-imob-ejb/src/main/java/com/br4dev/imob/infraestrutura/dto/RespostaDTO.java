package com.br4dev.imob.infraestrutura.dto;

import com.br4dev.imob.infraestrutura.execao.Mensagem;
import com.br4dev.imob.negocio.dto.DTO;

public class RespostaDTO implements DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Mensagem mensagem;
	private String respostaMensagem;

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public String getRespostaMensagem() {
		return respostaMensagem;
	}

	public void setRespostaMensagem(String respostaMensagem) {
		this.respostaMensagem = respostaMensagem;
	}

}

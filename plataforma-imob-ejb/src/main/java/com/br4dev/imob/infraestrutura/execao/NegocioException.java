package com.br4dev.imob.infraestrutura.execao;

public class NegocioException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Mensagem mensagem;

	public NegocioException(Mensagem mensagem, Exception e) {
		super(e);
		this.mensagem = mensagem;
	}
	
	
	public NegocioException(Mensagem mensagem) {
		super();
		this.mensagem = mensagem;
		
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

}

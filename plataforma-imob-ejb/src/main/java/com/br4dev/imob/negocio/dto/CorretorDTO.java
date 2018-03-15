package com.br4dev.imob.negocio.dto;

import com.br4dev.imob.infraestrutura.dto.EnvioDTO;

public class CorretorDTO extends EnvioDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String creci;
	private String foto;
	private String nome;
	private String situacao;
	private String telefones;
	private String email;

	public String getCreci() {
		return creci;
	}

	public void setCreci(String creci) {
		this.creci = creci;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTelefones() {
		return telefones;
	}

	public void setTelefones(String telefones) {
		this.telefones = telefones;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {

		return "[creci: " + creci + "]" + "[foto: " + foto + "]" + "[nome: " + nome + "]" + "[situacao: " + situacao + "]"
				+ "[telefones: " + telefones + "]" + "[email: " + email + "]";

	}

}

package com.br4dev.imob.negocio.dto;

import java.util.Date;
import java.util.List;

import com.br4dev.imob.infraestrutura.dto.EnvioDTO;

public class PessoaDTO extends EnvioDTO implements DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private TipoPessoaDTO tipoPessoa;

	private GeneroDTO genero;

	private EnderecoDTO endereco;

	private List<ImovelDTO> imoveis;

	private String nome;
	private String cpf;
	private String email;
	private String telefone;

	private Date datanascimento;

	public PessoaDTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<ImovelDTO> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<ImovelDTO> imoveis) {
		this.imoveis = imoveis;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	public GeneroDTO getGenero() {
		return genero;
	}

	public void setGenero(GeneroDTO genero) {
		this.genero = genero;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

	public TipoPessoaDTO getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoaDTO tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

}

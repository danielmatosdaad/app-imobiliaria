package com.br4dev.imob.negocio.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Pessoa implements Entidade, Serializable {

	public List<Agendamento> getAgendametos() {
		return agendametos;
	}

	public void setAgendametos(List<Agendamento> agendametos) {
		this.agendametos = agendametos;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated(EnumType.ORDINAL)
	private TipoPessoa tipoPessoa;

	@Enumerated(EnumType.ORDINAL)
	private Genero genero;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Endereco endereco;

	@ManyToOne(fetch = FetchType.EAGER)
	private Plano plano;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Imovel> imoveis;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<Proposta> propostas;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<ImovelFavorito> imoveisFavoritos;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<ConsultaFavorita> consultasFavoritas;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<Agendamento> agendametos;

	@OneToMany(mappedBy = "emissor", fetch = FetchType.LAZY)
	private List<Problema> problemas;

	private String nome;
	private String cpf;
	private String email;
	private String telefone;

	@Temporal(TemporalType.DATE)
	private Date datanascimento;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) {
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

	public List<Proposta> getPropostas() {
		return propostas;
	}

	public void setPropostas(List<Proposta> propostas) {
		this.propostas = propostas;
	}

	public List<ImovelFavorito> getImoveisFavoritos() {
		return imoveisFavoritos;
	}

	public void setImoveisFavoritos(List<ImovelFavorito> imoveisFavoritos) {
		this.imoveisFavoritos = imoveisFavoritos;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public List<ConsultaFavorita> getConsultasFavoritas() {
		return consultasFavoritas;
	}

	public void setConsultasFavoritas(List<ConsultaFavorita> consultasFavoritas) {
		this.consultasFavoritas = consultasFavoritas;
	}

}

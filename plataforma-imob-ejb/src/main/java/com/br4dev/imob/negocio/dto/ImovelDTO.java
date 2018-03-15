package com.br4dev.imob.negocio.dto;

import java.util.List;

import com.br4dev.imob.infraestrutura.dto.EnvioDTO;

public class ImovelDTO extends EnvioDTO implements DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private PessoaDTO pessoa;

	private TipoImovelDTO tipoImovel;

	private TipoComercialDTO tipoComercial;

	private TipoResidencialDTO tipoResidencial;

	private FormaPagamentoDTO formaPagamento;

	private List<ImagemImovelDTO> imagens;

	private String cidade;
	private String uf;
	private String bairro;
	private String logradouro;
	private String complemento;
	private String cep;
	private double valorimovel;
	private double valorAluguel;
	private double valorcondominio;
	private double valoriptu;
	private double valorcaucao;
	private int peridominimolocacao;
	private float metragemimovel;
	private String longitude;
	private boolean latitude;
	private boolean exigecaucao;
	private boolean exigefiador;
	private boolean exigesegurofiancalocaticia;
	private boolean exigesegudoincendio;
	private String descricaoimovel;
	private boolean disponivellocacao;
	private boolean condominiofechado;
	private boolean contemchuraqueira;
	private boolean contempiscina;
	private boolean contemacademia;
	private boolean contemsalaofestas;
	private boolean contemsauda;
	private boolean contembriquedoteca;
	private boolean contemarealazer;
	private boolean contemareaesporte;
	private boolean contemdependenciaempregada;
	private boolean contemvagas;
	private boolean contemvagascobertas;
	private boolean contemelevador;
	private int numeroandar;
	private String nomecondominio;
	private String nomepredio;
	private String blocopredio;
	private String nascenteint;
	private int quantidadequartos;
	private int quantidadesuites;
	private int quantidadevagas;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PessoaDTO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaDTO pessoa) {
		this.pessoa = pessoa;
	}

	public TipoImovelDTO getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(TipoImovelDTO tipoImovel) {
		this.tipoImovel = tipoImovel;
	}

	public FormaPagamentoDTO getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamentoDTO formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public double getValorimovel() {
		return valorimovel;
	}

	public void setValorimovel(double valorimovel) {
		this.valorimovel = valorimovel;
	}

	public double getValorAluguel() {
		return valorAluguel;
	}

	public void setValorAluguel(double valoralugel) {
		this.valorAluguel = valoralugel;
	}

	public double getValorcondominio() {
		return valorcondominio;
	}

	public void setValorcondominio(double valorcondominio) {
		this.valorcondominio = valorcondominio;
	}

	public double getValoriptu() {
		return valoriptu;
	}

	public void setValoriptu(double valoriptu) {
		this.valoriptu = valoriptu;
	}

	public double getValorcaucao() {
		return valorcaucao;
	}

	public void setValorcaucao(double valorcaucao) {
		this.valorcaucao = valorcaucao;
	}

	public int getPeridominimolocacao() {
		return peridominimolocacao;
	}

	public void setPeridominimolocacao(int peridominimolocacao) {
		this.peridominimolocacao = peridominimolocacao;
	}

	public float getMetragemimovel() {
		return metragemimovel;
	}

	public void setMetragemimovel(float metragemimovel) {
		this.metragemimovel = metragemimovel;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public boolean isLatitude() {
		return latitude;
	}

	public void setLatitude(boolean latitude) {
		this.latitude = latitude;
	}

	public boolean isExigecaucao() {
		return exigecaucao;
	}

	public void setExigecaucao(boolean exigecaucao) {
		this.exigecaucao = exigecaucao;
	}

	public boolean isExigefiador() {
		return exigefiador;
	}

	public void setExigefiador(boolean exigefiador) {
		this.exigefiador = exigefiador;
	}

	public boolean isExigesegurofiancalocaticia() {
		return exigesegurofiancalocaticia;
	}

	public void setExigesegurofiancalocaticia(boolean exigesegurofiancalocaticia) {
		this.exigesegurofiancalocaticia = exigesegurofiancalocaticia;
	}

	public boolean isExigesegudoincendio() {
		return exigesegudoincendio;
	}

	public void setExigesegudoincendio(boolean exigesegudoincendio) {
		this.exigesegudoincendio = exigesegudoincendio;
	}

	public String getDescricaoimovel() {
		return descricaoimovel;
	}

	public void setDescricaoimovel(String descricaoimovel) {
		this.descricaoimovel = descricaoimovel;
	}

	public boolean isDisponivellocacao() {
		return disponivellocacao;
	}

	public void setDisponivellocacao(boolean disponivellocacao) {
		this.disponivellocacao = disponivellocacao;
	}

	public boolean isCondominiofechado() {
		return condominiofechado;
	}

	public void setCondominiofechado(boolean condominiofechado) {
		this.condominiofechado = condominiofechado;
	}

	public boolean isContemchuraqueira() {
		return contemchuraqueira;
	}

	public void setContemchuraqueira(boolean contemchuraqueira) {
		this.contemchuraqueira = contemchuraqueira;
	}

	public boolean isContempiscina() {
		return contempiscina;
	}

	public void setContempiscina(boolean contempiscina) {
		this.contempiscina = contempiscina;
	}

	public boolean isContemacademia() {
		return contemacademia;
	}

	public void setContemacademia(boolean contemacademia) {
		this.contemacademia = contemacademia;
	}

	public boolean isContemsalaofestas() {
		return contemsalaofestas;
	}

	public void setContemsalaofestas(boolean contemsalaofestas) {
		this.contemsalaofestas = contemsalaofestas;
	}

	public boolean isContemsauda() {
		return contemsauda;
	}

	public void setContemsauda(boolean contemsauda) {
		this.contemsauda = contemsauda;
	}

	public boolean isContembriquedoteca() {
		return contembriquedoteca;
	}

	public void setContembriquedoteca(boolean contembriquedoteca) {
		this.contembriquedoteca = contembriquedoteca;
	}

	public boolean isContemarealazer() {
		return contemarealazer;
	}

	public void setContemarealazer(boolean contemarealazer) {
		this.contemarealazer = contemarealazer;
	}

	public boolean isContemareaesporte() {
		return contemareaesporte;
	}

	public void setContemareaesporte(boolean contemareaesporte) {
		this.contemareaesporte = contemareaesporte;
	}

	public boolean isContemdependenciaempregada() {
		return contemdependenciaempregada;
	}

	public void setContemdependenciaempregada(boolean contemdependenciaempregada) {
		this.contemdependenciaempregada = contemdependenciaempregada;
	}

	public boolean isContemvagas() {
		return contemvagas;
	}

	public void setContemvagas(boolean contemvagas) {
		this.contemvagas = contemvagas;
	}

	public boolean isContemvagascobertas() {
		return contemvagascobertas;
	}

	public void setContemvagascobertas(boolean contemvagascobertas) {
		this.contemvagascobertas = contemvagascobertas;
	}

	public boolean isContemelevador() {
		return contemelevador;
	}

	public void setContemelevador(boolean contemelevador) {
		this.contemelevador = contemelevador;
	}

	public int getNumeroandar() {
		return numeroandar;
	}

	public void setNumeroandar(int numeroandar) {
		this.numeroandar = numeroandar;
	}

	public String getNomecondominio() {
		return nomecondominio;
	}

	public void setNomecondominio(String nomecondominio) {
		this.nomecondominio = nomecondominio;
	}

	public String getNomepredio() {
		return nomepredio;
	}

	public void setNomepredio(String nomepredio) {
		this.nomepredio = nomepredio;
	}

	public String getBlocopredio() {
		return blocopredio;
	}

	public void setBlocopredio(String blocopredio) {
		this.blocopredio = blocopredio;
	}

	public String getNascenteint() {
		return nascenteint;
	}

	public void setNascenteint(String nascenteint) {
		this.nascenteint = nascenteint;
	}

	public int getQuantidadequartos() {
		return quantidadequartos;
	}

	public void setQuantidadequartos(int quantidadequartos) {
		this.quantidadequartos = quantidadequartos;
	}

	public int getQuantidadesuites() {
		return quantidadesuites;
	}

	public void setQuantidadesuites(int quantidadesuites) {
		this.quantidadesuites = quantidadesuites;
	}

	public int getQuantidadevagas() {
		return quantidadevagas;
	}

	public void setQuantidadevagas(int quantidadevagas) {
		this.quantidadevagas = quantidadevagas;
	}

	public TipoComercialDTO getTipoComercial() {
		return tipoComercial;
	}

	public void setTipoComercial(TipoComercialDTO tipoComercial) {
		this.tipoComercial = tipoComercial;
	}

	public TipoResidencialDTO getTipoResidencial() {
		return tipoResidencial;
	}

	public void setTipoResidencial(TipoResidencialDTO tipoResidencial) {
		this.tipoResidencial = tipoResidencial;
	}

	public List<ImagemImovelDTO> getImagens() {
		return imagens;
	}

	public void setImagens(List<ImagemImovelDTO> imagens) {
		this.imagens = imagens;
	}

}

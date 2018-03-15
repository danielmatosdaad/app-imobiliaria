package com.br4dev.imob.infraestrutura.dto;

import java.util.Date;

import com.br4dev.imob.negocio.dto.DTO;

public class MonitorDTO extends EnvioDTO implements DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private String servico;

	private String metodo;

	private String dados;

	private boolean sucesso;

	private String pilhaErro;

	private String mensagem;

	private long tempoMilissegundos;

	private Date dataHora;

	public MonitorDTO(EnvioDTO envioDTO) {

		if (envioDTO != null) {
			super.ip = envioDTO.getIp();
			super.porta = envioDTO.getPorta();
			super.pais = envioDTO.getPais();
			super.isoCodePais = envioDTO.getIsoCodePais();
			super.nomeEstado = envioDTO.getNomeEstado();
			super.isoCodeEstado = envioDTO.getIsoCodeEstado();
			super.nomeCidade = envioDTO.getNomeCidade();
			super.codigoPostalCidade = envioDTO.getCodigoPostalCidade();
			super.latitude = envioDTO.getLatitude();
			super.longitude = envioDTO.getLongitude();
			super.browser = envioDTO.getBrowser();
			super.sistemaOperacional = envioDTO.getSistemaOperacional();
			super.categoriaDispositivo = envioDTO.getCategoriaDispositivo();
			super.agenteUsuario = envioDTO.getAgenteUsuario();
			super.dispositivo = envioDTO.getDispositivo();
			super.timeZone = envioDTO.getTimeZone();
		}

	}

	public MonitorDTO() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public long getTempoMilissegundos() {
		return tempoMilissegundos;
	}

	public void setTempoMilissegundos(long tempoMilissegundos) {
		this.tempoMilissegundos = tempoMilissegundos;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getPilhaErro() {
		return pilhaErro;
	}

	public void setPilhaErro(String pilhaErro) {
		this.pilhaErro = pilhaErro;
	}

}

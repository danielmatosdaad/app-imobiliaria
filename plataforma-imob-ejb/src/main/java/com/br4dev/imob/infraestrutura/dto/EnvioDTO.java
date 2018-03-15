package com.br4dev.imob.infraestrutura.dto;

import java.util.Date;

import com.br4dev.imob.negocio.dto.DTO;

public class EnvioDTO implements DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String ip;
	protected String porta;
	protected String pais;
	protected String isoCodePais;
	protected String nomeEstado;
	protected String isoCodeEstado;
	protected String nomeCidade;
	protected String codigoPostalCidade;
	protected String latitude;
	protected String longitude;
	protected String browser;
	protected String sistemaOperacional;
	protected String categoriaDispositivo;
	protected String agenteUsuario;
	protected String dispositivo;
	protected String timeZone;

	protected Date dataHora = new Date();

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getIsoCodePais() {
		return isoCodePais;
	}

	public void setIsoCodePais(String isoCodePais) {
		this.isoCodePais = isoCodePais;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	public String getIsoCodeEstado() {
		return isoCodeEstado;
	}

	public void setIsoCodeEstado(String isoCodeEstado) {
		this.isoCodeEstado = isoCodeEstado;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public String getCodigoPostalCidade() {
		return codigoPostalCidade;
	}

	public void setCodigoPostalCidade(String codigoPostalCidade) {
		this.codigoPostalCidade = codigoPostalCidade;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(String sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public String getCategoriaDispositivo() {
		return categoriaDispositivo;
	}

	public void setCategoriaDispositivo(String categoriaDispositivo) {
		this.categoriaDispositivo = categoriaDispositivo;
	}

	public String getAgenteUsuario() {
		return agenteUsuario;
	}

	public void setAgenteUsuario(String agenteUsuario) {
		this.agenteUsuario = agenteUsuario;
	}

}

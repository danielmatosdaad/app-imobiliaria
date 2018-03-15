package com.br4dev.imob.infraestrutura.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import com.br4dev.imob.negocio.entidade.Entidade;

@Entity
@XmlRootElement
public class Monitor implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String servico;

	private String metodo;

	private String dados;

	private boolean sucesso;

	private String pilhaErro;

	private String mensagem;

	private long tempoMilissegundos;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHora;

	private String ip;

	private String porta;

	private String pais;

	private String isoCodePais;

	private String nomeEstado;

	private String isoCodeEstado;

	private String nomeCidade;

	private String codigoPostalCidade;

	private String latitude;

	private String longitude;

	private String browser;

	private String dispositivo;

	private String timeZone;

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

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

}

package com.br4dev.imob.negocio.dto;

import java.io.InputStream;
import java.util.Date;

import com.br4dev.imob.infraestrutura.dto.EnvioDTO;

public class ImagemImovelDTO extends EnvioDTO implements DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private long idImovel;

	private String nomeArquivo;

	private String extensao;

	private String url;

	private int tamanho;

	private Date datahora;
	
	private String imagemBase64;

	private transient InputStream imagemStream;

	private TipoImagemNegocialDTO tipoImagemNegocial;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {

		this.nomeArquivo = nomeArquivo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public Date getDatahora() {
		return datahora;
	}

	public void setDatahora(Date datahora) {
		this.datahora = datahora;
	}

	public TipoImagemNegocialDTO getTipoImagemNegocial() {
		return tipoImagemNegocial;
	}

	public void setTipoImagemNegocial(TipoImagemNegocialDTO tipoImagemNegocial) {
		this.tipoImagemNegocial = tipoImagemNegocial;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public long getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(long idImovel) {
		this.idImovel = idImovel;
	}


	public InputStream getImagemStream() {
		return imagemStream;
	}

	public void setImagemStream(InputStream imagemStream) {
		this.imagemStream = imagemStream;
	}

	public String getImagemBase64() {
		return imagemBase64;
	}

	public void setImagemBase64(String imagemBase64) {
		this.imagemBase64 = imagemBase64;
	}

}

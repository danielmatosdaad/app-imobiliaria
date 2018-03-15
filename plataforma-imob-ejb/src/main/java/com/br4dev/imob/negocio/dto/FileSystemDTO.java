package com.br4dev.imob.negocio.dto;

import java.io.InputStream;
import java.nio.file.Path;

public class FileSystemDTO implements DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PastaDTO pastaRaiz;
	private Path enderecoArquivo;
	private String nomeArquivo;
	private String formato;
	private InputStream is;

	public FileSystemDTO(PastaDTO pastaRaiz, Path enderecoArquivo, String nomeArquivo, String formato, InputStream is) {
		this.pastaRaiz = pastaRaiz;
		this.enderecoArquivo = enderecoArquivo;
		this.nomeArquivo = nomeArquivo;
		this.formato = formato;
		this.is = is;

	}

	public FileSystemDTO(FileSystemDTO fileSystem, InputStream is) {
		this.pastaRaiz = fileSystem.pastaRaiz;
		this.enderecoArquivo = fileSystem.enderecoArquivo;
		this.nomeArquivo = fileSystem.nomeArquivo;
		this.formato = fileSystem.formato;
		this.is = is;

	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public String getFormato() {
		return formato;
	}

	public InputStream getIs() {
		return is;
	}

	public PastaDTO getPastaRaiz() {
		return pastaRaiz;
	}

	public Path getEnderecoArquivo() {
		return enderecoArquivo;
	}

	public String getEnderecoArquivoRelativo() {

		return replaceAllString(enderecoArquivo.toString(), this.pastaRaiz.getUrlBase(), "");
	}

	private String replaceAllString(String strOrig, String strFind, String strReplace) {
		if (strOrig == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer(strOrig);
		String toReplace = "";

		if (strReplace == null)
			toReplace = "";
		else
			toReplace = strReplace;

		int pos = strOrig.length();

		while (pos > -1) {
			pos = strOrig.lastIndexOf(strFind, pos);
			if (pos > -1)
				sb.replace(pos, pos + strFind.length(), toReplace);
			pos = pos - strFind.length();
		}

		return sb.toString();
	}

}

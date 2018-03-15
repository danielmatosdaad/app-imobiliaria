package com.br4dev.imob.negocio.dto;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PastaDTO implements Cloneable, DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String FILE_SEPARATOR = "\\";
	public static String USER_HOME = "";
	public static String USER_DIR = "";
	public static String USER_NAME = "";
	public static String DOCUMENTO_DEFAULT = "documento";
	public static String AUDIO_DEFAULT = "audio";
	public static String VIDEO_DEFAULT = "video";
	public static String IMAGEM_DEFAULT = "imagem";

	static {

		try {
			FILE_SEPARATOR = System.getProperty("file.separator");
			USER_HOME = System.getProperty("user.home");
			USER_DIR = System.getProperty("user.dir");
			USER_NAME = System.getProperty("user.name");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private String nome;
	private String url;
	private String urlBase;
	private Path path;

	public PastaDTO() {

	}

	public PastaDTO(String nome) {

		this.nome = nome;
		this.url = USER_HOME.concat(FILE_SEPARATOR).concat(nome).concat(FILE_SEPARATOR);
		this.urlBase = USER_HOME;
		this.path = Paths.get(this.url);
	}

	public PastaDTO(PastaDTO pastaRaiz, String nomeNovaPasta) {

		this.nome = nomeNovaPasta;
		this.url = pastaRaiz.url.concat(nomeNovaPasta).concat(FILE_SEPARATOR);
		this.path = Paths.get(this.url);
	}

	public String getNome() {
		return nome;
	}

	public String getUrl() {
		return url;
	}

	public Path getPath() {
		return path;
	}

	public PastaDTO addPasta(String nomePasta) {

		this.nome = nomePasta;
		this.url = this.url.concat(nomePasta).concat(FILE_SEPARATOR);
		this.path = Paths.get(this.url);
		return this;
	}

	public Path novoArquivo(String nomeArquivo, String formato) {
		String caminhoArquivo;
		if (nomeArquivo == null) {
			return null;
		}
		if (formato == null) {
			caminhoArquivo = this.url.concat(nomeArquivo);
			return Paths.get(caminhoArquivo);
		}

		if (nomeArquivo.contains(formato)) {
			caminhoArquivo = this.url.concat(nomeArquivo);
		} else {
			if (formato.contains(".")) {
				caminhoArquivo = this.url.concat(nomeArquivo).concat(formato);
			} else {
				caminhoArquivo = this.url.concat(nomeArquivo).concat(".").concat(formato);
			}

		}

		return Paths.get(caminhoArquivo);
	}

	public String getUrlBase() {
		return urlBase;
	}

	@Override
	public PastaDTO clone() {

		try {
			return (PastaDTO) super.clone();
		} catch (Exception e) {
			PastaDTO pasta = new PastaDTO();
			pasta.nome = this.nome;
			pasta.url = this.url;
			pasta.urlBase = this.urlBase;
			pasta.path = this.path;
			return pasta;
		}

	}

}

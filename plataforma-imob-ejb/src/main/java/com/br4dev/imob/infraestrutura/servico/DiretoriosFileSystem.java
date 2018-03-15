package com.br4dev.imob.infraestrutura.servico;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import org.apache.commons.codec.binary.Base64;
import com.br4dev.imob.infraestrutura.dto.ParametroDTO;
import com.br4dev.imob.infraestrutura.dto.ParametroValor;
import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.Mensagem;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.negocio.dto.PastaDTO;

@Startup
@Singleton
@ApplicationScoped
public class DiretoriosFileSystem implements IServicoDiretorioFileSystem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	private PastaDTO pastaImagem;
	private PastaDTO pastaVideo;
	private PastaDTO pastaAudio;
	private PastaDTO pastaDocumento;

	@Inject
	private IServicoParametro parametroServico;

	@PostConstruct
	public void init() {

		try {

			ParametroDTO parametroDTO = this.parametroServico
					.buscarPorId(ParametroValor.ENDERECO_BASE_IMAGEM.getValue());
			this.pastaImagem = criarDiretorio(parametroDTO.getValor());
			parametroDTO = this.parametroServico.buscarPorId(ParametroValor.ENDERECO_BASE_VIDEO.getValue());
			this.pastaVideo = criarDiretorio(parametroDTO.getValor());
			parametroDTO = this.parametroServico.buscarPorId(ParametroValor.ENDERECO_BASE_AUDIO.getValue());
			this.pastaAudio = criarDiretorio(parametroDTO.getValor());
			parametroDTO = this.parametroServico.buscarPorId(ParametroValor.ENDERECO_BASE_DOCUMENTO.getValue());
			this.pastaDocumento = criarDiretorio(parametroDTO.getValor());

		} catch (Exception e) {
			log.info("erro ao ler parametros de diretorio image,video,audio e documento" + e.getMessage());
			log.info("criando pastas default");

			criarDiretorio(PastaDTO.IMAGEM_DEFAULT);
			criarDiretorio(PastaDTO.VIDEO_DEFAULT);
			criarDiretorio(PastaDTO.AUDIO_DEFAULT);
			criarDiretorio(PastaDTO.DOCUMENTO_DEFAULT);

		}

	}

	private PastaDTO criarDiretorio(String nome) {

		try {
			PastaDTO pasta = new PastaDTO(nome);
			Path dir = Paths.get(pasta.getUrl());
			if (dir == null) {
				log.info("Erro ao criar diretório: " + nome);
				return null;
			}
			boolean isDiretorioExistente = Files.isDirectory(dir);
			if (!isDiretorioExistente) {
				Files.createDirectories(dir);
				// Files.createDirectory(dir);
				log.info("Diretório criado: " + dir.toString());
			}
			return pasta;
		} catch (Exception e) {
			log.info("Erro ao criar diretório: " + nome);
			throw new RuntimeException(e.getMessage());
		}

	}

	public PastaDTO criarDiretorio(PastaDTO pasta) {

		try {
			Path dir = Paths.get(pasta.getUrl());
			boolean isDiretorioExistente = Files.isDirectory(dir);
			if (!isDiretorioExistente) {
				Files.createDirectory(dir);
				log.info("Diretório criado: " + pasta.getUrl());
			}
			return pasta;
		} catch (Exception e) {
			log.info("Erro ao criar diretório");
			throw new RuntimeException(e.getMessage());
		}

	}

	public void salvarImagemStream(Path pathArquivo, InputStream in) throws NegocioException, InfraException {

		try {
			if (!Files.exists(pathArquivo)) {
				try {
					Files.createDirectories(pathArquivo.getParent());
					Files.copy(in, pathArquivo);
				} catch (FileAlreadyExistsException x) {
					System.err.format("file named %s" + " already exists%n", pathArquivo);
				} catch (IOException x) {
					System.err.format("createFile error: %s%n", x);
				}
			} else {
				System.out.println("deletando");
				Files.delete(pathArquivo);
				System.out.println("copiando");
				Files.copy(in, pathArquivo);
			}
		} catch (Exception e) {
			throw new NegocioException(Mensagem.ERRO_NEGOCIO,
					new RuntimeException("Houveram problemas ao salvar imagem."));
		}

	}

	public BufferedImage buscarBufferedImage(Path path) throws NegocioException, InfraException {

		try {
			BufferedImage bufferedImage = ImageIO.read(Files.newInputStream(path));
			return bufferedImage;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String converterImagemParaBase64(Long id, String nomeArquivo, String formato)
			throws NegocioException, InfraException {
		return null;
	}

	public String buscarImagem64(Path path) throws NegocioException, InfraException {
		try {
			long start = System.currentTimeMillis();
			byte[] bytes = Files.readAllBytes(path);
			long elapsed = System.currentTimeMillis() - start;
			System.out.println("Tempo execução Leitura: " + elapsed);
			start = System.currentTimeMillis();
			String base64 = Base64.encodeBase64String(bytes);
			elapsed = System.currentTimeMillis() - start;
			System.out.println("Tempo execução conversao: " + elapsed);
			return base64;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public InputStream buscarImagem(Path pathArquivo) throws NegocioException, InfraException {
		try {

			if (pathArquivo != null) {
				if (Files.exists(pathArquivo)) {
					return Files.newInputStream(pathArquivo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PastaDTO obterPastaImagem() throws NegocioException, InfraException {

		return this.pastaImagem.clone();
	}

	@Override
	public PastaDTO obterPastaVideo() throws NegocioException, InfraException {
		return this.pastaVideo.clone();
	}

	@Override
	public PastaDTO obterPastaAudio() throws NegocioException, InfraException {
		return this.pastaAudio.clone();
	}

	@Override
	public PastaDTO obterPastaDocumento() throws NegocioException, InfraException {
		return this.pastaDocumento.clone();
	}

	@Override
	public List<PastaDTO> obterPastas() throws NegocioException, InfraException {

		List<PastaDTO> pastas = new ArrayList<PastaDTO>();

		pastas.add(obterPastaImagem());
		pastas.add(obterPastaVideo());
		pastas.add(obterPastaAudio());
		pastas.add(obterPastaDocumento());

		return pastas;
	}
}

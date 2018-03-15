package com.br4dev.imob.infraestrutura.servico;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import org.apache.commons.codec.binary.Base64;
import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.Mensagem;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.negocio.dto.FileSystemDTO;
import com.br4dev.imob.negocio.dto.PastaDTO;

@Stateless
@Local(IServicoFileSystem.class)
public class ServicoFileSystemImp implements IServicoFileSystem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	private IServicoDiretorioFileSystem diretorioFileSystemServico;

	public PastaDTO criarDiretorio(String nome) {

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
	public void salvarImagemStream(FileSystemDTO fileSystemDTO) throws NegocioException, InfraException {

		if (fileSystemDTO == null || fileSystemDTO.getEnderecoArquivo() == null || fileSystemDTO.getIs() == null) {
			throw new NegocioException(Mensagem.ERRO_NEGOCIO, new RuntimeException("Dados inválidos"));
		}

		salvarImagemStream(fileSystemDTO.getEnderecoArquivo(), fileSystemDTO.getIs());
	}

	@Override
	public BufferedImage buscarBufferedImage(FileSystemDTO fileSystemDTO) throws NegocioException, InfraException {

		if (fileSystemDTO == null || fileSystemDTO.getFormato() == null || fileSystemDTO.getNomeArquivo() == null) {
			throw new NegocioException(Mensagem.ERRO_NEGOCIO, new RuntimeException("Dados inválidos"));
		}

		return buscarBufferedImage(fileSystemDTO.getEnderecoArquivo());
	}

	@Override
	public String buscarImagem64(FileSystemDTO fileSystemDTO) throws NegocioException, InfraException {
		if (fileSystemDTO == null || fileSystemDTO.getFormato() == null || fileSystemDTO.getNomeArquivo() == null) {
			throw new NegocioException(Mensagem.ERRO_NEGOCIO, new RuntimeException("Dados inválidos"));
		}
		return buscarImagem64(fileSystemDTO.getEnderecoArquivo());
	}

	@Override
	public FileSystemDTO buscarImagem(FileSystemDTO fileSystemDTO) throws NegocioException, InfraException {
		if (fileSystemDTO == null || fileSystemDTO.getEnderecoArquivo() == null || fileSystemDTO.getFormato() == null
				|| fileSystemDTO.getNomeArquivo() == null) {
			throw new NegocioException(Mensagem.ERRO_NEGOCIO, new RuntimeException("Dados inválidos"));
		}

		InputStream is = buscarImagem(fileSystemDTO.getEnderecoArquivo());
		FileSystemDTO fileSystem = new FileSystemDTO(fileSystemDTO, is);
		return fileSystem;
	}

	@Override
	public FileSystemDTO novoFileSystemDTO(String nomeArquivo, String extensao, InputStream stream,
			String... identificadoresPasta) throws NegocioException, InfraException {

		PastaDTO pastaArquivo = this.diretorioFileSystemServico.obterPastaImagem();
		for (String idPasta : identificadoresPasta) {
			pastaArquivo.addPasta(idPasta);
		}
		Path enderecoArquivo = pastaArquivo.novoArquivo(nomeArquivo, extensao);
		FileSystemDTO fileSystemDTO = new FileSystemDTO(pastaArquivo, enderecoArquivo, nomeArquivo, extensao, stream);

		return fileSystemDTO;
	}

}

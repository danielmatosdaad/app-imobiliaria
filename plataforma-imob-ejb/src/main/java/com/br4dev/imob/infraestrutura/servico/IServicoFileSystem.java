package com.br4dev.imob.infraestrutura.servico;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.negocio.dto.FileSystemDTO;
import com.br4dev.imob.negocio.servico.IServico;

public interface IServicoFileSystem extends IServico<FileSystemDTO> {

	public void salvarImagemStream(FileSystemDTO fileSystemDTO) throws NegocioException, InfraException;

	public BufferedImage buscarBufferedImage(FileSystemDTO fileSystemDTO) throws NegocioException, InfraException;

	public String buscarImagem64(FileSystemDTO fileSystemDTO) throws NegocioException, InfraException;

	public FileSystemDTO buscarImagem(FileSystemDTO fileSystemDTO) throws NegocioException, InfraException;

	public FileSystemDTO novoFileSystemDTO(String nomeArquivo, String extensao, InputStream stream,
			String... identificadoresPasta) throws NegocioException, InfraException;

}

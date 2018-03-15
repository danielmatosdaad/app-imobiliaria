package com.br4dev.imob.infraestrutura.servico;

import java.util.List;

import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.negocio.dto.PastaDTO;
import com.br4dev.imob.negocio.servico.IServico;

public interface IServicoDiretorioFileSystem extends IServico<PastaDTO> {

	public PastaDTO obterPastaImagem() throws NegocioException, InfraException;

	public PastaDTO obterPastaVideo() throws NegocioException, InfraException;

	public PastaDTO obterPastaAudio() throws NegocioException, InfraException;

	public PastaDTO obterPastaDocumento() throws NegocioException, InfraException;

	public List<PastaDTO> obterPastas() throws NegocioException, InfraException;
}

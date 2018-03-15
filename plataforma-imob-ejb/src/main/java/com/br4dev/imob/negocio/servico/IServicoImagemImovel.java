package com.br4dev.imob.negocio.servico;

import java.util.List;

import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.negocio.dto.ImagemImovelDTO;

public interface IServicoImagemImovel extends IServicoDAO<ImagemImovelDTO> {


	public void configuraPathImagem(ImagemImovelDTO imagemDTO) throws NegocioException, InfraException;

	public void salvar(List<ImagemImovelDTO> imagens) throws NegocioException, InfraException;
}

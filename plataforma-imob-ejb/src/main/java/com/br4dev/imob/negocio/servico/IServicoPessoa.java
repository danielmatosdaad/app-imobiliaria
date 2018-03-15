package com.br4dev.imob.negocio.servico;

import java.util.List;

import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.negocio.dto.InformacaoImovelPublicadoDTO;
import com.br4dev.imob.negocio.dto.PessoaDTO;

public interface IServicoPessoa extends IServicoDAO<PessoaDTO> {

	public List<InformacaoImovelPublicadoDTO> buscarTodosImoveisPulbicados(long idPessoa)
			throws NegocioException, InfraException;


}

package com.br4dev.imob.infraestrutura.servico;

import com.br4dev.imob.infraestrutura.dto.EnvioDTO;
import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.negocio.servico.IServico;

public interface IServicoGeoLocalizacao extends IServico<EnvioDTO> {

	public EnvioDTO buscarLocalizacao(EnvioDTO envioDTO) throws InfraException, NegocioException;

}

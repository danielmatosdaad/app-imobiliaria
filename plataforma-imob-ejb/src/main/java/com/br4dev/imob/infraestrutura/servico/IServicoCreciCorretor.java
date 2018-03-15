package com.br4dev.imob.infraestrutura.servico;

import java.util.List;

import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.negocio.dto.CorretorDTO;
import com.br4dev.imob.negocio.servico.IServico;

public interface IServicoCreciCorretor extends IServico<CorretorDTO> {

	public boolean validaPorInscricao(String inscricao) throws InfraException, NegocioException;
	
	public CorretorDTO pesquisarPorInscricao(String inscricao) throws InfraException, NegocioException;

	public List<CorretorDTO> pesquisarPorNome(String nome) throws InfraException, NegocioException;;

	public List<CorretorDTO> pesquisarPorNomeFantasiaNpa(String nomeFantasicaNPA) throws InfraException, NegocioException;;

	public List<CorretorDTO> pesquisarPorCidadeBairro(String cidadadeBairro) throws InfraException, NegocioException;;

}
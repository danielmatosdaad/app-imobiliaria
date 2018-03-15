/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.br4dev.imob.negocio.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.Mensagem;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.infraestrutura.servico.MonitorInterceptor;
import com.br4dev.imob.negocio.dto.ImovelDTO;
import com.br4dev.imob.negocio.dto.filtros.FiltroDTO;
import com.br4dev.imob.negocio.entidade.Imovel;
import com.br4dev.imob.negocio.entidade.TipoOperacao;
import com.br4dev.imob.persistencia.repositorio.imp.ImovelRepositorio;
import com.br4dev.util.Conversor;

@Stateless
@Remote({ IServicoImovelRemote.class, IServicoDAORemote.class })
@Local({ IServicoImovel.class, IServicoDAO.class })
@Interceptors(MonitorInterceptor.class)
public class ServicoImovelImp
		implements IServicoImovel, IServicoImovelRemote, IServicoDAO<ImovelDTO>, IServicoDAORemote<ImovelDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	private ImovelRepositorio respositorioImovel;

	@Override
	public void salvar(ImovelDTO imovelDTO) throws NegocioException, InfraException {

		Imovel imovel = Conversor.converter(imovelDTO, Imovel.class);
		log.info("Registrando " + imovelDTO.getCidade());
		respositorioImovel.adicionar(imovel);

	}

	@Override
	public void excluir(long idImovel) throws NegocioException, InfraException {
		log.info("Excluindo idImovel " + idImovel);
	}

	@Override
	public void atualizar(ImovelDTO ImovelDTO) throws NegocioException, InfraException {
		Imovel Imovel = Conversor.converter(ImovelDTO, Imovel.class);
		respositorioImovel.atualizar(Imovel);
	}

	@Override
	public List<ImovelDTO> listaComFiltro(FiltroDTO filtroDTO, int resultados, int pagina)
			throws NegocioException, InfraException {

		List<Imovel> Imovels;
		try {
			TipoOperacao tipoOperacao = Conversor.converter(filtroDTO.getTipoOperacao(), TipoOperacao.class);
			Imovels = respositorioImovel.listaPaginadaComFiltro(filtroDTO.getCampoValores(), tipoOperacao, pagina, resultados);
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new NegocioException(Mensagem.ERRO_INFRA, e);
		}
		List<ImovelDTO> dtos = new ArrayList<ImovelDTO>();
		for (Imovel p : Imovels) {
			ImovelDTO ImovelDTO = Conversor.converter(p, ImovelDTO.class);
			dtos.add(ImovelDTO);
		}

		return dtos;
	}


	@Override
	public List<ImovelDTO> filtrar(FiltroDTO filtro) throws NegocioException, InfraException {
		
		TipoOperacao tipoOperacao = Conversor.converter(filtro.getTipoOperacao(), TipoOperacao.class);
		List<Imovel> Imovels = respositorioImovel.filtrar(filtro.getCampoValores(),tipoOperacao);

		List<ImovelDTO> dtos = new ArrayList<ImovelDTO>();
		for (Imovel i : Imovels) {
			ImovelDTO imovelDTO = Conversor.converter(i, ImovelDTO.class);
			dtos.add(imovelDTO);
		}
		return dtos;
	}

	@Override
	public ImovelDTO buscarPorId(long id) throws NegocioException, InfraException {
		Imovel Imovel = respositorioImovel.buscarPorId(id);
		ImovelDTO ImovelDTO = Conversor.converter(Imovel, ImovelDTO.class);
		return ImovelDTO;
	}

	@Override
	public List<ImovelDTO> buscarTodos() throws NegocioException, InfraException {

		List<Imovel> Imovels = respositorioImovel.buscarTodos();
		List<ImovelDTO> dtos = new ArrayList<ImovelDTO>();
		for (Imovel p : Imovels) {
			ImovelDTO ImovelDTO = Conversor.converter(p, ImovelDTO.class);
			dtos.add(ImovelDTO);
		}
		return dtos;
	}

}

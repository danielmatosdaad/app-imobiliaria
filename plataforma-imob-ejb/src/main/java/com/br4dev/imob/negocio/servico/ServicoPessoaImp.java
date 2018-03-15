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
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.infraestrutura.servico.MonitorInterceptor;
import com.br4dev.imob.negocio.dto.InformacaoImovelPublicadoDTO;
import com.br4dev.imob.negocio.dto.PessoaDTO;
import com.br4dev.imob.negocio.dto.filtros.FiltroDTO;
import com.br4dev.imob.negocio.entidade.Pessoa;
import com.br4dev.imob.negocio.entidade.TipoOperacao;
import com.br4dev.imob.persistencia.repositorio.imp.PessoaRepositorio;
import com.br4dev.util.Conversor;

@Stateless
@Remote({ IServicoPessoaRemote.class, IServicoDAORemote.class })
@Local({ IServicoPessoa.class, IServicoDAO.class })
@Interceptors(MonitorInterceptor.class)
public class ServicoPessoaImp
		implements IServicoPessoa, IServicoPessoaRemote, IServicoDAO<PessoaDTO>, IServicoDAORemote<PessoaDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	private PessoaRepositorio respositorioPessoa;

	@Inject
	private Event<Pessoa> pessoaEventSrc;

	@Override
	public void salvar(PessoaDTO pessoaDTO) throws NegocioException, InfraException {

		Pessoa pessoa = Conversor.converter(pessoaDTO, Pessoa.class);
		log.info("Registrando " + pessoa.getNome());
		respositorioPessoa.adicionar(pessoa);
		pessoaEventSrc.fire(pessoa);

	}

	@Override
	public void excluir(long idPessoa) throws NegocioException, InfraException {
		log.info("Excluindo idPessoa " + idPessoa);
		respositorioPessoa.removerPorId(idPessoa);
	}

	@Override
	public void atualizar(PessoaDTO pessoaDTO) throws NegocioException, InfraException {
		Pessoa pessoa = Conversor.converter(pessoaDTO, Pessoa.class);
		respositorioPessoa.atualizar(pessoa);
	}

	@Override
	public List<InformacaoImovelPublicadoDTO> buscarTodosImoveisPulbicados(long idPessoa)
			throws NegocioException, InfraException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaDTO> listaComFiltro(FiltroDTO consulta, int resultados, int pagina)
			throws NegocioException, InfraException {
		throw new RuntimeException("Não implementado");
	}

	@Override
	public PessoaDTO buscarPorId(long id) throws NegocioException, InfraException {
		Pessoa entidade = respositorioPessoa.buscarPorId(id);
		PessoaDTO pessoaDTO = Conversor.converter(entidade, PessoaDTO.class);
		return pessoaDTO;
	}

	@Override
	public List<PessoaDTO> buscarTodos() throws NegocioException, InfraException {

		List<Pessoa> pessoas = respositorioPessoa.buscarTodos();
		List<PessoaDTO> dtos = new ArrayList<PessoaDTO>();
		for (Pessoa p : pessoas) {
			PessoaDTO pessoaDTO = Conversor.converter(p, PessoaDTO.class);
			dtos.add(pessoaDTO);
		}

		return dtos;
	}


	@Override
	public List<PessoaDTO> filtrar(FiltroDTO filtroDTO) throws NegocioException, InfraException {

		TipoOperacao tipoOperacao = Conversor.converter(filtroDTO.getTipoOperacao(),TipoOperacao.class);
		List<Pessoa> pessoas = respositorioPessoa.filtrar(filtroDTO.getCampoValores(),tipoOperacao);

		List<PessoaDTO> dtos = new ArrayList<PessoaDTO>();
		for (Pessoa p : pessoas) {
			PessoaDTO pessoaDTO = Conversor.converter(p, PessoaDTO.class);
			dtos.add(pessoaDTO);
		}
		return dtos;
	}

}

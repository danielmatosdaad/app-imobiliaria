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
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.Mensagem;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.infraestrutura.servico.MonitorInterceptor;
import com.br4dev.imob.negocio.dto.AgendamentoDTO;
import com.br4dev.imob.negocio.dto.PessoaDTO;
import com.br4dev.imob.negocio.dto.filtros.FiltroDTO;
import com.br4dev.imob.negocio.dto.filtros.TipoOperacaoDTO;
import com.br4dev.imob.negocio.entidade.Agendamento;
import com.br4dev.imob.negocio.entidade.Pessoa;
import com.br4dev.imob.negocio.entidade.TipoOperacao;
import com.br4dev.imob.persistencia.repositorio.imp.AgendamentoRepositorio;
import com.br4dev.imob.persistencia.repositorio.imp.PessoaRepositorio;
import com.br4dev.util.Conversor;

@Stateless
@Remote({ IServicoAgendamentoRemote.class, IServicoDAORemote.class })
@Local({ IServicoAgendamento.class, IServicoDAO.class })
@Interceptors(MonitorInterceptor.class)
public class ServicoAgendamentoImp implements IServicoAgendamento, IServicoAgendamentoRemote,
		IServicoDAO<AgendamentoDTO>, IServicoDAORemote<AgendamentoDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private AgendamentoRepositorio agendamentoRepositorio;

	@Inject
	PessoaRepositorio pessoaRepositorio;

	@Override
	public void salvar(AgendamentoDTO dto) throws NegocioException, InfraException {

		Agendamento agendamento = Conversor.converter(dto, Agendamento.class);

		Pessoa pessoa = pessoaRepositorio.buscarPorId(dto.getPessoa().getId());
		Pessoa pessoaAgendada = pessoaRepositorio.buscarPorId(dto.getPessoaAgendada().getId());
		agendamento.setPessoa(pessoa);
		agendamento.setPessoaAgendada(pessoaAgendada);

		this.agendamentoRepositorio.adicionar(agendamento);
		dto.setId(agendamento.getId());

	}

	@Override
	public void excluir(long id) throws NegocioException, InfraException {
		this.agendamentoRepositorio.removerPorId(id);
	}

	@Override
	public void atualizar(AgendamentoDTO dto) throws NegocioException, InfraException {
		Agendamento agendamento = Conversor.converter(dto, Agendamento.class);
		this.agendamentoRepositorio.atualizar(agendamento);
	}

	@Override
	public AgendamentoDTO buscarPorId(long id) throws NegocioException, InfraException {
		Agendamento entidade = agendamentoRepositorio.buscarPorId(id);
		AgendamentoDTO agendamentoDTO = Conversor.converter(entidade, AgendamentoDTO.class);
		return agendamentoDTO;
	}

	@Override
	public List<AgendamentoDTO> buscarTodos() throws NegocioException, InfraException {
		List<Agendamento> agendamentos = agendamentoRepositorio.buscarTodos();
		List<AgendamentoDTO> dtos = new ArrayList<AgendamentoDTO>();
		for (Agendamento a : agendamentos) {
			AgendamentoDTO agendamentoDTO = Conversor.converter(a, AgendamentoDTO.class);
			dtos.add(agendamentoDTO);
		}
		return dtos;
	}

	@Override
	public List<AgendamentoDTO> listaComFiltro(FiltroDTO filtro, int resultados, int pagina)
			throws NegocioException, InfraException {

		TipoOperacao tipoOperacao = Conversor.converter(filtro.getTipoOperacao(), TipoOperacao.class);

		List<Agendamento> agendamentos = agendamentoRepositorio.listaPaginadaComFiltro(filtro.getCampoValores(),
				tipoOperacao, pagina, resultados);
		List<AgendamentoDTO> dtos = new ArrayList<AgendamentoDTO>();
		for (Agendamento a : agendamentos) {
			AgendamentoDTO agendamentoDTO = Conversor.converter(a, AgendamentoDTO.class);
			dtos.add(agendamentoDTO);
		}
		return dtos;
	}

	@Override
	public List<AgendamentoDTO> buscarAgenda(Long idPessoa) throws NegocioException, InfraException {

		HashMap<String, String> fill = new HashMap<String, String>();
		fill.put("idPessoa", idPessoa.toString());
		FiltroDTO filtroDTO = new FiltroDTO(fill, TipoOperacaoDTO.IGUAL);
		return filtrar(filtroDTO);
	}

	@Override
	public List<AgendamentoDTO> filtrar(FiltroDTO filtro) throws NegocioException, InfraException {

		TipoOperacao tipoOperacao = Conversor.converter(filtro.getTipoOperacao(), TipoOperacao.class);
		List<Agendamento> agendamentos = agendamentoRepositorio.filtrar(filtro.getCampoValores(), tipoOperacao);
		List<AgendamentoDTO> dtos = new ArrayList<AgendamentoDTO>();
		for (Agendamento a : agendamentos) {
			AgendamentoDTO agendamentoDTO = Conversor.converter(a, AgendamentoDTO.class);
			dtos.add(agendamentoDTO);
		}
		return dtos;
	}

	@Override
	public AgendamentoDTO agendar(AgendamentoDTO agendamentoDTO) throws NegocioException, InfraException {

		if (agendamentoDTO == null) {
			throw new NegocioException(Mensagem.ERRO_NEGOCIO);
		}
		salvar(agendamentoDTO);
		return agendamentoDTO;
	}

}

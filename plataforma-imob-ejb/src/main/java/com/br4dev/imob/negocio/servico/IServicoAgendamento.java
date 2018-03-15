package com.br4dev.imob.negocio.servico;

import java.util.List;

import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.negocio.dto.AgendamentoDTO;

public interface IServicoAgendamento extends IServicoDAO<AgendamentoDTO> {

	public List<AgendamentoDTO> buscarAgenda(Long idPessoa) throws NegocioException, InfraException;

	public AgendamentoDTO agendar(AgendamentoDTO agendamento) throws NegocioException, InfraException;

}

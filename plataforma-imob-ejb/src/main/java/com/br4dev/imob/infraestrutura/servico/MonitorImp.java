package com.br4dev.imob.infraestrutura.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.br4dev.imob.infraestrutra.persistencia.repositorio.imp.MonitorRepositorio;
import com.br4dev.imob.infraestrutura.dto.MonitorDTO;
import com.br4dev.imob.infraestrutura.entidade.Monitor;
import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.Mensagem;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.negocio.dto.filtros.FiltroDTO;
import com.br4dev.imob.negocio.entidade.TipoOperacao;
import com.br4dev.util.Conversor;

@Local(IServicoMonitor.class)
@Stateless
public class MonitorImp implements IServicoMonitor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	private MonitorRepositorio monitorRespositorio;

	@Override
	@Asynchronous
	public void monitorar(MonitorDTO monitorDTO) {

		try {
			log.info("Gravando a monitoração ");
			salvar(monitorDTO);
			log.info("Monitoração gravada com sucesso");
		} catch (Exception e) {
			log.info("Erro ao gravar a monitoração.");
		}

	}

	@Override
	public void salvar(MonitorDTO monitorDTO) throws NegocioException, InfraException {

		Monitor monitor = Conversor.converter(monitorDTO, Monitor.class);
		monitorRespositorio.adicionar(monitor);
	}

	@Override
	public void excluir(long idImovel) throws NegocioException, InfraException {
		log.info("Excluindo idImovel " + idImovel);
	}

	@Override
	public void atualizar(MonitorDTO monitorDTO) throws NegocioException, InfraException {
		Monitor monitor = Conversor.converter(monitorDTO, Monitor.class);
		monitorRespositorio.atualizar(monitor);
	}

	public List<MonitorDTO> listaComFiltro(Map<String, String> queryParams, int resultados, int pagina)
			throws NegocioException, InfraException {

		List<Monitor> monitores;
		try {
			monitores = monitorRespositorio.buscaComFiltro(pagina, resultados, queryParams);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(Mensagem.ERRO_INFRA, e);
		}
		List<MonitorDTO> dtos = new ArrayList<MonitorDTO>();
		for (Monitor monitor : monitores) {
			MonitorDTO dto = Conversor.converter(monitor, MonitorDTO.class);
			dtos.add(dto);
		}

		return dtos;
	}

	public List<MonitorDTO> filtrar(FiltroDTO filtroDTO) throws NegocioException, InfraException {

		TipoOperacao tipoOperacao = Conversor.converter(filtroDTO.getTipoOperacao(),TipoOperacao.class);
		List<Monitor> lista = monitorRespositorio.filtrar(filtroDTO.getCampoValores(),tipoOperacao);

		List<MonitorDTO> dtos = new ArrayList<MonitorDTO>();
		for (Monitor monitor : lista) {
			MonitorDTO dto = Conversor.converter(monitor, MonitorDTO.class);
			dtos.add(dto);
		}
		return dtos;
	}

	public List<MonitorDTO> buscarPorIntervalo(int[] range) throws NegocioException, InfraException {

		List<Monitor> lista = monitorRespositorio.buscarPorIntervalo(range);
		List<MonitorDTO> dtos = new ArrayList<MonitorDTO>();
		for (Monitor monitor : lista) {
			MonitorDTO Monitor = Conversor.converter(monitor, MonitorDTO.class);
			dtos.add(Monitor);
		}

		return dtos;
	}

	@Override
	public MonitorDTO buscarPorId(long id) throws NegocioException, InfraException {
		Monitor monitor = monitorRespositorio.buscarPorId(id);
		MonitorDTO dto = Conversor.converter(monitor, MonitorDTO.class);
		return dto;
	}

	@Override
	public List<MonitorDTO> buscarTodos() throws NegocioException, InfraException {

		List<Monitor> monitores = monitorRespositorio.buscarTodos();
		List<MonitorDTO> dtos = new ArrayList<MonitorDTO>();
		for (Monitor monitor : monitores) {
			MonitorDTO dto = Conversor.converter(monitor, MonitorDTO.class);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<MonitorDTO> listaComFiltro(FiltroDTO consulta, int resultados, int pagina)
			throws NegocioException, InfraException {
		// TODO Auto-generated method stub
		return null;
	}


}

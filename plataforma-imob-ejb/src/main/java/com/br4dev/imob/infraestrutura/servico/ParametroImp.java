package com.br4dev.imob.infraestrutura.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.br4dev.imob.infraestrutra.persistencia.repositorio.imp.ParametroRepositorio;
import com.br4dev.imob.infraestrutura.dto.ParametroDTO;
import com.br4dev.imob.infraestrutura.entidade.Parametro;
import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.Mensagem;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.negocio.dto.filtros.FiltroDTO;
import com.br4dev.imob.negocio.entidade.TipoOperacao;
import com.br4dev.util.Conversor;

@Local(IServicoParametro.class)
@Stateless
public class ParametroImp implements IServicoParametro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	private ParametroRepositorio parametroRepositorio;

	@Override
	public void salvar(ParametroDTO parametroDTO) throws NegocioException, InfraException {

		Parametro parametro = Conversor.converter(parametroDTO, Parametro.class);
		parametroRepositorio.adicionar(parametro);
	}

	@Override
	public void excluir(long idImovel) throws NegocioException, InfraException {
		log.info("Excluindo idImovel " + idImovel);
	}

	@Override
	public void atualizar(ParametroDTO parametroDTO) throws NegocioException, InfraException {
		Parametro parametro = Conversor.converter(parametroDTO, Parametro.class);
		parametroRepositorio.atualizar(parametro);
	}

	public List<ParametroDTO> listaComFiltro(Map<String, String> queryParams, int resultados, int pagina)
			throws NegocioException, InfraException {

		List<Parametro> Parametroes;
		try {
			Parametroes = parametroRepositorio.buscaComFiltro(pagina, resultados, queryParams);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(Mensagem.ERRO_INFRA, e);
		}
		List<ParametroDTO> dtos = new ArrayList<ParametroDTO>();
		for (Parametro Parametro : Parametroes) {
			ParametroDTO dto = Conversor.converter(Parametro, ParametroDTO.class);
			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public ParametroDTO buscarPorId(long id) throws NegocioException, InfraException {
		Parametro Parametro = parametroRepositorio.buscarPorId(id);
		ParametroDTO dto = Conversor.converter(Parametro, ParametroDTO.class);
		return dto;
	}

	@Override
	public List<ParametroDTO> buscarTodos() throws NegocioException, InfraException {

		List<Parametro> Parametroes = parametroRepositorio.buscarTodos();
		List<ParametroDTO> dtos = new ArrayList<ParametroDTO>();
		for (Parametro parametro : Parametroes) {
			ParametroDTO dto = Conversor.converter(parametro, ParametroDTO.class);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<ParametroDTO> listaComFiltro(FiltroDTO filtro, int resultados, int pagina)
			throws NegocioException, InfraException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<ParametroDTO> filtrar(FiltroDTO filtroDTO) throws NegocioException, InfraException {

		TipoOperacao tipoOperacao = Conversor.converter(filtroDTO.getTipoOperacao(),TipoOperacao.class);
		List<Parametro> parametros = parametroRepositorio.filtrar(filtroDTO.getCampoValores(),tipoOperacao);

		List<ParametroDTO> dtos = new ArrayList<ParametroDTO>();
		for (Parametro p : parametros) {
			ParametroDTO ParametroDTO = Conversor.converter(p, ParametroDTO.class);
			dtos.add(ParametroDTO);
		}
		return dtos;
	}

}

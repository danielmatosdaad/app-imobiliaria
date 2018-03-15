package com.br4dev.imob.negocio.servico;

import java.util.List;

import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.negocio.dto.DTO;
import com.br4dev.imob.negocio.dto.filtros.FiltroDTO;

public interface IServicoDAO<T extends DTO> extends IServico<T> {

	public void salvar(T dto) throws NegocioException, InfraException;

	public void excluir(long id) throws NegocioException, InfraException;

	public void atualizar(T dot) throws NegocioException, InfraException;

	public T buscarPorId(long id) throws NegocioException, InfraException;

	public List<T> buscarTodos() throws NegocioException, InfraException;
	
	public List<T> filtrar(FiltroDTO filtro)
			throws NegocioException, InfraException;

	public List<T> listaComFiltro(FiltroDTO filtro , int resultados, int pagina)
			throws NegocioException, InfraException;
	
}

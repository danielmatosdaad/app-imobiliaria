package com.br4dev.imob.persistencia.repositorio;

import java.util.List;

import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;

import com.br4dev.imob.negocio.entidade.Entidade;

public interface IRepositorio<T extends Entidade> {

	void adicionar(T item);

	void atualizar(T item);

	void remover(T item);

	void removerPorId(long id);
	
	void removerComCriteria(CriteriaDelete<T> criteria);

	T buscarPorId(long id);
	
	List<T> buscarTodos();

	List<T> listaComCriteria(CriteriaQuery<T> criteria);
	
	List<T> listaPaginadaComCriteria(CriteriaQuery<T> criteria, int pagina, int resultados);

	T consultaComCriteria(CriteriaQuery<T> criteria);
}
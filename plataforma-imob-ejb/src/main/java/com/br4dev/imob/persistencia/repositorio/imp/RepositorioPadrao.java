package com.br4dev.imob.persistencia.repositorio.imp;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.br4dev.imob.negocio.entidade.Entidade;
import com.br4dev.imob.negocio.entidade.TipoOperacao;
import com.br4dev.imob.persistencia.repositorio.IRepositorio;
import com.br4dev.imob.persistencia.util.ConsultaCriteriaBuilder;

@Dependent
public abstract class RepositorioPadrao<T extends Entidade> implements IRepositorio<T> {

	private Class<T> entityClass;

	public RepositorioPadrao(Class<T> clazz) {

		this.entityClass = clazz;
	}

	public abstract EntityManager getEntityManager();

	public Class<T> getEntityClass() {
		return entityClass;
	}

	@Override
	public void adicionar(T item) {

		getEntityManager().persist(item);
	}

	@Override
	public void atualizar(T item) {
		getEntityManager().merge(item);
	}

	@Override
	public void remover(T item) {
		getEntityManager().remove(item);
	}

	@Override
	public void removerComCriteria(CriteriaDelete<T> criteria) {
		int result = getEntityManager().createQuery(criteria).executeUpdate();
	}

	public void removerPorId(long id) {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaDelete<T> delete = cb.createCriteriaDelete(this.entityClass);
		CriteriaQuery<T> criteria = cb.createQuery(this.entityClass);
		Root<T> root = criteria.from(this.entityClass);
		delete.where(root.get("id").in(id));
		removerComCriteria(delete);
	}

	public T buscarPorId(long id) {

		return getEntityManager().find(this.entityClass, id);
	}

	@Override
	public List<T> buscarTodos() {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(this.entityClass);

		Root<T> from = query.from(this.entityClass);
		CriteriaQuery<T> select = query.select(from);

		TypedQuery<T> typedQuery = getEntityManager().createQuery(select);
		List<T> results = typedQuery.getResultList();
		return results;
	}

	public List<T> buscarPorIntervalo(int[] range) {

		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);

		List<T> resultado = q.getResultList();
		return resultado;
	}

	@Override
	public List<T> listaComCriteria(CriteriaQuery<T> criteria) {
		return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<T> listaPaginadaComCriteria(CriteriaQuery<T> criteria, int pagina, int resultados) {
		TypedQuery<T> query = getEntityManager().createQuery(criteria);
		query.setFirstResult((pagina - 1) * resultados);
		query.setMaxResults(resultados);
		return query.getResultList();
	}

	@Override
	public T consultaComCriteria(CriteriaQuery<T> criteria) {
		return getEntityManager().createQuery(criteria).getSingleResult();
	}

	
	public List<T> listaPaginadaComFiltro(Map<String, String> campoValores, TipoOperacao tipoOperacao, int pagina, int resultados) {
		if (campoValores == null || campoValores.isEmpty() || tipoOperacao == null) {

			return null;
		}
		CriteriaQuery<T> criteria = ConsultaCriteriaBuilder.criarConsulta(getEntityManager(), entityClass, campoValores,
				tipoOperacao);
		TypedQuery<T> query = getEntityManager().createQuery(criteria);
		query.setFirstResult((pagina - 1) * resultados);
		query.setMaxResults(resultados);
		return query.getResultList();
	}
	
	public List<T> filtrar(Map<String, String> campoValores, TipoOperacao tipoOperacao) {

		if (campoValores == null || campoValores.isEmpty() || tipoOperacao == null) {

			return null;
		}
		CriteriaQuery<T> criteria = ConsultaCriteriaBuilder.criarConsulta(getEntityManager(), entityClass, campoValores,
				tipoOperacao);

		return listaComCriteria(criteria);
	}



}

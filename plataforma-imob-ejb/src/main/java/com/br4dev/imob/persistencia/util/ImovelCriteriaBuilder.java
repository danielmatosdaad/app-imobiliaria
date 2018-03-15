package com.br4dev.imob.persistencia.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.br4dev.imob.negocio.dto.filtros.FiltroDTO;
import com.br4dev.imob.negocio.entidade.Imovel;

public final class ImovelCriteriaBuilder {

	private static final String MAX = "Max";
	private static final String MIN = "Min";
	private static final String EMPTY = "";

	private ImovelCriteriaBuilder() {
	}

	public static Predicate[] criarCriterioConsulta(EntityManager em, FiltroDTO filtro, CriteriaBuilder cb, Root<Imovel> imovel) throws NoSuchFieldException, SecurityException{
		List<Predicate> predicates = new ArrayList<Predicate>();
//		Metamodel metamodel = em.getMetamodel();
//		EntityType<Imovel> pClass = metamodel.entity(Imovel.class);
//		for (String param : filtro.keySet()) {
//			
//			if(param.endsWith(MIN)){
//				String nomeAtributo = param.replace(MIN, EMPTY);
//				Class<Comparable> tipo = (Class<Comparable>) Imovel.class.getDeclaredField(nomeAtributo).getType();
//				Path<Comparable> column = imovel.get(pClass.getSingularAttribute(nomeAtributo, tipo));
//				predicates.add(cb.greaterThanOrEqualTo(column, filtro.get(param)));
//			}
//			else if(param.endsWith(MAX)) {
//				String nomeAtributo = param.replace(MAX, EMPTY);
//				Class<Comparable> tipo = (Class<Comparable>) Imovel.class.getDeclaredField(nomeAtributo).getType();
//				Path<Comparable> column = imovel.get(pClass.getSingularAttribute(nomeAtributo, tipo));
//				predicates.add(cb.lessThanOrEqualTo(column, filtro.get(param)));
//			}
//			else {
//				predicates.add(imovel.get(param).in(filtro.get(param)));
//			}
//		}
//		
		Predicate[] predicatesArr = new Predicate[predicates.size()];
		predicates.toArray(predicatesArr);
		
		return predicatesArr;
	}

}

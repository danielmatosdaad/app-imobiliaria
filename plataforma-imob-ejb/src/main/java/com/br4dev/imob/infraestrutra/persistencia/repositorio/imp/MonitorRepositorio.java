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
package com.br4dev.imob.infraestrutra.persistencia.repositorio.imp;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.br4dev.imob.infraestrutura.entidade.Monitor;
import com.br4dev.imob.persistencia.repositorio.imp.RepositorioPadrao;

@Stateless
public class MonitorRepositorio extends RepositorioPadrao<Monitor> {

	@Inject
	private EntityManager entityManager;

	public MonitorRepositorio() {
		super(Monitor.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public List<Monitor> buscaComFiltro(int pagina, int resultados, Map<String, String> queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

}

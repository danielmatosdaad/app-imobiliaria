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
package com.br.br4.imob.plataforma_imob.test;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import com.br4dev.imob.negocio.dto.PessoaDTO;
import com.br4dev.imob.negocio.entidade.Pessoa;
import com.br4dev.imob.negocio.servico.ServicoPessoaImp;
import com.br4dev.util.Resources;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class PessoaTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(Pessoa.class, ServicoPessoaImp.class, Resources.class)
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Deploy our test datasource
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	@Inject
	ServicoPessoaImp servicoPessoa;

	@Inject
	Logger log;

	@Test
	public void testRegister() throws Exception {
		PessoaDTO novaPessoa = new PessoaDTO();
		novaPessoa.setNome("danie.matos");
		servicoPessoa.salvar(novaPessoa);
		assertNotNull(novaPessoa.getId());
		log.info(novaPessoa.getNome() + " inserido " + novaPessoa.getId());
	}

}

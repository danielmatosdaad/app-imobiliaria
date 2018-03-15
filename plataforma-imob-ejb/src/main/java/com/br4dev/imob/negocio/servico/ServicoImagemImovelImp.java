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

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.apache.commons.codec.binary.Base64;
import com.br4dev.imob.infraestrutura.dto.ParametroDTO;
import com.br4dev.imob.infraestrutura.dto.ParametroValor;
import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.Mensagem;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.infraestrutura.servico.IServicoFileSystem;
import com.br4dev.imob.infraestrutura.servico.IServicoParametro;
import com.br4dev.imob.infraestrutura.servico.MonitorInterceptor;
import com.br4dev.imob.negocio.dto.FileSystemDTO;
import com.br4dev.imob.negocio.dto.ImagemImovelDTO;
import com.br4dev.imob.negocio.dto.ImovelDTO;
import com.br4dev.imob.negocio.dto.TipoImagemNegocialDTO;
import com.br4dev.imob.negocio.dto.filtros.FiltroDTO;
import com.br4dev.imob.negocio.entidade.Imagem;
import com.br4dev.imob.negocio.entidade.Imovel;
import com.br4dev.imob.negocio.entidade.TipoOperacao;
import com.br4dev.imob.persistencia.repositorio.imp.ImagemRepositorio;
import com.br4dev.util.Conversor;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@Remote({ IServicoImagemRemote.class, IServicoDAORemote.class })
@Local({ IServicoImagemImovel.class, IServicoDAO.class })
public class ServicoImagemImovelImp implements IServicoImagemImovel, IServicoImagemRemote, IServicoDAO<ImagemImovelDTO>,
		IServicoDAORemote<ImagemImovelDTO> {

	private static final long serialVersionUID = 1L;
	private static final String PASTA_IMOVEL_DEFAUL = "imovel";
	private static String UPLOAD_FILE_SERVER_IMAGEM_IMOVEL;
	private static String UPLOAD_FILE_SERVER_IMAGEM_PESSOA;
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String USER_HOME = System.getProperty("user.home");

	@Inject
	private Logger log;
	@Inject
	private ImagemRepositorio respositorioImagem;
	@Inject
	private IServicoParametro parametroServico;
	@Inject
	private IServicoImovel imovelServico;
	@Inject
	private IServicoFileSystem fileSystemServico;

	@PostConstruct
	public void init() {
		try {
			ParametroDTO parametroDTO = this.parametroServico
					.buscarPorId(ParametroValor.LOCAL_GRAVACAO_IMAGEM_IMOVEL.getValue());
			UPLOAD_FILE_SERVER_IMAGEM_IMOVEL = parametroDTO.getValor();

		} catch (NegocioException | InfraException e) {
			UPLOAD_FILE_SERVER_IMAGEM_IMOVEL = USER_HOME.concat(FILE_SEPARATOR).concat(File.separator)
					.concat(PASTA_IMOVEL_DEFAUL).concat(FILE_SEPARATOR);
			e.printStackTrace();
		}
	}

	@Override
	public void salvar(ImagemImovelDTO imagemDTO) throws NegocioException, InfraException {

		if (imagemDTO == null) {

			throw new NegocioException(Mensagem.ERRO_NEGOCIO, new RuntimeException("Faltam dados obrigatórios"));
		}

		if (imagemDTO.getImagemStream() == null && imagemDTO.getImagemBase64() == null) {

			throw new NegocioException(Mensagem.ERRO_NEGOCIO, new RuntimeException("Nao foi possivel ler a imagem"));
		}

		if (imagemDTO.getTipoImagemNegocial() == null) {

			throw new NegocioException(Mensagem.ERRO_NEGOCIO,
					new RuntimeException("Necessario espeficificar qual tipo imagem negocial."));
		}

		if (imagemDTO.getIdImovel() <= 0) {

			throw new NegocioException(Mensagem.ERRO_NEGOCIO,
					new RuntimeException("Para adicionar uma imagem é necessario selecionar um imóvel"));
		}

		Imagem imagem = Conversor.converter(imagemDTO, Imagem.class);

		if (imagemDTO.getIdImovel() > 0) {
			ImovelDTO imovelDTO = imovelServico.buscarPorId(imagemDTO.getIdImovel());
			Imovel imovel = Conversor.converter(imovelDTO, Imovel.class);
			imagem.setImovel(imovel);
		}

		log.info("Registrando imagem para o imovel " + imagemDTO.getIdImovel());

		if (imagemDTO.getImagemBase64() != null) {

			byte[] imagemBase64bytes = Base64.decodeBase64(imagemDTO.getImagemBase64());
			InputStream imagemInputStream = new ByteArrayInputStream(imagemBase64bytes);
			imagemDTO.setImagemStream(imagemInputStream);
		}
		respositorioImagem.adicionar(imagem);

		FileSystemDTO fileSystemDTO = null;
		if (imagemDTO.getTipoImagemNegocial().equals(TipoImagemNegocialDTO.IMOVEL)) {

			fileSystemDTO = fileSystemServico.novoFileSystemDTO(imagemDTO.getNomeArquivo(), imagemDTO.getExtensao(),
					imagemDTO.getImagemStream(), UPLOAD_FILE_SERVER_IMAGEM_IMOVEL,
					String.valueOf(imagem.getImovel().getId()), String.valueOf(imagem.getId()));
		}

		imagem.setUrl(fileSystemDTO.getEnderecoArquivoRelativo());
		respositorioImagem.atualizar(imagem);
		fileSystemServico.salvarImagemStream(fileSystemDTO);

	}

	@Override
	public void excluir(long idImagem) throws NegocioException, InfraException {
		log.info("Excluindo idImagem " + idImagem);
		respositorioImagem.removerPorId(idImagem);

	}

	@Override
	public void atualizar(ImagemImovelDTO imagemDTO) throws NegocioException, InfraException {
		Imagem imagem = Conversor.converter(imagemDTO, Imagem.class);

		FileSystemDTO fileSystemDTO = fileSystemServico.novoFileSystemDTO(imagemDTO.getNomeArquivo(),
				imagemDTO.getExtensao(), imagemDTO.getImagemStream(), String.valueOf(imagem.getImovel().getId()),
				String.valueOf(imagem.getId()));
		imagem.setUrl(fileSystemDTO.getEnderecoArquivo().toString());
		respositorioImagem.atualizar(imagem);
		fileSystemServico.salvarImagemStream(fileSystemDTO);
	}

	public void configuraPathImagem(ImagemImovelDTO imagemDTO) throws NegocioException, InfraException {
		String qualifiedUploadFilePath = null;
		if (imagemDTO.getTipoImagemNegocial().equals(TipoImagemNegocialDTO.IMOVEL)) {
			qualifiedUploadFilePath = UPLOAD_FILE_SERVER_IMAGEM_IMOVEL;
		}

		if (imagemDTO.getTipoImagemNegocial().equals(TipoImagemNegocialDTO.PESSOA)) {
			qualifiedUploadFilePath = UPLOAD_FILE_SERVER_IMAGEM_PESSOA;
		}

		if (qualifiedUploadFilePath == null) {
			throw new NegocioException(Mensagem.ERRO_NEGOCIO,
					new RuntimeException("Local gravação arquivo não encontrado"));
		}

		int tamanhoCaminho = qualifiedUploadFilePath.length();

		if (qualifiedUploadFilePath.substring(tamanhoCaminho - 1) != File.pathSeparator) {

			qualifiedUploadFilePath = qualifiedUploadFilePath.concat(File.pathSeparator);
		}
		qualifiedUploadFilePath = qualifiedUploadFilePath.concat(String.valueOf(imagemDTO.getId()))
				.concat(File.pathSeparator);
		System.out.println(qualifiedUploadFilePath);
		imagemDTO.setUrl(qualifiedUploadFilePath);

	}

	@Override
	public List<ImagemImovelDTO> filtrar(FiltroDTO filtro) throws NegocioException, InfraException {

		TipoOperacao tipoOperacao = Conversor.converter(filtro.getTipoOperacao(), TipoOperacao.class);
		
		List<Imagem> Imagems = respositorioImagem.filtrar(filtro.getCampoValores(),tipoOperacao);

		List<ImagemImovelDTO> dtos = new ArrayList<ImagemImovelDTO>();
		for (Imagem bean : Imagems) {
			ImagemImovelDTO ImagemDTO = Conversor.converter(bean, ImagemImovelDTO.class);
			dtos.add(ImagemDTO);
		}
		return dtos;
	}

	@Override
	public ImagemImovelDTO buscarPorId(long id) throws NegocioException, InfraException {
		Imagem entidade = respositorioImagem.buscarPorId(id);
		ImagemImovelDTO imagemDTO = Conversor.converter(entidade, ImagemImovelDTO.class);
		return imagemDTO;
	}

	@Override
	public List<ImagemImovelDTO> buscarTodos() throws NegocioException, InfraException {

		List<Imagem> Imagems = respositorioImagem.buscarTodos();
		List<ImagemImovelDTO> dtos = new ArrayList<ImagemImovelDTO>();
		for (Imagem bean : Imagems) {
			ImagemImovelDTO ImagemDTO = Conversor.converter(bean, ImagemImovelDTO.class);
			dtos.add(ImagemDTO);
		}

		return dtos;
	}

	@Override
	public void salvar(List<ImagemImovelDTO> imagens) throws NegocioException, InfraException {

		if (imagens != null) {
			for (ImagemImovelDTO imagemDTO : imagens) {
				salvar(imagemDTO);
			}
		}

	}


	@Override
	public List<ImagemImovelDTO> listaComFiltro(FiltroDTO filtro, int resultados, int pagina)
			throws NegocioException, InfraException {
		
		return null;
	}

}

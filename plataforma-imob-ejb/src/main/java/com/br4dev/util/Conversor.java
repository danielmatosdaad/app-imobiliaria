package com.br4dev.util;

import org.modelmapper.ModelMapper;

import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.Mensagem;

public class Conversor {

	public static <T> T converter(Object objeto, Class<T> destino) throws InfraException {
		try {
			ModelMapper mapper = new ModelMapper();
			return mapper.map(objeto, destino);
		} catch (Exception e) {

			throw new InfraException(Mensagem.ERRO_INFRA, e);
		}

	}

	public static <T> T converter(ModelMapper mapper, Object objeto, Class<T> destino) throws InfraException {
		try {
			return mapper.map(objeto, destino);
		} catch (Exception e) {

			throw new InfraException(Mensagem.ERRO_INFRA, e);
		}

	}

	public static ModelMapper newInstance() throws InfraException {
		try {
			ModelMapper mapper = new ModelMapper();
			return mapper;
		} catch (Exception e) {

			throw new InfraException(Mensagem.ERRO_INFRA, e);
		}

	}
}

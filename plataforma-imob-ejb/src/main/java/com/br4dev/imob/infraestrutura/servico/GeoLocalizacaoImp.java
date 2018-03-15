package com.br4dev.imob.infraestrutura.servico;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import com.br4dev.imob.infraestrutura.dto.EnvioDTO;
import com.br4dev.imob.infraestrutura.dto.LocalizacaoDTO;
import com.br4dev.imob.infraestrutura.entidade.Localizacao;
import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.negocio.entidade.TipoOperacao;
import com.br4dev.imob.persistencia.repositorio.imp.LocalizacaoRepositorio;
import com.br4dev.util.Conversor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Stateless
public class GeoLocalizacaoImp implements IServicoGeoLocalizacao {

	private static final int PORTA_PROXY = 80;
	private static final String IP_PROXY = "192.168.100.161";
	private static final String USERNAME_PASSWORD = "daniel.matos:Enaldo16193113";
	private static final String ATTR_IP = "ip";
	private static final String HTTP_FREEGEOIP_NET_JSON = "http://www.freegeoip.net/json/";

	private static final boolean UTILIZAR_PROXY = false;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	private LocalizacaoRepositorio repositorioLocalizacao;

	public EnvioDTO buscarLocalizacao(EnvioDTO envioDTO) throws InfraException, NegocioException {

		if (envioDTO == null || envioDTO.getIp() == null) {

			return envioDTO;
		}
		log.info("buscando localizacao base interna ip: " + envioDTO.getIp());
		List<LocalizacaoDTO> localizacoes = buscarLocalizacaoServicoInterno(envioDTO);

		if (localizacoes != null && !localizacoes.isEmpty()) {
			log.info("ip achado base interna");
			configurarLocalizacao(envioDTO, localizacoes.get(0));
		}

		if (localizacoes == null || localizacoes.isEmpty()) {
			log.info("buscando localizacao em api externa :" + envioDTO.getIp());
			localizacoes = buscarLocalizacaoServicoExterno(envioDTO);

			if (localizacoes != null && !localizacoes.isEmpty()) {
				log.info("ip achado api externa :" + envioDTO.getIp());
				for (LocalizacaoDTO localizacaoDTO : localizacoes) {
					try {
						Localizacao localizacaoEntidade = Conversor.converter(localizacaoDTO, Localizacao.class);
						log.info("salvando informações em base interna :" + envioDTO.getIp());
						if (localizacaoEntidade != null
								&& (localizacaoEntidade.getLatitude() != null
										&& !localizacaoEntidade.getLatitude().equals("0"))
								&& (localizacaoEntidade.getLongitude() != null
										&& !localizacaoEntidade.getLongitude().equals("0"))) {
							repositorioLocalizacao.adicionar(localizacaoEntidade);
							log.info("informacoes salva :" + envioDTO.getIp());
						}

					} catch (Exception e) {
						log.info(e.getMessage());
						e.printStackTrace();
					}

				}
				configurarLocalizacao(envioDTO, localizacoes.get(0));
			}
		}

		return envioDTO;
	}

	public void configurarLocalizacao(EnvioDTO envioDTO, LocalizacaoDTO localizacaoDTO) {

		if (localizacaoDTO != null) {
			log.info("configurando objeto envioDTO");
			envioDTO.setIp(localizacaoDTO.getIp());
			envioDTO.setPais(localizacaoDTO.getCountry_name());
			envioDTO.setIsoCodePais(localizacaoDTO.getCountry_code());
			envioDTO.setNomeEstado(localizacaoDTO.getRegion_name());
			envioDTO.setIsoCodeEstado(localizacaoDTO.getRegion_code());
			envioDTO.setNomeCidade(localizacaoDTO.getCity());
			envioDTO.setCodigoPostalCidade(localizacaoDTO.getZip_code());
			envioDTO.setLatitude(localizacaoDTO.getLatitude());
			envioDTO.setLongitude(localizacaoDTO.getLongitude());
			envioDTO.setTimeZone(localizacaoDTO.getTime_zone());
		}

	}

	private List<LocalizacaoDTO> buscarLocalizacaoServicoInterno(EnvioDTO envioDTO) throws InfraException {

		Map<String, String> filtro = new HashMap<String, String>();
		filtro.put(ATTR_IP, envioDTO.getIp());
		List<Localizacao> localizacoes = repositorioLocalizacao.filtrar(filtro, TipoOperacao.IGUAL);

		List<LocalizacaoDTO> dtos = new ArrayList<LocalizacaoDTO>();
		for (Localizacao localizacao : localizacoes) {
			LocalizacaoDTO dto = Conversor.converter(localizacao, LocalizacaoDTO.class);
			dtos.add(dto);
		}
		return dtos;

	}

	private List<LocalizacaoDTO> buscarLocalizacaoServicoExterno(EnvioDTO envioDTO) {

		try {

			CloseableHttpClient httpclient = HttpClientBuilder.create().build();
			HttpResponse response = null;
			HttpGet get = new HttpGet(HTTP_FREEGEOIP_NET_JSON.concat(envioDTO.getIp()));
			get.setHeader("Accept", "application/json");
			get.setHeader("Content-type", "application/json");

			if (UTILIZAR_PROXY) {

				String basicAuth = "Basic " + new String(new Base64().encode(USERNAME_PASSWORD.getBytes()));
				get.setHeader("Authorization", basicAuth);
				HttpHost target = new HttpHost(IP_PROXY, PORTA_PROXY);
				RequestConfig config = RequestConfig.custom().setProxy(target).build();
				get.setConfig(config);
				response = httpclient.execute(target, get);

			} else {

				response = httpclient.execute(get);
			}

			if (response != null) {
				StatusLine statusLine = response.getStatusLine();
				HttpEntity entity = response.getEntity();

				if (statusLine.getStatusCode() >= 300) {
					throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
				}
				if (entity == null) {
					log.info("A resposta não contem conteudo");
					return null;
				}
				Gson gson = new GsonBuilder().create();
				Reader reader = new InputStreamReader(entity.getContent(), Charset.defaultCharset());
				LocalizacaoDTO dto = gson.fromJson(reader, LocalizacaoDTO.class);
				List<LocalizacaoDTO> dtos = new ArrayList<LocalizacaoDTO>();
				dtos.add(dto);
				return dtos;
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) throws Exception {

		CloseableHttpClient httpclient = HttpClientBuilder.create().build();

		HttpGet get = new HttpGet(HTTP_FREEGEOIP_NET_JSON);
		String basicAuth = "Basic " + new String(new Base64().encode(USERNAME_PASSWORD.getBytes()));
		get.setHeader("Accept", "application/json");
		get.setHeader("Authorization", basicAuth);
		get.setHeader("Content-type", "application/json");
		HttpHost target = new HttpHost(IP_PROXY, PORTA_PROXY);

		RequestConfig config = RequestConfig.custom().setProxy(target).build();
		get.setConfig(config);

		HttpResponse response = httpclient.execute(target, get);
		StatusLine statusLine = response.getStatusLine();
		HttpEntity entity = response.getEntity();

		if (statusLine.getStatusCode() >= 300) {
			throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		}
		if (entity == null) {
			throw new ClientProtocolException("Response contains no content");
		}
		Gson gson = new GsonBuilder().create();
		ContentType contentType = ContentType.getOrDefault(entity);
		Reader reader = new InputStreamReader(entity.getContent(), Charset.defaultCharset());
		LocalizacaoDTO dto = gson.fromJson(reader, LocalizacaoDTO.class);

		System.out.println(response.getStatusLine().getStatusCode());

	}
}

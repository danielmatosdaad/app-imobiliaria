package com.br4dev.imob.infraestrutura.servico;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.Mensagem;
import com.br4dev.imob.infraestrutura.execao.NegocioException;
import com.br4dev.imob.negocio.dto.CorretorDTO;

@Stateless
@Local(IServicoCreciCorretor.class)
public class ServicoCreciExternoImp implements IServicoCreciCorretor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PORTA_PROXY = 80;
	private static final String IP_PROXY = "192.168.100.161";
	private static final String USERNAME_PASSWORD = "daniel.matos:Enaldo16193114";
	private static final String URL_TARGET = "http://www.crecidf.conselho.net.br/form_pesquisa_cadastro_geral_site.php?acao=PesquisarCadastro&OK=1";
	private static final boolean UTILIZAR_PROXY = false;


	@Override
	public CorretorDTO pesquisarPorInscricao(String inscricao) throws InfraException, NegocioException {

		try {
			List<NameValuePair> form = new ArrayList<>();
			form.add(new BasicNameValuePair("intCodigoCadastro", inscricao));
			List<CorretorDTO> corretores = obterCorretores(form);
			if (corretores == null || corretores.isEmpty()) {
				throw new NegocioException(Mensagem.ERRO_INFRA,
						new RuntimeException("Número cresci não achado: " + inscricao));
			}
			return corretores.get(0);
		} catch (NegocioException ex) {
			throw ex;
		} catch (Exception e) {
			throw new InfraException(Mensagem.ERRO_INFRA, new RuntimeException("Tente novamente mais tarde."));
		}

	}

	@Override
	public List<CorretorDTO> pesquisarPorNome(String nome) throws InfraException, NegocioException {
		try {
			List<NameValuePair> form = new ArrayList<>();
			form.add(new BasicNameValuePair("strNome", nome));
			List<CorretorDTO> corretores = obterCorretores(form);
			if (corretores == null || corretores.isEmpty()) {
				throw new InfraException(Mensagem.ERRO_INFRA, new RuntimeException("Nome não achado: " + nome));
			}
			return corretores;
		} catch (Exception e) {
			throw new InfraException(Mensagem.ERRO_INFRA, new RuntimeException("Tente novamente mais tarde."));
		}

	}

	@Override
	public List<CorretorDTO> pesquisarPorNomeFantasiaNpa(String nomeFantasicaNPA)
			throws InfraException, NegocioException {

		try {
			List<NameValuePair> form = new ArrayList<>();
			form.add(new BasicNameValuePair("strNPA", nomeFantasicaNPA));
			List<CorretorDTO> corretores = obterCorretores(form);
			if (corretores == null || corretores.isEmpty()) {
				throw new InfraException(Mensagem.ERRO_INFRA,
						new RuntimeException("Nome Fantasia NPA não achado: " + nomeFantasicaNPA));
			}
			return corretores;
		} catch (Exception e) {
			throw new InfraException(Mensagem.ERRO_INFRA, new RuntimeException("Tente novamente mais tarde."));
		}

	}

	@Override
	public List<CorretorDTO> pesquisarPorCidadeBairro(String cidadadeBairro) throws InfraException, NegocioException {
		try {
			List<NameValuePair> form = new ArrayList<>();
			form.add(new BasicNameValuePair("strLocal", cidadadeBairro));

			List<CorretorDTO> corretores = obterCorretores(form);
			if (corretores == null || corretores.isEmpty()) {
				throw new InfraException(Mensagem.ERRO_INFRA,
						new RuntimeException("Cidade/Bairro não achado: " + cidadadeBairro));
			}
			return corretores;
		} catch (Exception e) {
			throw new InfraException(Mensagem.ERRO_INFRA, new RuntimeException("Tente novamente mais tarde."));
		}

	}

	private List<CorretorDTO> obterCorretores(List<NameValuePair> form) {
		List<CorretorDTO> corretores = new ArrayList<CorretorDTO>();
		try {
			try (CloseableHttpClient httpclient = HttpClientBuilder.create().build()) {

				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);
				HttpPost httpPost = getPost(entity, URL_TARGET);
				String responseBody = enviarRequisicao(httpclient, httpPost);
				return converterRespostaHttpCorretores(responseBody);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return corretores;
	}

	private static List<CorretorDTO> converterRespostaHttpCorretores(String responseBody) {
		List<CorretorDTO> corretores = new ArrayList<CorretorDTO>();
		Document doc = Jsoup.parse(responseBody);
		Elements trs = doc.getElementsByTag("tr");
		for (Element t : trs) {
			Elements dadosCorretor = t.getElementsByClass("ft10");
			if (dadosCorretor != null && dadosCorretor.size() > 5) {
				CorretorDTO corretor = new CorretorDTO();

				Element creci = dadosCorretor.get(1);
				corretor.setCreci(creci.text());
				System.out.println("creci" + creci.text());
				Element foto = dadosCorretor.get(2);
				if (foto != null) {

					if (foto.getElementById("div_Foto") != null) {

						if (foto.getElementById("div_Foto").getElementsByAttribute("src") != null) {

							if (foto.getElementById("div_Foto").getElementsByAttribute("src").get(0) != null) {
								if (foto.getElementById("div_Foto").getElementsByAttribute("src").get(0)
										.attr("src") != null) {
									corretor.setFoto(foto.getElementById("div_Foto").getElementsByAttribute("src")
											.get(0).attr("src").toString());
								}
							}
						}
					}

				}

				Element nome = dadosCorretor.get(4);
				if (nome != null) {
					corretor.setNome(nome.text());
				}
				Element situacao = dadosCorretor.get(6);
				if (situacao != null) {
					corretor.setSituacao(situacao.text());
				}

				Element telefone = dadosCorretor.get(7);
				if (telefone != null) {
					corretor.setTelefones(telefone.text());
				}

				corretores.add(corretor);

			}
		}

		return corretores;
	}

	private String enviarRequisicao(CloseableHttpClient httpclient, HttpPost httpPost)
			throws IOException, ClientProtocolException {
		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

			@Override
			public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {

				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity responseEntity = response.getEntity();
					return responseEntity != null ? EntityUtils.toString(responseEntity) : null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			}

		};
		String responseBody = httpclient.execute(httpPost, responseHandler);
		return responseBody;
	}

	private static HttpPost getPost(UrlEncodedFormEntity entity, String urlTarget) {
		HttpPost httpPost = new HttpPost(urlTarget);
		httpPost.setEntity(entity);
		System.out.println("Executing request " + httpPost.getRequestLine());
		HttpHost target = configHttpHost(httpPost);
		RequestConfig config = null;
		if (UTILIZAR_PROXY) {

			config = RequestConfig.custom().setProxy(target).build();
		} else {

			config = RequestConfig.custom().build();
		}

		httpPost.setConfig(config);
		return httpPost;
	}

	private static HttpHost configHttpHost(HttpPost httpPost) {

		httpPost.setHeader("Accept", "text/html, application/xhtml+xml, */*");
		String basicAuth = "Basic " + new String(new Base64().encode(USERNAME_PASSWORD.getBytes()));
		httpPost.setHeader("Authorization", basicAuth);
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		HttpHost target = new HttpHost(IP_PROXY, PORTA_PROXY);
		return target;
	}

	public static void main(String args[])
			throws ClientProtocolException, IOException, URISyntaxException, InfraException, NegocioException {

		IServicoCreciCorretor servico = new ServicoCreciExternoImp();
		CorretorDTO corretor = servico.pesquisarPorInscricao("23542");
		System.out.println(corretor.toString());

		List<CorretorDTO> corretores = servico.pesquisarPorCidadeBairro("sobradinho");
		for (CorretorDTO corretorDTO : corretores) {
			System.out.println(corretorDTO.toString());
		}

	}

	@Override
	public boolean validaPorInscricao(String inscricao) throws InfraException, NegocioException {
		CorretorDTO corretor = pesquisarPorInscricao(inscricao);
		if (corretor != null && corretor.getCreci() != null && !corretor.getCreci().isEmpty()) {

			if (corretor.getSituacao() != null && corretor.getSituacao().trim().equals("APTO AO EXERCÍCIO")) {
				return true;
			}
		}
		throw new NegocioException(Mensagem.ERRO_NEGOCIO, new RuntimeException(corretor.getSituacao()));
	}
}

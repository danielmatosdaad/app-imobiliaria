package com.br4dev.imob.infraestrutura.servico;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import com.br4dev.imob.infraestrutura.dto.EnvioDTO;
import com.br4dev.imob.infraestrutura.dto.MonitorDTO;
import com.br4dev.imob.infraestrutura.execao.InfraException;
import com.br4dev.imob.infraestrutura.execao.Mensagem;
import com.br4dev.imob.infraestrutura.execao.NegocioException;

@Stateless
public class MonitorInterceptor {

	@Inject
	private Logger log;

	@Inject
	private IServicoMonitor monitorService;

	@Inject
	private IServicoGeoLocalizacao geoLocalizacao;

	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {

		Object result = null;
		long tempoInicio = System.currentTimeMillis();
		try {
			if (context.getParameters()[0] != null && context.getParameters()[0] instanceof EnvioDTO) {

				EnvioDTO envioDTO = geoLocalizacao.buscarLocalizacao((EnvioDTO) context.getParameters()[0]);
				context.getParameters()[0] = envioDTO;
			}
			result = context.proceed();
			registraMonitoracao(context, tempoInicio, true, Mensagem.SUCESSO, null);
		} catch (NegocioException e) {
			log.info(e.getMessage());
			e.printStackTrace();
			registraMonitoracao(context, tempoInicio, false, Mensagem.ERRO_NEGOCIO, e.getMessage());
			throw e;
		} catch (InfraException e) {
			log.info(e.getMessage());
			e.printStackTrace();
			registraMonitoracao(context, tempoInicio, false, Mensagem.ERRO_INFRA, e.getMessage());
			throw e;
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
			registraMonitoracao(context, tempoInicio, false, Mensagem.ERRO_INFRA, e.getMessage());
			throw new InfraException(Mensagem.ERRO_INFRA, e);

		}

		return result;
	}

	public void registraMonitoracao(InvocationContext context, long tempoInicio, boolean sucesso, Mensagem mensagem,
			String pilhaerro) throws NegocioException, InfraException {

		long tempFim = System.currentTimeMillis();
		long tempo = (tempFim - tempoInicio);

		log.info("[MonitorDTO]");
		log.info("[Tempo de execucao] : " + ((tempo) / 1000d));

		if (context.getParameters()[0] instanceof EnvioDTO) {
			EnvioDTO envioDTO = (EnvioDTO) context.getParameters()[0];
			MonitorDTO monitorDTO = new MonitorDTO(envioDTO);
			monitorDTO.setDataHora(new Date());
			monitorDTO.setServico(context.getTarget().getClass().getName());
			monitorDTO.setMetodo(context.getMethod().getName());
			monitorDTO.setMensagem(mensagem.toString());
			monitorDTO.setPilhaErro(pilhaerro);
			monitorDTO.setTempoMilissegundos(tempo);
			monitorService.monitorar(monitorDTO);
			log.info("[MonitorDTO DATA]" + monitorDTO.getDataHora().toString());
			log.info("[MonitorDTO SERVICO]" + monitorDTO.getServico());
			log.info("[MonitorDTO METODO]" + monitorDTO.getMetodo());
			log.info("[MonitorDTO IP]" + monitorDTO.getIp());
			log.info("[MonitorDTO PORTA]" + monitorDTO.getPorta());
			log.info("[MonitorDTO PAIS]" + monitorDTO.getPais());
			log.info("[MonitorDTO COD PAIS]" + monitorDTO.getIsoCodePais());
			log.info("[MonitorDTO ESTADO]" + monitorDTO.getNomeEstado());
			log.info("[MonitorDTO CO ESTADO]" + monitorDTO.getIsoCodeEstado());
			log.info("[MonitorDTO CIDADE]" + monitorDTO.getNomeCidade());
			log.info("[MonitorDTO COD CIDADE]" + monitorDTO.getCodigoPostalCidade());
			log.info("[MonitorDTO LATITUDE]" + monitorDTO.getLatitude());
			log.info("[MonitorDTO LONGITUDE]" + monitorDTO.getLongitude());
			log.info("[MonitorDTO BROWSER]" + monitorDTO.getBrowser());
			log.info("[MonitorDTO SISTEMA OPERACIONAL]" + monitorDTO.getSistemaOperacional());
			log.info("[MonitorDTO CATEGORIA DISPOSITIVO]" + monitorDTO.getCategoriaDispositivo());
			log.info("[MonitorDTO USUARIO AGENTE]" + monitorDTO.getAgenteUsuario());
			log.info("[MonitorDTO DISPOSITIVO]" + monitorDTO.getDispositivo());
			log.info("[MonitorDTO TIME ZONE]" + monitorDTO.getTimeZone());
			log.info("[MonitorDTO MENSAGEM]" + monitorDTO.getMensagem());
			log.info("[MonitorDTO ERRO]" + monitorDTO.getPilhaErro());
		}

	}

}

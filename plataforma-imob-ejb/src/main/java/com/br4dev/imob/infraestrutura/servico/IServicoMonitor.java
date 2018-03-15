package com.br4dev.imob.infraestrutura.servico;

import com.br4dev.imob.infraestrutura.dto.MonitorDTO;
import com.br4dev.imob.negocio.servico.IServicoDAO;

public interface IServicoMonitor extends IServicoDAO<MonitorDTO>{

	public void monitorar(MonitorDTO monitor);
}

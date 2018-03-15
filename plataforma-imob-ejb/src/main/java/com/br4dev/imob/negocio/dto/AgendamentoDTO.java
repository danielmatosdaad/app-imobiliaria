package com.br4dev.imob.negocio.dto;

import java.util.Date;
import com.br4dev.imob.infraestrutura.dto.EnvioDTO;

public class AgendamentoDTO extends EnvioDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private TipoAgendamentoDTO tipoAgendamento;

	private StatusAgendamentoDTO statusAgendamento;

	private PessoaDTO pessoa;

	private PessoaDTO pessoaAgendada;

	private String data;

	private String hora;

	private String observacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoAgendamentoDTO getTipoAgendamento() {
		return tipoAgendamento;
	}

	public void setTipoAgendamento(TipoAgendamentoDTO tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento;
	}

	public StatusAgendamentoDTO getStatusAgendamento() {
		return statusAgendamento;
	}

	public void setStatusAgendamento(StatusAgendamentoDTO statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public PessoaDTO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaDTO pessoa) {
		this.pessoa = pessoa;
	}

	public PessoaDTO getPessoaAgendada() {
		return pessoaAgendada;
	}

	public void setPessoaAgendada(PessoaDTO pessoaAgendada) {
		this.pessoaAgendada = pessoaAgendada;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}

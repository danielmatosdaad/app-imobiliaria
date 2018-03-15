package com.br4dev.imob.negocio.entidade;

import java.sql.Time;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Daniel.Matos
 */
@Entity
public class Agendamento implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.ORDINAL)
	private TipoAgendamento tipoAgendamento;

	@Enumerated(EnumType.ORDINAL)
	private StatusAgendamento statusAgendamento;

	@ManyToOne
	private Pessoa pessoa;

	@ManyToOne
	private Pessoa pessoaAgendada;

	private Date data;

	private Time hora;

	private String observacao;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoAgendamento getTipoAgendamento() {
		return this.tipoAgendamento;
	}

	public void setTipoAgendamento(TipoAgendamento tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public StatusAgendamento getStatusAgendamento() {
		return statusAgendamento;
	}

	public void setStatusAgendamento(StatusAgendamento statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}

	public Pessoa getPessoaAgendada() {
		return pessoaAgendada;
	}

	public void setPessoaAgendada(Pessoa pessoaAgendada) {
		this.pessoaAgendada = pessoaAgendada;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}

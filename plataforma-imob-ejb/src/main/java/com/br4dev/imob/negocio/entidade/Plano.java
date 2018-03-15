package com.br4dev.imob.negocio.entidade;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Daniel.Matos
 */
@Entity
public class Plano implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private float valor;

	private String descriacao;

	@Enumerated(EnumType.ORDINAL)
	private TipoPlano tipo;

	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	private Date dataTermino;

	@Enumerated(EnumType.ORDINAL)
	private StatusPlano statusPlano;

	@OneToMany(mappedBy = "plano")
	private List<Pessoa> pessoas;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getDescriacao() {
		return descriacao;
	}

	public void setDescriacao(String descriacao) {
		this.descriacao = descriacao;
	}

	public TipoPlano getTipo() {
		return tipo;
	}

	public void setTipo(TipoPlano tipo) {
		this.tipo = tipo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public StatusPlano getStatusPlano() {
		return statusPlano;
	}

	public void setStatusPlano(StatusPlano statusPlano) {
		this.statusPlano = statusPlano;
	}
	
	

}

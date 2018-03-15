package com.br4dev.imob.negocio.entidade;

import java.util.Date;

import javax.persistence.Entity;
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
public class ImovelFavorito implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Imovel imovel;

	@ManyToOne
	private Pessoa pessoa;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusaoFavorito;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}

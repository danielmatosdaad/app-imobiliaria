package com.br4dev.imob.negocio.entidade;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Daniel.Matos
 */
@Entity
public class Vistoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy="vistoria")
	private List<AnexoVistoria> anexosVistorias;

	@ManyToOne
	private Imovel imovel;

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

	public List<AnexoVistoria> getAnexosVistorias() {
		return anexosVistorias;
	}

	public void setAnexosVistorias(List<AnexoVistoria> anexosVistorias) {
		this.anexosVistorias = anexosVistorias;
	}

}

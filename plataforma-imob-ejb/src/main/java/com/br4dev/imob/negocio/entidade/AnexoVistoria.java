package com.br4dev.imob.negocio.entidade;

import java.util.Date;

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
public class AnexoVistoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String url;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusao;

	@Enumerated(EnumType.ORDINAL)
	private TipoDocumento tipoDocumento;

	@ManyToOne
	private Vistoria vistoria;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vistoria getVistoria() {
		return this.vistoria;
	}

	public void setVistoria(Vistoria vistoria) {
		this.vistoria = vistoria;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}

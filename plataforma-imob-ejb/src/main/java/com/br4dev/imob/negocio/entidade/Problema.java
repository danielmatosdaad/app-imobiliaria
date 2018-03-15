package com.br4dev.imob.negocio.entidade;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Daniel.Matos
 */
@Entity
public class Problema implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Pessoa emissor;

	@ManyToOne
	private Pessoa destinatario;

	@Lob
	private String descricao;

	@OneToMany(mappedBy = "problema")
	private List<AnexoProblema> anexosProblemas;

	@Enumerated(EnumType.ORDINAL)
	private StatusProblema statusProblema;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getEmissor() {
		return emissor;
	}

	public void setEmissor(Pessoa emissor) {
		this.emissor = emissor;
	}

	public Pessoa getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Pessoa destinatario) {
		this.destinatario = destinatario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<AnexoProblema> getAnexosProblemas() {
		return anexosProblemas;
	}

	public void setAnexosProblemas(List<AnexoProblema> anexosProblemas) {
		this.anexosProblemas = anexosProblemas;
	}

	public StatusProblema getStatusProblema() {
		return statusProblema;
	}

	public void setStatusProblema(StatusProblema statusProblema) {
		this.statusProblema = statusProblema;
	}

}

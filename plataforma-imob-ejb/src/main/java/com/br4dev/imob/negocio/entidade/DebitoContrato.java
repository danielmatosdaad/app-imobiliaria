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
public class DebitoContrato implements Entidade{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contrato contrato;

    @OneToMany(mappedBy="debitosContrato")
    private List<Transacao> transacoes;
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contrato getContrato() {
        return this.contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

}

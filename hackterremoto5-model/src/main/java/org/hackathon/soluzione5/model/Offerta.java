package org.hackathon.soluzione5.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Geometry;


/**
 * ArchitecturalBackground entity. @author MyEclipse Persistence Tools
 */
@Entity
public class Offerta  implements java.io.Serializable {


    // Fields    

     private Long entityId;
     private Geometry geom;
     private byte[] foto;
     private String nomeOfferente;
     private String cognomeOfferente;



   
    public Offerta() {
		super();
	}

	public Offerta(Long entityId, Geometry geom, byte[] foto,
			String nomeOfferente, String cognomeOfferente) {
		super();
		this.entityId = entityId;
		this.geom = geom;
		this.foto = foto;
		this.nomeOfferente = nomeOfferente;
		this.cognomeOfferente = cognomeOfferente;
	}

	// Property accessors
    @Id 
    @GeneratedValue
    @Column(name="entity_id", unique=true, nullable=false)
    public Long getEntityId() {
        return this.entityId;
    }
    
    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }
    
    @Column(name="geom")
    @Type(type="org.hibernatespatial.GeometryUserType")
    public Geometry getGeom() {
        return this.geom;
    }
    
    public void setGeom(Geometry geom) {
        this.geom = geom;
    }

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getNomeOfferente() {
		return nomeOfferente;
	}

	public void setNomeOfferente(String nomeOfferente) {
		this.nomeOfferente = nomeOfferente;
	}

	public String getCognomeOfferente() {
		return cognomeOfferente;
	}

	public void setCognomeOfferente(String cognomeOfferente) {
		this.cognomeOfferente = cognomeOfferente;
	}
    








}
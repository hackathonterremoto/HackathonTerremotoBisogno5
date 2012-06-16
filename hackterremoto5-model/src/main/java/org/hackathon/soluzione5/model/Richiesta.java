package org.hackathon.soluzione5.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hackathon.soluzione5.model.converter.PointJsonSerializer;
import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Point;

@Entity
public class Richiesta implements java.io.Serializable {

	// Fields

	private Long entityId;
	private Point posizione;
	private Integer raggio;

	// APPARTAMENTO, STANZA, POSTO LETTO, CAMPER/ROULOTTE, TENDA
	private String tipologia;
	private Integer postiLetto;
	private Date disponibileDa;
	private Date disponibileFino;

	// CUCINA, LAVANDERIA, ANIMALI DOMESTICI, FUMATORI
	private String servizi;
	private Boolean disabili;
	private String note;
	private String indirizzo;

	private User utente;

	public Richiesta() {
		super();
	}

	public Richiesta(Point posizione, Integer raggio, String indirizzo, String tipologia,
			Integer postiLetto, Date disponibileDa, Date disponibileFino,
			String servizi, Boolean disabili, String note, User utente) {
		super();
		this.posizione = posizione;
		this.indirizzo = indirizzo;
		this.raggio = raggio;
		this.tipologia = tipologia;
		this.postiLetto = postiLetto;
		this.disponibileDa = disponibileDa;
		this.disponibileFino = disponibileFino;
		this.servizi = servizi;
		this.disabili = disabili;
		this.note = note;
		this.utente = utente;
	}

	// Property accessors
	@Id
	@GeneratedValue
	public Long getEntityId() {
		return this.entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	@Column
	@Type(type = "org.hibernatespatial.GeometryUserType")
	@NotNull
	@JsonSerialize(using=PointJsonSerializer.class)
	public Point getPosizione() {
		return this.posizione;
	}

	public void setPosizione(Point posizione) {
		this.posizione = posizione;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public Integer getPostiLetto() {
		return postiLetto;
	}

	public void setPostiLetto(Integer postiLetto) {
		this.postiLetto = postiLetto;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDisponibileDa() {
		return disponibileDa;
	}

	public void setDisponibileDa(Date disponibileDa) {
		this.disponibileDa = disponibileDa;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDisponibileFino() {
		return disponibileFino;
	}

	public void setDisponibileFino(Date disponibileFino) {
		this.disponibileFino = disponibileFino;
	}

	public String getServizi() {
		return servizi;
	}

	public void setServizi(String servizi) {
		this.servizi = servizi;
	}

	public Boolean getDisabili() {
		return disabili;
	}

	public void setDisabili(Boolean disabili) {
		this.disabili = disabili;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	public User getUtente() {
		return utente;
	}

	public void setUtente(User utente) {
		this.utente = utente;
	}

	public Integer getRaggio() {
		return raggio;
	}

	public void setRaggio(Integer raggio) {
		this.raggio = raggio;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

}
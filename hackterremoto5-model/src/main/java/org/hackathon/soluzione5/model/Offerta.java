package org.hackathon.soluzione5.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Point;

@Entity
public class Offerta implements java.io.Serializable {

	// Fields

	private Long entityId;
	private Point location;
	private byte[] foto1;
	private byte[] foto2;
	private byte[] foto3;

	// APPARTAMENTO, STANZA, POSTO LETTO, CAMPER/ROULOTTE, TENDA
	private String tipologia;
	private Integer postiLetto;
	private Date disponibileDa;
	private Date disponibileFino;

	// CUCINA, LAVANDERIA, ANIMALI DOMESTICI, FUMATORI
	private String servizi;
	private Boolean disabili;
	private String indirizzo;
	private Boolean confermata;
	
	
	private String note;

	private User utente;

	public Offerta() {
		super();
		confermata = false;
		foto1 = new byte[]{};
		foto2 = new byte[]{};
		foto3 = new byte[]{};
	}

	public Offerta(Point location, String indirizzo, byte[] foto1, byte[] foto2, byte[] foto3,
			String tipologia, Integer postiLetto, Date disponibileDa,
			Date disponibileFino, String servizi, Boolean disabili, String note) {
		super();
		this.indirizzo = indirizzo;
		this.location = location;
		this.foto1 = foto1;
		this.foto2 = foto2;
		this.foto3 = foto3;
		this.tipologia = tipologia;
		this.postiLetto = postiLetto;
		this.disponibileDa = disponibileDa;
		this.disponibileFino = disponibileFino;
		this.servizi = servizi;
		this.disabili = disabili;
		this.note = note;
	}

	@Lob
	@Column(length = 1024*1024*2)
	public byte[] getFoto1() {
		return foto1;
	}

	public void setFoto1(byte[] foto1) {
		this.foto1 = foto1;
	}

	@Lob
	@Column(length = 1024*1024*2)
	public byte[] getFoto2() {
		return foto2;
	}

	public void setFoto2(byte[] foto2) {
		this.foto2 = foto2;
	}

	@Lob
	@Column(length = 1024*1024*2)
	public byte[] getFoto3() {
		return foto3;
	}

	public void setFoto3(byte[] foto3) {
		this.foto3 = foto3;
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
	public Point getLocation() {
		return this.location;
	}

	public void setLocation(Point location) {
		this.location = location;
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

	public Boolean getConfermata() {
		return confermata;
	}

	public void setConfermata(Boolean confermata) {
		this.confermata = confermata;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

}
package org.hackathon.soluzione5.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable{
	


	private Long entityId;
	private String nome;
	private String cognome;
	private String email;

	private String telefono;
	private String infoContatto;
	
	private List<Offerta> offerte;
	private List<Richiesta> richieste;
	
	
	public User() {
		super();
		richieste = new ArrayList<Richiesta>();
		offerte = new ArrayList<Offerta>();
	}
	


	public User(String nome, String cognome, String email, String telefono,
			String infoContatto, List<Offerta> offerte,
			List<Richiesta> richieste) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.telefono = telefono;
		this.infoContatto = infoContatto;
		this.offerte = offerte;
		this.richieste = richieste;
		
		if (richieste == null)
			richieste = new ArrayList<Richiesta>();
		
		if (offerte == null)
			offerte = new ArrayList<Offerta>();
	}

	public Long getEntityId() {
		return entityId;
	}
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	
	@OneToMany(fetch = FetchType.EAGER, cascade =
	    {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy="offerte", orphanRemoval=true)
	public List<Offerta> getOfferte() {
		return offerte;
	}
	public void setOfferte(List<Offerta> offerte) {
		this.offerte = offerte;
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getInfoContatto() {
		return infoContatto;
	}

	public void setInfoContatto(String infoContatto) {
		this.infoContatto = infoContatto;
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public String getCognome() {
		return cognome;
	}




	public void setCognome(String cognome) {
		this.cognome = cognome;
	}




	@OneToMany(fetch = FetchType.EAGER, cascade =
	    {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy="richieste", orphanRemoval=true)
	public List<Richiesta> getRichieste() {
		return richieste;
	}

	public void setRichieste(List<Richiesta> richieste) {
		this.richieste = richieste;
	}
	
	
	
}

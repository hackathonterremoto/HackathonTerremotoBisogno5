package org.hackathon.soluzione5.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable{
	


	private Long entityId;
	private String nome;
	private String cognome;
	private String email;

	private String telefono;
	private String infoContatto;
	
	private Set<Offerta> offerte;
	private Set<Richiesta> richieste;
	
	
	public User() {
		super();
		richieste = new HashSet<Richiesta>();
		offerte = new HashSet<Offerta>();
	}
	


	public User(String nome, String cognome, String email, String telefono,
			String infoContatto, Set<Offerta> offerte,
			Set<Richiesta> richieste) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.telefono = telefono;
		this.infoContatto = infoContatto;
		this.offerte = offerte;
		this.richieste = richieste;
		
		if (richieste == null)
			richieste = new HashSet<Richiesta>();
		
		if (offerte == null)
			offerte = new HashSet<Offerta>();
	}

	@Id
	@GeneratedValue
	public Long getEntityId() {
		return entityId;
	}
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	
	@OneToMany(fetch = FetchType.EAGER, cascade =
	    {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy="utente", orphanRemoval=true)
	public Set<Offerta> getOfferte() {
		return offerte;
	}
	public void setOfferte(Set<Offerta> offerte) {
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
	    {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy="utente", orphanRemoval=true)
	public Set<Richiesta> getRichieste() {
		return richieste;
	}

	public void setRichieste(Set<Richiesta> richieste) {
		this.richieste = richieste;
	}
	
	
	
}

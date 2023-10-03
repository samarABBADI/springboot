package com.cni.centre.demosante.entities;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Table (name = "medicament")
public class Medicament {

	

	@Id
	@Column(name = "ref")
	private String ref;
	
	@Column(name = "intitile")
	private String intitile;
	@JsonIgnore
	@Column(name = "description")
	private String description;
	@Column(name = "nbrecomprimes")
	private Integer nbrecomprimes;
	

	
	//@ManyToOne (optional = true,fetch = FetchType.LAZY)
	 @ManyToOne (optional = true,fetch = FetchType.LAZY)
	 @JoinColumn(name = "sp_id", referencedColumnName = "sp_id", nullable = true, insertable=true, updatable=true) // Optional: Permet de personnaliser la colonne de jointure
	 
	  private Specialite specialite;

	
	public Medicament() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Medicament(String ref, String intitile, String description, Integer nbrecomprimes, Specialite specialite) {
		super();
		this.ref = ref;
		this.intitile = intitile;
		this.description = description;
		this.nbrecomprimes = nbrecomprimes;
		this.specialite = specialite;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getRef() {
		return ref;
	}


	public void setRef(String ref) {
		this.ref = ref;
	}


	public String getIntitile() {
		return intitile;
	}


	public void setIntitile(String intitile) {
		this.intitile = intitile;
	}


	public Integer getNbrecomprimes() {
		return nbrecomprimes;
	}


	public void setNbrecomprimes(Integer nbrecomprimes) {
		this.nbrecomprimes = nbrecomprimes;
	}


	public Specialite getSpecialite() {
		return specialite;
	}


	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	
	


	
}

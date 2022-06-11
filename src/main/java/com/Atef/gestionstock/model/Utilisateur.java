package com.Atef.gestionstock.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Utilisateur")
public class Utilisateur extends AbstractEntity{
	
	@Column(name ="nom")
	private String nom;
	
	@Column(name ="prenom")
	private String prenom;
	
	@Column(name ="email")
	private String email;
	
	@Column(name="datedenaissance")
	private Instant dateDeNaissance ;

	@Column(name="motdepasse")
	private String motDePasse ;
	
	@Embedded
	private Adresse adresse ;
	
	
	@Column(name="photo")
	private String photo;
	
	@ManyToOne
	@JoinColumn(name="identreprise")
	private Entreprise entreprise;
	
	@OneToMany(mappedBy = "utilisateur")
	private List<Role> role ;
	
	
	

}


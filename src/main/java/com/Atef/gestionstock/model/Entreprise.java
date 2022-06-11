package com.Atef.gestionstock.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Entreprise")
public class Entreprise extends AbstractEntity {
	
	@Column(name="nom")
	private String nom ;
	
	@Column(name="description")
	private String description;
	
	@Embedded
	private Adresse adresse;
	
	@Column(name="codefiscal")
	private String codeFiscal;
	
	@Column(name="photo")
	private String photo;
	
	@Column(name="email")
	private String email;
	
	@Column(name="numtel")
	private String numTel;
	
	@Column(name ="siteweb")
	private String steWeb;
	
	@OneToMany(mappedBy = "entreprise")
	private List<Utilisateur> utilisateurs;
	
	


}

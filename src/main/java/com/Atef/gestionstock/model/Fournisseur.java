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
@Table(name="Fournisseur")
public class Fournisseur extends AbstractEntity {

	
	@Column(name ="nom")
	private String nom ;
	
	@Column(name ="prenom")
	private String prenom;
	
	@Embedded
	private Adresse addresse;
	
	
	@Column(name="identreprise")
	private Integer idEntreprise;
	
	
	@Column(name ="photo")
	private String photo ;
	
	@Column(name ="mail")
	private String mail;
	
	@Column(name ="numtel")
	private String numTel;
	
	@OneToMany(mappedBy = "fournisseur")
	private List<CommandeFournisseur> commandeFournisseurs;
}

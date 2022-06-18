package com.Atef.gestionstock.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
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
@Table(name="CommandeFournisseur")
public class CommandeFournisseur extends AbstractEntity {

	@Column(name="code")
	private String code;
	
	@Column(name ="datecommande")
	private Instant datecommande ;
	
	
	@Column(name="identreprise")
	private Integer idEntreprise;

	@Column(name = "etatCommande")
	private EtatCommande etatCommande;
	
	@ManyToOne
	@JoinColumn(name ="idfournisseur")
	private Fournisseur fournisseur;
	
	@OneToMany(mappedBy = "commandeFournisseur")
	private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;
}

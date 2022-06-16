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
@Table(name="CommandeClient")
public class CommandeClient extends AbstractEntity {
	
	@Column(name="code")
	private String code ;
	
	@Column(name="dateCommande")
	private Instant dateCommande ;

	@Column(name = "etatCommande")
	private EtatCommande etatCommande;
	
	
	@Column(name="identreprise")
	private Integer idEntreprise;
	
	
	@ManyToOne
	@JoinColumn(name ="idclient ")
	private Client client ;
	
	@OneToMany(mappedBy = "commandeClient")
	private List<LigneCommandeClient> ligneCommandeClients ;

}

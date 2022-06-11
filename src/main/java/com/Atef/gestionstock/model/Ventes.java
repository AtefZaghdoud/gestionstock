package com.Atef.gestionstock.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
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
@Table(name="Ventes ")
public class Ventes extends AbstractEntity{
	
	@Column(name="code")
	private String code;
	
	@Column(name="identreprise")
	private Integer idEntreprise;
	
	@Column(name="datevente")
	private Instant dateVente;
	
	@Column(name="commentaire")
	private String commentaire ;
	
	@OneToMany(mappedBy = "vente")
	private List<LigneVente> ligneVentes;

}

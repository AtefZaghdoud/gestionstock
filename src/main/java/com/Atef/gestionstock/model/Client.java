package com.Atef.gestionstock.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true )
@Entity
@Table(name="Client")
public class Client extends AbstractEntity{

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
	
	@OneToMany(mappedBy = "client")
	private List<CommandeClient> commandeClients ;
	
	
}

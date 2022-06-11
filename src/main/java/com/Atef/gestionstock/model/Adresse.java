package com.Atef.gestionstock.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true )
@Embeddable
public class Adresse implements Serializable {
	
	@Column(name="adresse1")
	private String adresse1;
	
	@Column(name="adresse2")
	private String adresse2;
	
	@Column(name="ville")
	private String ville;
	
	@Column(name="codePostal")
	private String codePostal;
	
	@Column(name="pays")
	private String pays;
	
	

}

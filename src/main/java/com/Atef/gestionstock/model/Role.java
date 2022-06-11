package com.Atef.gestionstock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Role")
public class Role extends AbstractEntity{

	@Column(name="rolename")
	private String roleName;

	@Column(name="identreprise")
	private Integer idEntreprise;
	
	@ManyToOne
	@JoinColumn(name="idutilisateur")
	private Utilisateur utilisateur;

}

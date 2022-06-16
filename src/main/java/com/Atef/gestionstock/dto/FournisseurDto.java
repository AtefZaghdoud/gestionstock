package com.Atef.gestionstock.dto;

import java.util.List;

import com.Atef.gestionstock.model.Fournisseur;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class FournisseurDto {

	private Integer id ;

	private String nom ;
	
	private String prenom;
	
	private AdresseDto addresse;
	
	private String photo ;
	
	private String mail;
	
	private Integer idEntreprise;
	
	private String numTel;

	@JsonIgnore
	private List<CommandeFournisseurDto> commandeFournisseurs;
	
public static FournisseurDto fromEntity(Fournisseur fournisseur) {
		
		if (fournisseur ==null) {
			return null;
		}
		return FournisseurDto.builder()
				.id(fournisseur.getId())
				.nom(fournisseur.getNom())
				.prenom(fournisseur.getPrenom())
				.addresse(AdresseDto.fromEntity(fournisseur.getAddresse()))
				//.commandeFournisseurs(CommandeFournisseurDto.fromEntity(fournisseur.getCommandeFournisseurs()))
				.mail(fournisseur.getMail())
				.numTel(fournisseur.getNumTel())
				.photo(fournisseur.getPhoto())
				.idEntreprise(fournisseur.getIdEntreprise())
				.build();
		
	}
	
	public static Fournisseur toEntity(FournisseurDto fournisseurDto) {
		if (fournisseurDto == null ) {
			return null ;
		}
		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setId(fournisseurDto.getId());
		fournisseur.setAddresse(AdresseDto.toEntity(fournisseurDto.getAddresse()));
		fournisseur.setNom(fournisseurDto.getNom());
		fournisseur.setPrenom(fournisseurDto.getPrenom());
		fournisseur.setNumTel(fournisseurDto.getNumTel());
		fournisseur.setPhoto(fournisseurDto.getPhoto());
		fournisseur.setIdEntreprise(fournisseurDto.getIdEntreprise());
		//fournisseur.setCommandeFournisseurs(CommandeFournisseurDto.toEntity(fournisseurDto.getCommandeFournisseurs()));
		return fournisseur ;
	}
	
	

}

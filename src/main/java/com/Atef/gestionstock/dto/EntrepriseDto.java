package com.Atef.gestionstock.dto;

import java.util.List;

import com.Atef.gestionstock.model.Entreprise;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class EntrepriseDto {

	private Integer id ;

	private String nom ;
	
	private String description;
	
	private AdresseDto adresse;
	
	private String codeFiscal;
	
	private String photo;
	
	private String email;
	
	private String numTel;
	
	private String steWeb;
	
	private List<UtilisateurDto> utilisateurs;
	
public static EntrepriseDto fromEntity(Entreprise entreprise) {
		
		if (entreprise ==null) {
			return null;
		}
		return EntrepriseDto.builder()
				.id(entreprise.getId())
				.nom(entreprise.getNom())
				.adresse(AdresseDto.fromEntity(entreprise.getAdresse()))
				.codeFiscal(entreprise.getCodeFiscal())
				.numTel(entreprise.getNumTel())
				.email(entreprise.getEmail())
				.photo(entreprise.getPhoto())
				.steWeb(entreprise.getSteWeb())
				.description(entreprise.getDescription())
				//.utilisateurs(entreprise.getUtilisateurs())
				.build();
		
	}
	
	public static Entreprise toEntity(EntrepriseDto entrepriseDto) {
		if (entrepriseDto == null ) {
			return null ;
		}
		Entreprise entreprise = new Entreprise();
		entreprise.setAdresse(AdresseDto.toEntity(entrepriseDto.getAdresse()));
		entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
		entreprise.setDescription(entrepriseDto.getDescription());
		entreprise.setId(entrepriseDto.getId());
		entreprise.setEmail(entrepriseDto.getEmail());
		entreprise.setNom(entrepriseDto.getNom());
		entreprise.setNumTel(entrepriseDto.getNumTel());
		entreprise.setPhoto(entrepriseDto.getPhoto());
		entreprise.setSteWeb(entrepriseDto.getSteWeb());
		//entreprise.setUtilisateurs(entrepriseDto.getUtilisateurs());
		
		return entreprise ;
	}
	
}

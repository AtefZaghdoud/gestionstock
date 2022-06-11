package com.Atef.gestionstock.dto;

import java.time.Instant;
import java.util.List;

import com.Atef.gestionstock.model.Utilisateur;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UtilisateurDto {

	private Integer id ;

	private String nom;
	
	private String prenom;
	
	private String email;
	
	private Instant dateDeNaissance ;

	private String motDePasse ;
	
	private AdresseDto adresse ;
	
	private String photo;
	
	private EntrepriseDto entreprise;
	
	private List<RoleDto> roles ;
	
	public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
		
		if (utilisateur ==null) {
			return null;
		}
		return UtilisateurDto.builder()
				.id(utilisateur.getId())
				.adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
				.dateDeNaissance(utilisateur.getDateDeNaissance())
				.email(utilisateur.getEmail())
				.entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
				.motDePasse(utilisateur.getMotDePasse())
				.nom(utilisateur.getNom())
				.prenom(utilisateur.getPrenom())
				.photo(utilisateur.getPhoto())
			//	.roles(RoleDto.fromEntity(utilisateur.getRole()))
				.build();
		
	}
	
	public static Utilisateur toEntity(UtilisateurDto utilisateurDto) {
		if (utilisateurDto == null ) {
			return null ;
		}
		Utilisateur utilisateur = new Utilisateur() ;
		utilisateur.setId(utilisateurDto.getId());
		utilisateur.setNom(utilisateurDto.getNom());
		utilisateur.setPrenom(utilisateurDto.getPrenom());
		utilisateur.setPhoto(utilisateurDto.getPhoto());
		utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
		utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresse()));
		utilisateur.setEmail(utilisateurDto.getEmail());
		utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
		utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntreprise()));
	//	utilisateur.setRole(RoleDto.toEntity(utilisateurDto.getRoles()));
		return utilisateur ;
	}
	
}

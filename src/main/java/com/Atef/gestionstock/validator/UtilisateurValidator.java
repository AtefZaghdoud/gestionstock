package com.Atef.gestionstock.validator;

import java.util.ArrayList;
import java.util.List;

import com.Atef.gestionstock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

public class UtilisateurValidator {
	public static List<String> validate(UtilisateurDto utilisateurDto){
		List<String> errors = new ArrayList<>();
		
		if (utilisateurDto==null) {
			errors.add("Veuillez remplir le nom de l'utilisateur ");
			errors.add("Veuillez remplir le prenom de l'utilisateur ");
			errors.add("Veuillez remplir l'email de l'utilisateur ");
			errors.add("Veuillez remplir le mot de passe de l'utilisateur ");
			errors.add("Veuillez rensigner la date de naissance de l'utilisateur ");
			errors.addAll(AdresseValidator.validate(utilisateurDto.getAdresse()));
			return errors;

		}
		if (!StringUtils.hasLength(utilisateurDto.getNom())) {
			errors.add("Veuillez remplir le nom de l'utilisateur ");
			
		}
		if ( !StringUtils.hasLength(utilisateurDto.getPrenom())) {
			errors.add("Veuillez remplir le prenom de l'utilisateur ");
			
		}
		if (!StringUtils.hasLength(utilisateurDto.getEmail())) {
			errors.add("Veuillez remplir l'email de l'utilisateur ");
			
		}
		if ( !StringUtils.hasLength(utilisateurDto.getMotDePasse())) {
			errors.add("Veuillez remplir le mot de passe de l'utilisateur ");
			
		}
		if (utilisateurDto.getDateDeNaissance()==null) {
			errors.add("Veuillez rensigner la date de naissance de l'utilisateur ");

		}
//		if ( utilisateurDto.getAdresse()==null) {
//			errors.add("Veuillez remplir l'adresse de l'utilisateur ");
//
//		}else
//		{
//			if (utilisateurDto.getAdresse().getAdresse1()==null) {
//				errors.add("le champs Adresse 1 est obligatoire ");
//
//			}
//			if ( utilisateurDto.getAdresse().getVille()==null) {
//				errors.add("le champs ville est obligatoire ");
//
//			}
//			if ( utilisateurDto.getAdresse().getPays()==null) {
//				errors.add("le champs pays est obligatoire ");
//
//			}
//			if ( utilisateurDto.getAdresse().getCodePostal()==null) {
//				errors.add("le champs code postal est obligatoire ");
//
//			}
//
//		}
		errors.addAll(AdresseValidator.validate(utilisateurDto.getAdresse()));


		return errors;
	}
}

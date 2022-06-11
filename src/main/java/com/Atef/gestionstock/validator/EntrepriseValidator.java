package com.Atef.gestionstock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.Atef.gestionstock.dto.EntrepriseDto;

public class EntrepriseValidator {
	
	public static List<String> validate(EntrepriseDto entrepriseDto){
		List<String> errors = new ArrayList<>();
		
		if (entrepriseDto==null) {
			errors.add("Veuillez renseigner le Nom du l'entreprise ");
			errors.add("Veuillez renseigner le mail du l'entreprise ");
			errors.add("Veuillez renseigner le Num Telephone du l'entreprise ");
			errors.add("Veuillez renseigne le code fiscal du l'entreprise");
			errors.add("Veuillez remplir l'adresse de l'entreprise ");

			return errors;
		}
		if (!StringUtils.hasLength(entrepriseDto.getNom())) {
			errors.add("Veuillez renseigner le Nom du l'entreprise ");
		}
		if (!StringUtils.hasLength(entrepriseDto.getEmail())) {
			errors.add("Veuillez renseigner le mail du l'entreprise ");
		}
		if (!StringUtils.hasLength(entrepriseDto.getNumTel())) {
			errors.add("Veuillez renseigner le Num Telephone du l'entreprise ");
		}
		if (!StringUtils.hasLength(entrepriseDto.getCodeFiscal())) {
			errors.add("Veuillez renseigne le code fiscal du l'entreprise");
		}
		if (!StringUtils.hasLength(entrepriseDto.getNumTel())) {
			errors.add("Veuillez renseigner le Num Telephone du l'entreprise ");
		}
		if ( entrepriseDto.getAdresse()==null) {
			errors.add("Veuillez remplir l'adresse de l'entreprise ");
			
		}else
		{
			if (entrepriseDto.getAdresse().getAdresse1()==null) {
				errors.add("le champs Adresse 1 est obligatoire ");
				
			}
			if ( entrepriseDto.getAdresse().getVille()==null) {
				errors.add("le champs ville est obligatoire ");
				
			}
			if ( entrepriseDto.getAdresse().getPays()==null) {
				errors.add("le champs pays est obligatoire ");
				
			}
			if ( entrepriseDto.getAdresse().getCodePostal()==null) {
				errors.add("le champs code postal est obligatoire ");
				
			}
		}
		return errors;
	}

}

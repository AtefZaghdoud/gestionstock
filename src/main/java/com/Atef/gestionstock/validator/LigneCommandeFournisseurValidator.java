package com.Atef.gestionstock.validator;

import java.util.ArrayList;
import java.util.List;

import com.Atef.gestionstock.dto.LigneCommandeFournisseurDto;

public class LigneCommandeFournisseurValidator {
	public static List<String> validate(LigneCommandeFournisseurDto ligneCommandeFournisseurDto){
		List<String> errors = new ArrayList<>();
		
		if (ligneCommandeFournisseurDto==null) {
			errors.add("Veuillez renseigner le prix unitaire de la ligne ");
			errors.add("Veuillez renseigner l'article correspond a la ligne ");
			errors.add("Veuillez renseigner la commande client correspond a la ligne ");
			errors.add("Veuillez renseigner la quantite de la ligne ");

			return errors;

		}
		if (ligneCommandeFournisseurDto.getPrixUnitaire()==null) {
			errors.add("Veuillez renseigner le prix unitaire de la ligne ");
		}
		if (ligneCommandeFournisseurDto.getArticle()==null) {
			errors.add("Veuillez renseigner l'article correspond a la ligne ");
		}
		if (ligneCommandeFournisseurDto.getCommandeFournisseur()==null) {
			errors.add("Veuillez renseigner la commande client correspond a la ligne ");
		}
		if (ligneCommandeFournisseurDto.getQuantite()==null) {
			errors.add("Veuillez renseigner la quantite de la ligne ");
		}
		return errors;
	}
}

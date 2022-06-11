package com.Atef.gestionstock.validator;

import java.util.ArrayList;
import java.util.List;


import com.Atef.gestionstock.dto.LigneCommandeClientDto;

public class LigneCommandeClientValidator {
	public static List<String> validate(LigneCommandeClientDto ligneCommandeClientDto){
		List<String> errors = new ArrayList<>();
		
		if (ligneCommandeClientDto==null) {
			errors.add("Veuillez renseigner le prix unitaire de la ligne ");
			errors.add("Veuillez renseigner l'article correspond a la ligne ");
			errors.add("Veuillez renseigner la commande client correspond a la ligne ");
			errors.add("Veuillez renseigner la quantite de la ligne ");

			return errors;

		}
		if (ligneCommandeClientDto.getPrixUnitaire()==null) {
			errors.add("Veuillez renseigner le prix unitaire de la ligne ");
		}
		if (ligneCommandeClientDto.getArticle()==null) {
			errors.add("Veuillez renseigner l'article correspond a la ligne ");
		}
		if (ligneCommandeClientDto.getCommandeClient()==null) {
			errors.add("Veuillez renseigner la commande client correspond a la ligne ");
		}
		if (ligneCommandeClientDto.getQuantite()==null) {
			errors.add("Veuillez renseigner la quantite de la ligne ");
		}
		return errors;
	}

}

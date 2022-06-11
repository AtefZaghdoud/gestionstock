package com.Atef.gestionstock.validator;

import java.util.ArrayList;
import java.util.List;

import com.Atef.gestionstock.dto.LigneVenteDto;

public class LigneVenteValidator {
	public static List<String> validate(LigneVenteDto ligneVenteDto){
		List<String> errors = new ArrayList<>();
		
		if (ligneVenteDto==null) {
			errors.add("Veuillez renseigner le prix unitaire de la ligne vente");
			errors.add("Veuillez renseigner la vente correspond a la ligne vente ");
			errors.add("Veuillez renseigner la quantite de la ligne vente ");

			return errors;

		}
		if (ligneVenteDto.getPrixUnitaire()==null) {
			errors.add("Veuillez renseigner le prix unitaire de la ligne ");
		}
		if (ligneVenteDto.getVente()==null) {
			errors.add("Veuillez renseigner la vente correspond a la ligne ");
		}
		if (ligneVenteDto.getQuantite()==null) {
			errors.add("Veuillez renseigner la quantite de la ligne ");
		}
		return errors;
	}


}

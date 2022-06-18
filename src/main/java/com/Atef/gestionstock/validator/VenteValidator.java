package com.Atef.gestionstock.validator;

import java.util.ArrayList;
import java.util.List;

import com.Atef.gestionstock.dto.VenteDto;

public class VenteValidator {

	public static List<String> validate(VenteDto venteDto){
		List<String> errors = new ArrayList<>();
		
		if (venteDto==null) {
			errors.add("Veuillez renseigner le code du vente ");
			errors.add("Veuillez renseigner la date du vente ");
			return errors;
		}
		if (venteDto.getCode()==null) {
			errors.add("Veuillez renseigner le code du vente ");
		}
		if (venteDto.getDateVente()==null) {
			errors.add("Veuillez renseigner la date du vente ");
		}

		
		return errors;
	}
}

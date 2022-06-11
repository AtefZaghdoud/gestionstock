package com.Atef.gestionstock.validator;

import java.util.ArrayList;
import java.util.List;


import com.Atef.gestionstock.dto.MvtStkDto;

public class MvtStkValidator {
	public static List<String> validate(MvtStkDto mvtStkDto){
		List<String> errors = new ArrayList<>();
		
		if (mvtStkDto==null) {
			errors.add("Veuillez renseigner le prix unitaire de la ligne vente");
			errors.add("Veuillez renseigner la vente correspond a la ligne vente ");
			errors.add("Veuillez renseigner la quantite de la ligne vente ");

			return errors;

		}
		if (mvtStkDto.getArticle()==null) {
			errors.add("Veuillez renseigner l'article corresspond a la mvt stock ");
		}
		if (mvtStkDto.getTypeMvt()==null) {
			errors.add("Veuillez renseigner le type de mvt ");
		}
		if (mvtStkDto.getQuantite()==null) {
			errors.add("Veuillez renseigner la quantite de la ligne ");
		}
		return errors;
	}
}

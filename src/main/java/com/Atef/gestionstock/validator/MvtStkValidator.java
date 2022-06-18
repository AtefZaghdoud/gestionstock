package com.Atef.gestionstock.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import com.Atef.gestionstock.dto.MvtStkDto;
import org.springframework.util.StringUtils;

public class MvtStkValidator {
	public static List<String> validate(MvtStkDto mvtStkDto){
		List<String> errors = new ArrayList<>();
		
		if (mvtStkDto==null) {
			errors.add("Veuillez renseigner l'article corresspond a la mvt stock ");
			errors.add("Veuillez renseigner le type de mvt ");
			errors.add("Veuillez renseigner la quantite de la ligne ");
			errors.add("Veuillez renseigner la date de la ligne ");

			return errors;

		}
		if (mvtStkDto.getArticle()==null || mvtStkDto.getArticle().getId()==null) {
			errors.add("Veuillez renseigner l'article corresspond a la mvt stock ");
		}
		if (mvtStkDto.getTypeMvt()==null) {
			errors.add("Veuillez renseigner le type de mvt ");
		}
		if (mvtStkDto.getQuantite()==null || mvtStkDto.getQuantite().compareTo(BigDecimal.ZERO)==0) {
			errors.add("Veuillez renseigner la quantite de la ligne ");
		}
		if (!StringUtils.hasLength(mvtStkDto.getTypeMvt().name())) {
			errors.add("Veuillez renseigner la date de la ligne ");
		}
		return errors;
	}
}

package com.Atef.gestionstock.validator;

import java.util.ArrayList;
import java.util.List;

import com.Atef.gestionstock.dto.CategoryDto;


public class CategoryValidator {
	public static List<String> validate(CategoryDto categoryDto){
		List<String> errors = new ArrayList<>();
		
		if ( categoryDto == null || !org.springframework.util.StringUtils.hasLength(categoryDto.getCode())) {
			errors.add("Veuillez renseigner le code de la categorie ");
			
		}
		return errors;
	}
}

package com.Atef.gestionstock.validator;

import java.util.ArrayList;
import java.util.List;


import com.Atef.gestionstock.dto.RoleDto;

public class RoleValidator {

	public static List<String> validate(RoleDto roleDto){
		List<String> errors = new ArrayList<>();
		
		if (roleDto==null) {
			errors.add("Veuillez renseigner le Nom du role ");
			errors.add("Veuillez renseigner l'utilisateur ");

			return errors;
		}
		if (roleDto.getRoleName()==null) {
			errors.add("Veuillez renseigner le Nom du role ");
		}
		if (roleDto.getUtilisateur()==null) {
			errors.add("Veuillez renseigner l'utilisateur ");
		}

		
		return errors;
	}
}

package com.Atef.gestionstock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.Atef.gestionstock.dto.CommandeFournisseurDto;

public class CommandeFournisseurValidator {
	public static List<String> validate(CommandeFournisseurDto commandeFournisseurDto){
		List<String> errors = new ArrayList<>();
		
		if (commandeFournisseurDto==null) {
			errors.add("Veuillez renseigner le code de commande Fournisseur ");
			errors.add("Veuillez renseigner le client correspond a la commande Fournisseur ");
//			errors.add("Veuillez renseigner l'etat de la commande Fournisseur ");
//			errors.add("Veuillez renseigner le code de commande Fournisseur ");
//			errors.add("Veuillez renseigner le ligne de commande correspond a la commande Fournisseur");

			return errors;

		}
		if (!StringUtils.hasLength(commandeFournisseurDto.getCode())) {
			errors.add("Veuillez renseigner le code de commande Fournisseur ");
		}
		if (commandeFournisseurDto.getFournisseur()==null || commandeFournisseurDto.getFournisseur().getId()==null) {
			errors.add("Veuillez renseigner le client correspond a la commande Fournisseur ");
		}
//		if (!StringUtils.hasLength(commandeFournisseurDto.getEtatCommande().toString())) {
//			errors.add("Veuillez renseigner l'etat de la commande Fournisseur ");
//		}
		/*if (commandeFournisseurDto.getDateCommande()==null) {
			errors.add("Veuillez renseigner le code de commande Fournisseur ");
		}*/
//		if (commandeFournisseurDto.getLigneCommandeFournisseurs()==null) {
//			errors.add("Veuillez renseigner le ligne de commande correspond a la commande Fournisseur ");
//		}
		return errors;
	}

}

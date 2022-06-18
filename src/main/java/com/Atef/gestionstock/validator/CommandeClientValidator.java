package com.Atef.gestionstock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.Atef.gestionstock.dto.CommandeClientDto;

public class CommandeClientValidator {

	public static List<String> validate(CommandeClientDto commandeClientDto){
		List<String> errors = new ArrayList<>();
		
		if (commandeClientDto==null) {
			errors.add("Veuillez renseigner le code de commande client ");
			errors.add("Veuillez renseigner le client correspond a la commande client ");
			errors.add("Veuillez renseigner l'etat de la commande client ");
//			errors.add("Veuillez renseigner le code de commande client ");
//			errors.add("Veuillez renseigner le ligne de commande correspond a la commande client ");
			errors.add("Veuillez renseigner la date de commande client ");

			return errors;

		}
		if (!StringUtils.hasLength(commandeClientDto.getCode())) {
			errors.add("Veuillez renseigner le code de commande client ");
		}
		if (commandeClientDto.getClient()==null|| commandeClientDto.getClient().getId()==null) {
			errors.add("Veuillez renseigner le client correspond a la commande client ");
		}
		if (!StringUtils.hasLength(commandeClientDto.getEtatCommande().toString())) {
			errors.add("Veuillez renseigner l'etat de la commande client ");
		}
		if (commandeClientDto.getDateCommande()==null) {
			errors.add("Veuillez renseigner la date de commande client ");
		}
//		if (commandeClientDto.getLigneCommandeClients()==null) {
//			errors.add("Veuillez renseigner le ligne de commande correspond a la commande client ");
//		}
		return errors;
	}
}

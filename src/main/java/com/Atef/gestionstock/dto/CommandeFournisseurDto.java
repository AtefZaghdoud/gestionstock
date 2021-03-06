package com.Atef.gestionstock.dto;

import java.time.Instant;
import java.util.List;

import com.Atef.gestionstock.model.CommandeFournisseur;

import com.Atef.gestionstock.model.EtatCommande;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CommandeFournisseurDto {

	private Integer id ;

	private String code;
	
	private Instant datecommande ;
	
	private Integer idEntreprise;

	private EtatCommande etatCommande;

	private FournisseurDto fournisseur;
	
	private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;
	
	
public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
		
		if (commandeFournisseur ==null) {
			return null;
		}
		return CommandeFournisseurDto.builder()
				.id(commandeFournisseur.getId())
				.code(commandeFournisseur.getCode())
				.datecommande(commandeFournisseur.getDatecommande())
				.idEntreprise(commandeFournisseur.getIdEntreprise())
				.etatCommande(commandeFournisseur.getEtatCommande())
				//.ligneCommandeFournisseurs(null)
				.fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
				.build();
		
	}
	
	public static CommandeFournisseur toEntity(CommandeFournisseurDto commandeFournisseurDto) {
		if (commandeFournisseurDto == null ) {
			return null ;
		}
		CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
		commandeFournisseur.setId(commandeFournisseurDto.getId());
		commandeFournisseur.setCode(commandeFournisseurDto.getCode());
		commandeFournisseur.setDatecommande(commandeFournisseurDto.getDatecommande());
		commandeFournisseur.setIdEntreprise(commandeFournisseurDto.getIdEntreprise());
		commandeFournisseur.setEtatCommande(commandeFournisseurDto.getEtatCommande());
		commandeFournisseur.setFournisseur(FournisseurDto.toEntity(commandeFournisseurDto.getFournisseur()));
		//commandeFournisseur.setLigneCommandeFournisseurs(commandeFournisseurDto.getLigneCommandeFournisseurs());
		
		return commandeFournisseur ;
	}

	public  boolean isCommandeLivree(){
		return EtatCommande.LIVREE.equals(this.etatCommande);
	}


}

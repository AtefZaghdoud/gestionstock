package com.Atef.gestionstock.dto;

import java.math.BigDecimal;

import com.Atef.gestionstock.model.LigneCommandeFournisseur;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class LigneCommandeFournisseurDto {

	private Integer id ;

	private ArticleDto article ;
	
	private CommandeFournisseurDto commandeFournisseur;
	
	private Integer idEntreprise;

	private BigDecimal quantite;
	
	private BigDecimal prixUnitaire;

	
	public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
		
		if (ligneCommandeFournisseur ==null) {
			return null;
		}
		return LigneCommandeFournisseurDto.builder()
				.id(ligneCommandeFournisseur.getId())
				.prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
				.quantite(ligneCommandeFournisseur.getQuantite())
				.article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
				.commandeFournisseur(CommandeFournisseurDto.fromEntity(ligneCommandeFournisseur.getCommandeFournisseur()))
				.idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
				.build();
		
	}
	
	public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
		if (ligneCommandeFournisseurDto == null ) {
			return null ;
		}
		LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur()  ;
		ligneCommandeFournisseur.setId(ligneCommandeFournisseurDto.getId());
		ligneCommandeFournisseur.setPrixUnitaire(ligneCommandeFournisseurDto.getPrixUnitaire());
		ligneCommandeFournisseur.setQuantite(ligneCommandeFournisseurDto.getQuantite());
		ligneCommandeFournisseur.setArticle(ArticleDto.toEntity(ligneCommandeFournisseurDto.getArticle()));
		ligneCommandeFournisseur.setIdEntreprise(ligneCommandeFournisseurDto.getIdEntreprise());
		ligneCommandeFournisseur.setCommandeFournisseur(CommandeFournisseurDto.toEntity(ligneCommandeFournisseurDto.getCommandeFournisseur()));
		return ligneCommandeFournisseur ;
	}
	
	
	


}

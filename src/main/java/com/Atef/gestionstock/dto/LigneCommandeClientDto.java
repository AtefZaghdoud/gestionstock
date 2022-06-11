package com.Atef.gestionstock.dto;

import java.math.BigDecimal;

import com.Atef.gestionstock.model.LigneCommandeClient;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LigneCommandeClientDto {

	private Integer id ;

	private ArticleDto article ;
	
	private Integer idEntreprise;
	
	
	private CommandeClientDto commandeClient;
	
	private BigDecimal quantite;
	
	private BigDecimal prixUnitaire;

	public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {
		
		if (ligneCommandeClient ==null) {
			return null;
		}
		return  LigneCommandeClientDto.builder()
				.id(ligneCommandeClient.getId())
				.article(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
				.prixUnitaire(ligneCommandeClient.getPrixUnitaire())
				.quantite(ligneCommandeClient.getQuantite())
				.commandeClient(CommandeClientDto.fromEntity(ligneCommandeClient.getCommandeClient()))
				.idEntreprise(ligneCommandeClient.getIdEntreprise())
				.build();
		
	}
	
	public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto) {
		if (ligneCommandeClientDto == null ) {
			return null ;
		}
		LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
		ligneCommandeClient.setId(ligneCommandeClientDto.getId());
		ligneCommandeClient.setPrixUnitaire(ligneCommandeClientDto.getPrixUnitaire());
		ligneCommandeClient.setQuantite(ligneCommandeClientDto.getQuantite());
		ligneCommandeClient.setIdEntreprise(ligneCommandeClientDto.getIdEntreprise());
		ligneCommandeClient.setArticle(ArticleDto.toEntity(ligneCommandeClientDto.getArticle()));
		ligneCommandeClient.setCommandeClient(CommandeClientDto.toEntity(ligneCommandeClientDto.getCommandeClient()));
		return ligneCommandeClient ;
	}
	
	


}
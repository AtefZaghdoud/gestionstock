package com.Atef.gestionstock.dto;

import java.math.BigDecimal;

import com.Atef.gestionstock.model.Article;
import com.Atef.gestionstock.model.LigneVente;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class LigneVenteDto {

	private Integer id ;

	private Article article;
	private VenteDto vente;
	
	private Integer idEntreprise;
	
	private BigDecimal quantite;
	
	private BigDecimal prixUnitaire;
	
	public static LigneVenteDto fromEntity(LigneVente ligneVente) {
		
		if (ligneVente ==null) {
			return null;
		}
		return LigneVenteDto.builder()
				.id(ligneVente.getId())
				.article(ligneVente.getArticle())
				.prixUnitaire(ligneVente.getPrixUnitaire())
				.quantite(ligneVente.getQuantite())
				.idEntreprise(ligneVente.getIdEntreprise())
				//.vente(VenteDto.)
				.build();
		
	}
	
	public static LigneVente toEntity(LigneVenteDto ligneVenteDto) {
		if (ligneVenteDto == null ) {
			return null ;
		}
		LigneVente ligneVente = new LigneVente() ;
		ligneVente.setId(ligneVenteDto.getId());
		ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());
		ligneVente.setQuantite(ligneVenteDto.getQuantite());
		ligneVente.setArticle(ligneVenteDto.getArticle());
		ligneVente.setIdEntreprise(ligneVenteDto.getIdEntreprise());
		//ligneVente.setVente(ligneVenteDto.getVente());
		return ligneVente ;
	}
	
}

package com.Atef.gestionstock.dto;

import java.time.Instant;
import java.util.List;

import com.Atef.gestionstock.model.Ventes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VenteDto {

	private Integer id ;

	private String code;
	
	private Instant dateVente;
	
	private String commentaire ;
	
	private Integer idEntreprise;
	
	List<LigneVenteDto> ligneVentes ;
	
	
	public static VenteDto fromEntity(Ventes ventes) {
		
		if (ventes==null) {
			return null;
		}
		return VenteDto.builder()
				.id(ventes.getId())
				.code(ventes.getCode())
				.commentaire(ventes.getCommentaire())
				.dateVente(ventes.getDateVente())
				.idEntreprise(ventes.getIdEntreprise())
				.build();
		
	}
	
	public static Ventes toEntity(VenteDto venteDto) {
		if (venteDto == null ) {
			return null ;
		}
		Ventes ventes = new Ventes();
		ventes.setId(venteDto.getId());
		ventes.setCode(venteDto.getCode());
		ventes.setCommentaire(venteDto.getCommentaire());
		ventes.setDateVente(venteDto.getDateVente());
		ventes.setIdEntreprise(venteDto.getIdEntreprise());
		return ventes;
	}
	
	
	
	
	
}

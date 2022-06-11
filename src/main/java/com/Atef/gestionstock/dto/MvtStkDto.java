package com.Atef.gestionstock.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.Atef.gestionstock.model.MvtStk;
import com.Atef.gestionstock.model.TypeMvtStk;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MvtStkDto {
	
	private Integer id ;

	private Instant dateMvt;
	
	private BigDecimal quantite;	
	
	private ArticleDto article ;
	
	private Integer idEntreprise;
	
	private TypeMvtStk typeMvt;
	
	
	public static MvtStkDto fromEntity(MvtStk mvtStk) {
		
		if (mvtStk ==null) {
			return null;
		}
		return MvtStkDto.builder()
				.id(mvtStk.getId())
				.quantite(mvtStk.getQuantite())
				.article(ArticleDto.fromEntity(mvtStk.getArticle()))
				.dateMvt(mvtStk.getDateMvt())
				.typeMvt(mvtStk.getTypeMvt())
				.idEntreprise(mvtStk.getIdEntreprise())
				.build();
		
	}
	
	public static MvtStk toEntity(MvtStkDto mvtStkDto) {
		if (mvtStkDto == null ) {
			return null ;
		}
		MvtStk mvtStk = new MvtStk() ;
		mvtStk.setId(mvtStkDto.getId());
		mvtStk.setDateMvt(mvtStkDto.getDateMvt());
		mvtStk.setQuantite(mvtStkDto.getQuantite());
		mvtStk.setTypeMvt(mvtStkDto.getTypeMvt());
		mvtStk.setIdEntreprise(mvtStkDto.getIdEntreprise());
		mvtStk.setArticle(ArticleDto.toEntity(mvtStkDto.getArticle()));
		return mvtStk ;
	}
	
	
	
}

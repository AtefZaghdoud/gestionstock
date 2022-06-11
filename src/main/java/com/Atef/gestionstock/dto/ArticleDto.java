package com.Atef.gestionstock.dto;

import java.math.BigDecimal;
import java.time.Instant;


import com.Atef.gestionstock.model.Article;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ArticleDto {

	private Integer id ;

	private String  codeArticle ;
	
	private String designation;

	private BigDecimal prixUnitaireHt;

	private BigDecimal tauxTva;

	private BigDecimal prixunitaireTtc;
	
	private String photo;
	
	private CategoryDto category;
	
	private Integer idEntreprise;
	
	public static ArticleDto fromEntity(Article article) {
		
		if (article==null) {
			return null;
		}
		return ArticleDto.builder()
				.id(article.getId())
				.codeArticle(article.getCodeArticle())
				.designation(article.getDesignation())
				.prixUnitaireHt(article.getPrixUnitaireHt())
				.prixunitaireTtc(article.getPrixunitaireTtc())
				.photo(article.getPhoto())
				.tauxTva(article.getTauxTva())
				.category(CategoryDto.fromEntity(article.getCategory()))
				.idEntreprise(article.getIdEntreprise())
				.build();
		
	}
	
	public static Article toEntity(ArticleDto articleDto) {
		if (articleDto == null ) {
			return null ;
		}
		Article article = new Article();
		article.setId(articleDto.getId());
		article.setCodeArticle(articleDto.getCodeArticle());
		article.setDesignation(articleDto.getDesignation());
		article.setCategory(CategoryDto.toEntity(articleDto.getCategory()));
		article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
		article.setPrixunitaireTtc(articleDto.getPrixunitaireTtc());
		article.setPhoto(articleDto.getPhoto());
		article.setTauxTva(articleDto.getTauxTva());
		article.setCreationDate(Instant.now());
		article.setIdEntreprise(articleDto.getIdEntreprise());
		return article;
	}
	
	
	
}

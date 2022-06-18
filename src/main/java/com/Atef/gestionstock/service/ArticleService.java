package com.Atef.gestionstock.service;

import java.util.List;

import com.Atef.gestionstock.dto.ArticleDto;
import com.Atef.gestionstock.dto.LigneCommandeClientDto;
import com.Atef.gestionstock.dto.LigneCommandeFournisseurDto;
import com.Atef.gestionstock.dto.LigneVenteDto;

public interface ArticleService {
	
	ArticleDto save(ArticleDto dto);
	
	ArticleDto findById(Integer id);
	
	ArticleDto findByCodeArticle(String CodeArticle);
	
	List<ArticleDto> findAll();

	List<LigneVenteDto> findHistoriqueVentes(Integer idArticle);

	List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle);

	List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);

	List<ArticleDto> findAllArticleByIdCategory(Integer idCategory);

	void delete(Integer id);
	
}

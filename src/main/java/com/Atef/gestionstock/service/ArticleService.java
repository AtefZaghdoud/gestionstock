package com.Atef.gestionstock.service;

import java.util.List;

import com.Atef.gestionstock.dto.ArticleDto;

public interface ArticleService {
	
	ArticleDto save(ArticleDto dto);
	
	ArticleDto findById(Integer id);
	
	ArticleDto findByCodeArticle(String CodeArticle);
	
	List<ArticleDto> findAll();
	
	void delete(Integer id);
	
}

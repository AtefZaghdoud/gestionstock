package com.Atef.gestionstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.Atef.gestionstock.controller.api.ArticleApi;
import com.Atef.gestionstock.dto.ArticleDto;
import com.Atef.gestionstock.service.ArticleService;

@RestController
public class ArticleController implements ArticleApi {

	private ArticleService articleService;

	@Autowired
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;

	}

	@Override
	public ArticleDto save(ArticleDto dto) {
		return articleService.save(dto);
	}

	@Override
	public ArticleDto findById(Integer id) {
		return articleService.findById(id);
	}

	@Override
	public ArticleDto findByCodeArticle(String CodeArticle) {
		return articleService.findByCodeArticle(CodeArticle);
	}

	@Override
	public List<ArticleDto> findAll() {
		return articleService.findAll();
	}

	@Override
	public void delete(Integer id) {
		articleService.delete(id);
	}

}

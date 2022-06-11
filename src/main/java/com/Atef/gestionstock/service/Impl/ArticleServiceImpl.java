package com.Atef.gestionstock.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.Atef.gestionstock.dto.ArticleDto;
import com.Atef.gestionstock.exception.EntityNotFoundException;
import com.Atef.gestionstock.exception.ErrorCodes;
import com.Atef.gestionstock.exception.InvalidEntityException;
import com.Atef.gestionstock.model.Article;
import com.Atef.gestionstock.repository.ArticleRepository;
import com.Atef.gestionstock.service.ArticleService;
import com.Atef.gestionstock.validator.ArticleValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService{
	
	private ArticleRepository articleRepository;
	
	@Autowired
	public ArticleServiceImpl(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	@Override
	public ArticleDto save(ArticleDto dto) {
		List<String> errors = ArticleValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Article is not Valid {}",dto);
			throw new InvalidEntityException("L'aritcle n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID,errors);
			
		}

		return ArticleDto.fromEntity(
				articleRepository.save(
						ArticleDto.toEntity(dto)
						)
				);
	} 

	@Override
	public ArticleDto findById(Integer id) {
		if (id ==null) {
			log.error("Article id is null");
			return null;
		}
		
		 
		Optional<Article > article = articleRepository.findById(id);
		
		ArticleDto dto = ArticleDto.fromEntity(article.get());
		
		return Optional.of(dto).orElseThrow( () -> 
		new EntityNotFoundException("Aucun article avec l'ID " + id + "n'ete trouver dans la base de données " ,
				 ErrorCodes.ARTICLE_NOT_FOUND)  ) ;
		}

	@Override
	public ArticleDto findByCodeArticle(String CodeArticle) {
		if (!StringUtils.hasLength(CodeArticle)) {
			log.error("Article CODE is null");
			return null;
		}	
		
		Optional<Article > article = articleRepository.findByCodeArticle(CodeArticle);
		
		ArticleDto dto = ArticleDto.fromEntity(article.get());
		
		return Optional.of(dto).orElseThrow( () -> 
		new EntityNotFoundException("Aucun article avec le Code  " + CodeArticle + "n'ete trouver dans la base de données " ,
				 ErrorCodes.ARTICLE_NOT_FOUND)  ) ;
		
	}

	@Override
	public List<ArticleDto> findAll() {
		return articleRepository.findAll().stream()
				.map(ArticleDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id ==null) {
			log.error("Article id is null");
			return ;
		}
		articleRepository.deleteById(id);
	}
	

}

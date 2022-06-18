package com.Atef.gestionstock.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import com.Atef.gestionstock.exception.InvalidOperationException;
import com.Atef.gestionstock.model.Article;
import com.Atef.gestionstock.model.LigneCommandeFournisseur;
import com.Atef.gestionstock.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.Atef.gestionstock.dto.CategoryDto;
import com.Atef.gestionstock.exception.EntityNotFoundException;
import com.Atef.gestionstock.exception.ErrorCodes;
import com.Atef.gestionstock.exception.InvalidEntityException;
import com.Atef.gestionstock.repository.CategoryRepository;
import com.Atef.gestionstock.service.CategoryService;
import com.Atef.gestionstock.validator.CategoryValidator;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{

	
	private CategoryRepository categoryRepository;
	private ArticleRepository articleRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, ArticleRepository articleRepository) {
		this.categoryRepository = categoryRepository;
		this.articleRepository = articleRepository;
	}
	
	
	@Override
	public CategoryDto save(CategoryDto dto) {
		List<String> errors = CategoryValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Category is not Valid {}",dto);
			throw new InvalidEntityException("La category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID,errors);
			
		}

		return CategoryDto.fromEntity(
				categoryRepository.save(
						CategoryDto.toEntity(dto)
						)
				);
	}

	@Override
	public CategoryDto findById(Integer id) {
		if (id ==null) {
			log.error("Category id is null");
			return null;
		}
		
		return categoryRepository.findById(id)
				.map(CategoryDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException("Aucun category avec l'ID "+id+"n'ete trouve dans la BDD ", 
						ErrorCodes.CATEGORY_NOT_FOUND));
	}

	@Override
	public CategoryDto findByCodeCategory(String CodeCategory) {
		if (!StringUtils.hasLength(CodeCategory)) {
			log.error("Category CODE is null");
			return null;
		}	
		return categoryRepository.findCategoryByCode(CodeCategory)
				.map(CategoryDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException("Aucun category avec le code "+CodeCategory+"n'ete trouve dans la BDD ", 
						ErrorCodes.CATEGORY_NOT_FOUND));
	}

	@Override
	public List<CategoryDto> findAll() {
		return categoryRepository.findAll().stream()
				.map(CategoryDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id ==null) {
			log.error("Category id is null");
			return ;
		}
		List<Article> articles =articleRepository.findAllByCategoryId(id);
		if (!articles.isEmpty()){
			throw new InvalidOperationException("Impossible de supprimer cette categorie qui est déja utilisé ",ErrorCodes.CATEGORY_ALREADY_IN_USE);
		}
		categoryRepository.deleteById(id);
		
	}

}

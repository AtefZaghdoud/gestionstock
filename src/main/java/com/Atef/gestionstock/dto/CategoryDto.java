package com.Atef.gestionstock.dto;

import java.time.Instant;
import java.util.List;



import com.Atef.gestionstock.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {

	private Integer id ;

	private String code;
	
	private String designation;
	
	private Integer idEntreprise;
	
	@JsonIgnore
	private List<ArticleDto> articles;
	
	public static CategoryDto fromEntity (Category category) {
		if (category==null) {
			return null;
			//TODO throw an exception
		}
		return CategoryDto.builder()
				.id(category.getId())
				.code(category.getCode())
				.designation(category.getDesignation())
				.idEntreprise(category.getIdEntreprise())
				.build();
				
	}
	
	public static Category toEntity (CategoryDto categoryDto) {
		if (categoryDto == null) {
			return null;
			//TODO throw an exception

		}
		Category category = new Category();
		category.setId(categoryDto.getId());
		category.setCode(categoryDto.getCode());
		category.setIdEntreprise(categoryDto.getIdEntreprise());
		category.setCreationDate(Instant.now());
		category.setDesignation(categoryDto.getDesignation());
		return category;
		/*return Category.builder()
				.code(categoryDto.getCode())
				.designation(categoryDto.getDesignation())
				.build();*/
	}
	
}

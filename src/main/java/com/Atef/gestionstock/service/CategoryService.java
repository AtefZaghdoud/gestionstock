package com.Atef.gestionstock.service;

import java.util.List;

import com.Atef.gestionstock.dto.CategoryDto;

public interface CategoryService {
	
	CategoryDto save(CategoryDto dto);
	
	CategoryDto findById(Integer id);
	
	CategoryDto findByCodeCategory(String CodeCategory);
	
	List<CategoryDto> findAll();
	
	void delete(Integer id);
	

}

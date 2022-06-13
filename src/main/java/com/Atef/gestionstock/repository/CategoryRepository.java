package com.Atef.gestionstock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Atef.gestionstock.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Optional<Category> findCategoryByCode(String code);

}

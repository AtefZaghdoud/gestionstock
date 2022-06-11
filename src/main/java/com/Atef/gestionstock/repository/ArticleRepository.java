package com.Atef.gestionstock.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Atef.gestionstock.model.Article;

public interface ArticleRepository extends JpaRepository<Article , Integer> {
	Optional<Article> findByCodeArticle(String codeArticle);
}

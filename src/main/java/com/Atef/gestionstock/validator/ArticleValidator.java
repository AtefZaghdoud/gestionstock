package com.Atef.gestionstock.validator;

import java.util.ArrayList;
import java.util.List;

import com.Atef.gestionstock.dto.ArticleDto;

public class ArticleValidator {
		public static List<String> validate(ArticleDto articleDto){
			List<String> errors = new ArrayList<>();
			
			if (articleDto==null) {
				errors.add("Veuillez renseigner le code de l'article ");
				errors.add("Veuillez renseigner le designation de l'article ");
				errors.add("Veuillez renseigner le prix unitaire ht de l'article ");
				errors.add("Veuillez renseigner le taux TVA de l'article ");
				errors.add("Veuillez sélectionner une categorie de l'article ");
				return errors;

			}
			if (!org.springframework.util.StringUtils.hasLength(articleDto.getCodeArticle())) {
				errors.add("Veuillez renseigner le code de l'article ");
			}
			if (!org.springframework.util.StringUtils.hasLength(articleDto.getDesignation())) {
				errors.add("Veuillez renseigner le designation de l'article ");
			}
			if (articleDto.getPrixUnitaireHt()==null) {
				errors.add("Veuillez renseigner le prix unitaire ht de l'article ");
			}
			if (articleDto.getTauxTva()==null) {
				errors.add("Veuillez renseigner le taux TVA de l'article ");
			}
			if (articleDto.getPrixunitaireTtc()==null) {
				errors.add("Veuillez renseigner le prix unitaire ttc de l'article ");
			}
			if (articleDto.getCategory()==null) {
				errors.add("Veuillez sélectionner une categorie de l'article ");
			}
			return errors;
		}
}

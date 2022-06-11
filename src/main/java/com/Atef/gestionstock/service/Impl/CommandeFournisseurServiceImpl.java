package com.Atef.gestionstock.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.Atef.gestionstock.dto.CommandeFournisseurDto;
import com.Atef.gestionstock.dto.LigneCommandeFournisseurDto;
import com.Atef.gestionstock.exception.EntityNotFoundException;
import com.Atef.gestionstock.exception.ErrorCodes;
import com.Atef.gestionstock.exception.InvalidEntityException;
import com.Atef.gestionstock.model.Article;
import com.Atef.gestionstock.model.CommandeFournisseur;
import com.Atef.gestionstock.model.Fournisseur;
import com.Atef.gestionstock.model.LigneCommandeFournisseur;
import com.Atef.gestionstock.repository.ArticleRepository;
import com.Atef.gestionstock.repository.CommandeFournisseurRepository;
import com.Atef.gestionstock.repository.FournisseurRepository;
import com.Atef.gestionstock.repository.LigneCommandeFournisseurRepository;
import com.Atef.gestionstock.service.CommandeFournisseurService;
import com.Atef.gestionstock.validator.CommandeFournisseurValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

	private CommandeFournisseurRepository commandeFournisseurRepository;
	private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
	private FournisseurRepository fournisseurRepository;
	private ArticleRepository articleRepository;
	
	
	@Autowired
	public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
			LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository,
			FournisseurRepository fournisseurRepository, ArticleRepository articleRepository) {
		super();
		this.commandeFournisseurRepository = commandeFournisseurRepository;
		this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
		this.fournisseurRepository = fournisseurRepository;
		this.articleRepository = articleRepository;
	}

	@Override
	public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
		List<String> errors = CommandeFournisseurValidator.validate(dto);
		
		if (!errors.isEmpty()) {
			log.error("Commande fournisseur n'est pas valide ");
			throw new InvalidEntityException("La commande Fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
		}
		
		Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseur().getId()) ;
		if (fournisseur.isEmpty()) {
			log.error("Fournisseur with ID {} was not found in the DB",dto.getFournisseur().getId());
			throw new EntityNotFoundException("Aucun fournisseur avec l'ID"+dto.getFournisseur().getId()+"n'a ete trouve dans la BDD", ErrorCodes.FOURNISSEUR_NOT_FOUND);
		}
		
		List<String > articleErrors = new ArrayList<>();
		
		if (dto.getLigneCommandeFournisseurs() != null) {
			dto.getLigneCommandeFournisseurs().forEach( ligneCommandeFournisseur -> {
				if (ligneCommandeFournisseur.getArticle()!=null ) {
				
					Optional<Article> article = articleRepository.findById(ligneCommandeFournisseur.getArticle().getId());
					if (article.isEmpty()) {
						articleErrors.add("L'article avec l'ID "+ ligneCommandeFournisseur.getArticle().getId() + "n'existe pas ");
					}
				} else {
					articleErrors.add(" Impossible d'enregistrer une commande avec un article NULL ");
				}
				
			});
			
		}
		
		if (!articleErrors.isEmpty()) {
			log.error("");
			throw new InvalidEntityException("Article n'existe pas dans la BDD ", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
		}
		
		CommandeFournisseur savedCommandeFournisseur	= commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));
		
		if (dto.getLigneCommandeFournisseurs() != null) {
			dto.getLigneCommandeFournisseurs().forEach(ligCommandeFournisseur -> {
				LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligCommandeFournisseur);
				ligneCommandeFournisseur.setCommandeFournisseur(savedCommandeFournisseur);
				ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
			});	
		}
		
		
		return CommandeFournisseurDto.fromEntity(savedCommandeFournisseur);
	}

	@Override
	public CommandeFournisseurDto findById(Integer id) {
		if (id == null) {
			log.error("commande Fournisseur ID is null");
			return null;
		}
		
		return commandeFournisseurRepository.findById(id)
				.map(CommandeFournisseurDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucune Commande Fournisseur ,'a ete trouve avec l'ID "+ id , ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
	}

	@Override
	public CommandeFournisseurDto findByCode(String code) {
		if (!StringUtils.hasLength(code)) {
			log.error("commande Fournisseur Code is null");
			return null;
		}
		return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
				.map(CommandeFournisseurDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucune Commande Fournisseur ,'a ete trouve avec l'Code "+ code , ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
	}

	@Override
	public List<CommandeFournisseurDto> findAll() {
		return commandeFournisseurRepository.findAll().stream()
				.map(CommandeFournisseurDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("commande Fournisseur ID is null");
			return ;
		}
		commandeFournisseurRepository.deleteById(id);
		
	}

}

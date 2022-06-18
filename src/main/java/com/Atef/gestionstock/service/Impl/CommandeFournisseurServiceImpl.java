package com.Atef.gestionstock.service.Impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.Atef.gestionstock.dto.*;
import com.Atef.gestionstock.exception.InvalidOperationException;
import com.Atef.gestionstock.model.*;
import com.Atef.gestionstock.service.MvtStkService;
import com.Atef.gestionstock.validator.ArticleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.Atef.gestionstock.exception.EntityNotFoundException;
import com.Atef.gestionstock.exception.ErrorCodes;
import com.Atef.gestionstock.exception.InvalidEntityException;
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
	private MvtStkService mvtStkService;


	@Autowired
	public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
										  LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository,
										  FournisseurRepository fournisseurRepository, ArticleRepository articleRepository, MvtStkService mvtStkService) {
		super();
		this.commandeFournisseurRepository = commandeFournisseurRepository;
		this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
		this.fournisseurRepository = fournisseurRepository;
		this.articleRepository = articleRepository;
		this.mvtStkService = mvtStkService;
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
	public CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
		if (idCommande == null ){
			log.error("commande Fournisseur ID is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un ID Null", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
		}
		if (StringUtils.hasLength(String.valueOf(etatCommande)) ){
			log.error("l'etat de la commande COMMANDE_FOURNISSEUR_NOT_MODIFIABLE is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un etat NULL ", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
		}

		CommandeFournisseurDto commandeFournisseurDto = findById(idCommande);

		if(commandeFournisseurDto.getId() != null && commandeFournisseurDto.isCommandeLivree()){
			throw new InvalidOperationException("impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
		}

		commandeFournisseurDto.setEtatCommande(etatCommande);

		CommandeFournisseur savedCmdFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));
		if (commandeFournisseurDto.isCommandeLivree()) {
			updateMvtStk(idCommande);
		}
		return CommandeFournisseurDto.fromEntity(savedCmdFournisseur);

	}

	@Override
	public CommandeFournisseurDto updateQuatiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
		if (idCommande == null ){
			log.error("commande Fournisseur ID is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un ID Null", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
		}

		if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0 ){
			log.error("commande client Quantite is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec une quantite null ou zero", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
		}

		if (idLigneCommande == null ){
			log.error("l'id de la ligne commande client is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec une ligne de commande null  ", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
		}

		CommandeFournisseurDto commandeFournisseur = findById(idCommande);

		if(commandeFournisseur.getId() != null && commandeFournisseur.isCommandeLivree()){
			throw new InvalidOperationException("impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
		}

		Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional = ligneCommandeFournisseurRepository.findById(idLigneCommande);

		if(ligneCommandeFournisseurOptional.isEmpty()){
			throw new EntityNotFoundException(
					"Aucune ligne Commande FOURNISSEUR 'a ete trouve avec l'id "+ idLigneCommande , ErrorCodes.LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND);
		}

		LigneCommandeFournisseur ligneCommandeFournisseur = ligneCommandeFournisseurOptional.get();

		ligneCommandeFournisseur.setQuantite(quantite);

		ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);

		return commandeFournisseur;
	}

	@Override
	public CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur) {
		if (idCommande == null ){
			log.error("commande Fournisseur ID is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un ID Null", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
		}
		if (idFournisseur == null ){
			log.error("l'ID de Fournisseur is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id Fournisseur NULL ", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
		}

		CommandeFournisseurDto commandeFournisseur = findById(idCommande);

		if(commandeFournisseur.getId() != null && commandeFournisseur.isCommandeLivree()){
			throw new InvalidOperationException("impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
		}

		Optional<Fournisseur> fournisseurOptional= fournisseurRepository.findById(idFournisseur);
		if (fournisseurOptional.isEmpty()){
			throw new InvalidOperationException("Aucun Fournisseur n'a ete trouve avec l'id "+ idFournisseur, ErrorCodes.FOURNISSEUR_NOT_FOUND);
		}

		commandeFournisseur.setFournisseur(FournisseurDto.fromEntity(fournisseurOptional.get() ));
		return CommandeFournisseurDto.fromEntity(
				commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseur))
		);
	}

	@Override
	public CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
		if (idCommande == null ){
			log.error("commande client ID is null");
			throw new InvalidOperationException("impossible de modifier l'article correspond a la commande  avec un ID Null", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
		}

		if (idLigneCommande == null ){
			log.error("l'id de la ligne commande Fournisseur is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec une ligne de commande null  ", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
		}

		if (idArticle == null ){
			log.error("Article nouvel commande Fournisseur ID is null");
			throw new InvalidOperationException("impossible de modifier l'article correspond a la commande  avec un nouvel ID article Null", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
		}

		CommandeFournisseurDto commandeFournisseur = findById(idCommande);

		if(commandeFournisseur.getId() != null && commandeFournisseur.isCommandeLivree()){
			throw new InvalidOperationException("impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
		}
		Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional =ligneCommandeFournisseurRepository.findById(idLigneCommande);

		if(ligneCommandeFournisseurOptional.isEmpty()){
			throw new EntityNotFoundException(
					"Aucune ligne Commande Fournisseur ,'a ete trouve avec l'id "+ idLigneCommande , ErrorCodes.LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND);
		}

		LigneCommandeFournisseur ligneCommandeFournisseur = ligneCommandeFournisseurOptional.get();

		Optional<Article> articleOptional= articleRepository.findById(idArticle);
		if(articleOptional.isEmpty()){
			throw new EntityNotFoundException(
					"Aucune article n'a ete trouve avec l'id "+ idArticle , ErrorCodes.ARTICLE_NOT_FOUND);

		}

		List<String> errors =  ArticleValidator.validate(ArticleDto.fromEntity(articleOptional.get()));

		if (!errors.isEmpty()){
			throw new InvalidEntityException("Article invalid ",ErrorCodes.ARTICLE_NOT_VALID,errors);
		}

		ligneCommandeFournisseur.setArticle(articleOptional.get());
		ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);

		return commandeFournisseur;

	}

	@Override
	public CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
		if (idCommande == null ){
			log.error("commande Fournisseur ID is null");
			throw new InvalidOperationException("impossible de modifier l'article correspond a la commande  avec un ID Null", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
		}

		if (idLigneCommande == null ){
			log.error("l'id de la ligne commande Fournisseur is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec une ligne de commande null  ", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
		}
		CommandeFournisseurDto commandeFournisseur = findById(idCommande);

		Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional =ligneCommandeFournisseurRepository.findById(idLigneCommande);

		if(ligneCommandeFournisseurOptional.isEmpty()){
			throw new EntityNotFoundException(
					"Aucune ligne Commande client ,'a ete trouve avec l'id "+ idLigneCommande , ErrorCodes.LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND);
		}

		ligneCommandeFournisseurRepository.deleteById(idLigneCommande);

		return commandeFournisseur;

	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("commande Fournisseur ID is null");
			return ;
		}
		List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(id);
		if (!ligneCommandeFournisseurs.isEmpty()) {
			throw new InvalidOperationException("Impossible de supprimer une commande fournisseur qui est déja utilisé  ",ErrorCodes.COMMANDE_FOURNISSEUR_ALREADY_IN_USE);
		}
		commandeFournisseurRepository.deleteById(id);
		
	}

	private void updateMvtStk(Integer idCommande){
		List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idCommande);
		ligneCommandeFournisseurs.forEach( lig -> {
			MvtStkDto mvtStkDto = MvtStkDto.builder()
					.article(ArticleDto.fromEntity(lig.getArticle()))
					.dateMvt(Instant.now())
					.typeMvt(TypeMvtStk.ENTREE)
					.sourceMvtStk(SourceMvtStk.COMMANDE_CLIENT)
					.quantite(lig.getQuantite())
					.idEntreprise(lig.getIdEntreprise())
					.build();
			mvtStkService.sortieStock(mvtStkDto);

		});

	}


}

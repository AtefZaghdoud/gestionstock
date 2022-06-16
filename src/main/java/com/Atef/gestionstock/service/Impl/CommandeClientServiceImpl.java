package com.Atef.gestionstock.service.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.Atef.gestionstock.dto.ArticleDto;
import com.Atef.gestionstock.dto.ClientDto;
import com.Atef.gestionstock.exception.InvalidOperationException;
import com.Atef.gestionstock.model.*;
import com.Atef.gestionstock.validator.ArticleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.Atef.gestionstock.dto.CommandeClientDto;
import com.Atef.gestionstock.dto.LigneCommandeClientDto;
import com.Atef.gestionstock.exception.EntityNotFoundException;
import com.Atef.gestionstock.exception.ErrorCodes;
import com.Atef.gestionstock.exception.InvalidEntityException;
import com.Atef.gestionstock.repository.ArticleRepository;
import com.Atef.gestionstock.repository.ClientRepository;
import com.Atef.gestionstock.repository.CommandeClientRepository;
import com.Atef.gestionstock.repository.LigneCommandeClientRepository;
import com.Atef.gestionstock.service.CommandeClientService;
import com.Atef.gestionstock.validator.CommandeClientValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {
	
	private CommandeClientRepository commandeClientRepository;
	private LigneCommandeClientRepository ligneCommandeClientRepository;
	private ClientRepository clientRepository;
	private ArticleRepository articleRepository;
	
	
	
	@Autowired
	public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
			ClientRepository clientRepository, ArticleRepository articleRepository ,LigneCommandeClientRepository ligneCommandeClientRepository) {
		super();
		this.commandeClientRepository = commandeClientRepository;
		this.clientRepository = clientRepository;
		this.articleRepository = articleRepository;
		this.ligneCommandeClientRepository=ligneCommandeClientRepository;
	}

	@Override
	public CommandeClientDto save(CommandeClientDto dto) {

		List<String> errors = CommandeClientValidator.validate(dto);
		
		if (!errors.isEmpty()) {
			log.error("Commande client n'est pas valide ");
			throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
		}

		if(dto.getId() != null && dto.isCommandeLivree()){
			throw new InvalidOperationException("impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}

		Optional<Client> client = clientRepository.findById(dto.getClient().getId()) ;
		if (client.isEmpty()) {
			log.error("Client with ID {} was not found in the DB",dto.getClient().getId());
			throw new EntityNotFoundException("Aucun client avec l'ID"+dto.getClient().getId()+"n'a ete trouve dans la BDD", ErrorCodes.CLIENT_NOT_FOUND);
		}
		
		List<String > articleErrors = new ArrayList<>();
		
		if (dto.getLigneCommandeClients() != null) {
			dto.getLigneCommandeClients().forEach( ligneCommandeClient -> {
				if (ligneCommandeClient.getArticle()!=null ) {
				
					Optional<Article> article = articleRepository.findById(ligneCommandeClient.getArticle().getId());
					if (article.isEmpty()) {
						articleErrors.add("L'article avec l'ID "+ ligneCommandeClient.getArticle().getId() + "n'existe pas ");
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
		
		CommandeClient savedCommandeClient	= commandeClientRepository.save(CommandeClientDto.toEntity(dto));
		
		if (dto.getLigneCommandeClients() != null) {
			dto.getLigneCommandeClients().forEach(ligCommandeClient -> {
				LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCommandeClient);
				ligneCommandeClient.setCommandeClient(savedCommandeClient);
				ligneCommandeClientRepository.save(ligneCommandeClient);
			});	
		}
		
		
		return CommandeClientDto.fromEntity(savedCommandeClient);
	}

	@Override
	public CommandeClientDto findById(Integer id) {
		if (id == null) {
			log.error("commande client ID is null");
			return null;
		}
		
		return commandeClientRepository.findById(id)
				.map(CommandeClientDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucune Commande client ,'a ete trouve avec l'ID "+ id , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
	}

	@Override
	public CommandeClientDto findByCode(String code) {
		if (!StringUtils.hasLength(code)) {
			log.error("commande client Code is null");
			return null;
		}
		return commandeClientRepository.findCommandeClientByCode(code)
				.map(CommandeClientDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucune Commande client ,'a ete trouve avec l'Code "+ code , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
	}

	@Override
	public List<CommandeClientDto> findAll() {
		
		return commandeClientRepository.findAll().stream()
				.map(CommandeClientDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("commande client ID is null");
			return ;
		}
		commandeClientRepository.deleteById(id);
		
	}

	@Override
	public CommandeClientDto updateQuatiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {

		if (idCommande == null ){
			log.error("commande client ID is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un ID Null", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}

		if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0 ){
			log.error("commande client Quantite is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec une quantite null ou zero", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}

		if (idLigneCommande == null ){
			log.error("l'id de la ligne commande client is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec une ligne de commande null  ", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}

		CommandeClientDto commandeClient = findById(idCommande);

		if(commandeClient.getId() != null && commandeClient.isCommandeLivree()){
			throw new InvalidOperationException("impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}

		Optional<LigneCommandeClient> ligneCommandeClientOptional = ligneCommandeClientRepository.findById(idLigneCommande);

		if(ligneCommandeClientOptional.isEmpty()){
			throw new EntityNotFoundException(
					"Aucune ligne Commande client ,'a ete trouve avec l'id "+ idLigneCommande , ErrorCodes.LIGNE_COMMANDE_CLIENT_NOT_FOUND);
		}

		LigneCommandeClient ligneCommandeClient = ligneCommandeClientOptional.get();

		ligneCommandeClient.setQuantite(quantite);

		ligneCommandeClientRepository.save(ligneCommandeClient);

		return commandeClient;
	}

	@Override
	public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
		if (idCommande == null ){
			log.error("commande client ID is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un ID Null", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}
		if (StringUtils.hasLength(String.valueOf(etatCommande)) ){
			log.error("l'etat de la commande client is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un etat NULL ", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}

		CommandeClientDto commandeClient = findById(idCommande);

		if(commandeClient.getId() != null && commandeClient.isCommandeLivree()){
			throw new InvalidOperationException("impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}

		commandeClient.setEtatCommande(etatCommande);

		return CommandeClientDto.fromEntity(
				commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient)
		));


	}

	@Override
	public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {

		if (idCommande == null ){
			log.error("commande client ID is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un ID Null", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}
		if (idClient == null ){
			log.error("l'ID de client client is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id client NULL ", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}

		CommandeClientDto commandeClient = findById(idCommande);

		if(commandeClient.getId() != null && commandeClient.isCommandeLivree()){
			throw new InvalidOperationException("impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}

		Optional<Client> clientOptional= clientRepository.findById(idClient);
		if (clientOptional.isEmpty()){
			throw new InvalidOperationException("Aucun client n'a ete trouve avec l'id "+ idClient, ErrorCodes.CLIENT_NOT_FOUND);
		}

		commandeClient.setClient(ClientDto.fromEntity(clientOptional.get() ));
		return CommandeClientDto.fromEntity(
				commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient))
		);
	}

	@Override
	public CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer newIdArticle) {
		if (idCommande == null ){
			log.error("commande client ID is null");
			throw new InvalidOperationException("impossible de modifier l'article correspond a la commande  avec un ID Null", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}

		if (idLigneCommande == null ){
			log.error("l'id de la ligne commande client is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec une ligne de commande null  ", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}

		if (newIdArticle == null ){
			log.error("Article nouvel commande client ID is null");
			throw new InvalidOperationException("impossible de modifier l'article correspond a la commande  avec un nouvel ID article Null", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}

		CommandeClientDto commandeClient = findById(idCommande);

		if(commandeClient.getId() != null && commandeClient.isCommandeLivree()){
			throw new InvalidOperationException("impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}
		Optional<LigneCommandeClient> ligneCommandeClientOptional =ligneCommandeClientRepository.findById(idLigneCommande);

		if(ligneCommandeClientOptional.isEmpty()){
			throw new EntityNotFoundException(
					"Aucune ligne Commande client ,'a ete trouve avec l'id "+ idLigneCommande , ErrorCodes.LIGNE_COMMANDE_CLIENT_NOT_FOUND);
		}

		LigneCommandeClient ligneCommandeClient = ligneCommandeClientOptional.get();

		Optional<Article> articleOptional= articleRepository.findById(newIdArticle);
		if(articleOptional.isEmpty()){
			throw new EntityNotFoundException(
					"Aucune article n'a ete trouve avec l'id "+ newIdArticle , ErrorCodes.ARTICLE_NOT_FOUND);

		}

		List<String> errors =  ArticleValidator.validate(ArticleDto.fromEntity(articleOptional.get()));

		if (!errors.isEmpty()){
			throw new InvalidEntityException("Article invalid ",ErrorCodes.ARTICLE_NOT_VALID,errors);
		}

		ligneCommandeClient.setArticle(articleOptional.get());
		ligneCommandeClientRepository.save(ligneCommandeClient);

		return commandeClient;
	}

	@Override
	public CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande ) {
		if (idCommande == null ){
			log.error("commande client ID is null");
			throw new InvalidOperationException("impossible de modifier l'article correspond a la commande  avec un ID Null", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}

		if (idLigneCommande == null ){
			log.error("l'id de la ligne commande client is null");
			throw new InvalidOperationException("impossible de modifier l'etat de la commande avec une ligne de commande null  ", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
		}
		CommandeClientDto commandeClient = findById(idCommande);

		Optional<LigneCommandeClient> ligneCommandeClientOptional =ligneCommandeClientRepository.findById(idLigneCommande);

		if(ligneCommandeClientOptional.isEmpty()){
			throw new EntityNotFoundException(
					"Aucune ligne Commande client ,'a ete trouve avec l'id "+ idLigneCommande , ErrorCodes.LIGNE_COMMANDE_CLIENT_NOT_FOUND);
		}

		ligneCommandeClientRepository.deleteById(idLigneCommande);

		return commandeClient;
	}

	@Override
	public List<LigneCommandeClientDto> findAllLignesCommandesClientByCommandeClientId(Integer idCommande) {
		return ligneCommandeClientRepository.findAllByCommandeClientId(idCommande).stream()
				.map(LigneCommandeClientDto::fromEntity)
				.collect(Collectors.toList()) ;
	}
}
